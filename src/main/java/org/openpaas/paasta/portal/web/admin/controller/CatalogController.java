package org.openpaas.paasta.portal.web.admin.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.common.Constants;
import org.openpaas.paasta.portal.web.admin.common.User;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.model.Catalog;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.openpaas.paasta.portal.web.admin.util.MultipartFileResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * 서비스 카탈로그, 개발 환경 카탈로그, 앱 템플릿 카탈로그 정보 조회 및 관리 등의 API 를 호출 하는 컨트롤러이다.
 *
 * @version 1.0
 * @since 2016.07.04 최초작성
 */
@Controller
class CatalogController extends Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);

    private final String V2_URL = "/v2";

    /**
     * 카탈로그 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/catalogs"}, method = RequestMethod.GET)
    public ModelAndView getCatalogMain() {
        return new ModelAndView() {{
            setViewName("/catalog/catalogMain");
        }};
    }


    /**
     * 카탈로그 메인페이지로 이동한다.
     *
     * @param tabName 탭 이름(String)
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/catalogs/{tabName}"}, method = RequestMethod.GET)
    public ModelAndView getCatalogMain(@PathVariable("tabName") String tabName) {
        String reqTabName = Constants.TAB_NAME_STARTER;

        if (Constants.TAB_NAME_DEVELOP_PACK.equals(tabName)) reqTabName = Constants.TAB_NAME_DEVELOP_PACK;
        if (Constants.TAB_NAME_SERVICE_PACK.equals(tabName)) reqTabName = Constants.TAB_NAME_SERVICE_PACK;
        if (Constants.TAB_NAME_STARTER.equals(tabName)) reqTabName = Constants.TAB_NAME_STARTER;

        String finalReqTabName = reqTabName;
        return new ModelAndView() {{
            setViewName("/catalog/catalogMain");
            addObject("TAB_NAME", finalReqTabName);
        }};
    }


    /**
     * 앱 개발환경 저장페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/developpackForm"}, method = RequestMethod.GET)
    public ModelAndView getBuildPackForm() {
        return new ModelAndView() {{
            setViewName("/catalog/developPackForm");
            addObject("REQUEST_NO", "");
        }};
    }


    /**
     * 앱 개발환경 수정페이지로 이동한다.
     *
     * @param req HttpServletRequest(자바클래스)
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/developpackForm"}, method = RequestMethod.POST)
    public ModelAndView getBuildPackForm(HttpServletRequest req) {
        return new ModelAndView() {{
            setViewName("/catalog/developPackForm");
            addObject("REQUEST_NO", req.getParameter("no"));
        }};
    }


    /**
     * 서비스 저장페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/servicepackForm"}, method = RequestMethod.GET)
    public ModelAndView getServicePackForm() {
        return new ModelAndView() {{
            setViewName("/catalog/servicePackForm");
            addObject("REQUEST_NO", "");
        }};
    }


    /**
     * 서비스 수정페이지로 이동한다.
     *
     * @param req HttpServletRequest(자바클래스)
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/servicepackForm"}, method = RequestMethod.POST)
    public ModelAndView getServicePackForm(HttpServletRequest req) {

        return new ModelAndView() {{
            setViewName("/catalog/servicePackForm");
            addObject("REQUEST_NO", req.getParameter("no"));
        }};
    }


    /**
     * 앱 템플릿 저장페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/starterpackForm"}, method = RequestMethod.GET)
    public ModelAndView getStarterForm() {

        return new ModelAndView() {{
            setViewName("/catalog/starterPackForm");
            addObject("REQUEST_NO", "");
        }};
    }


    /**
     * 앱 템플릿 수정페이지로 이동한다.
     *
     * @param req HttpServletRequest(자바클래스)
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/starterpackForm"}, method = RequestMethod.POST)
    public ModelAndView getStarterForm(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();

        return new ModelAndView() {{
            setViewName("/catalog/starterPackForm");
            addObject("REQUEST_NO", req.getParameter("no"));
        }};
    }

    private ResponseEntity<byte[]> generateMethodNotAllowResponse() {
        final ResponseEntity<byte[]> resEntity = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        return resEntity;
    }

    /**
     * 이미지 파일을 가져온다.
     *
     * @param thumbnailFilename
     * @return ResponseEntity<byte [ ]> (relays response from storage api)
     */
    @GetMapping(V2_URL + "/thumbnail/{filename}")
    @ResponseBody
    public ResponseEntity<byte[]> getThumbnail(HttpServletRequest request, @PathVariable("filename") String thumbnailFilename) {
        String key = request.getParameter("key");
        ResponseEntity<byte[]> result = commonService.procStorageApiRestTemplateBinary(Integer.parseInt(key),thumbnailFilename, HttpMethod.GET,null);
        if (result.getHeaders().getContentType().toString().toLowerCase().startsWith("image")) {
            return result;
        } else {
            return generateMethodNotAllowResponse();
        }
    }

    /**
     * 이미지 파일을 업로드한다.
     *
     * @param multipartFile MultipartFile(Spring 클래스)
     * @return ResponseEntity<Map < String, Object>> (relays response from storage api)
     * @throws Exception Exception(자바클래스)
     */
    @SuppressWarnings("unchecked")
    @PostMapping(V2_URL + "/thumbnail")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadThumbnail(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        try {
            String key = request.getParameter("key");

            MultiValueMap<String, Object> requestBodyObject = new LinkedMultiValueMap<>();
            requestBodyObject.add("file", new MultipartFileResource(multipartFile));

            ResponseEntity<String> result = commonService.procStorageApiRestTemplateText(Integer.parseInt(key), null, HttpMethod.POST, requestBodyObject);

            Map<String, Object> resultMap = new ObjectMapper().readValue(result.getBody().toString(), Map.class);

            resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);
            final ResponseEntity<Map<String, Object>> generateResponseEntity = new ResponseEntity<Map<String, Object>>(resultMap, result.getHeaders(), result.getStatusCode());
            return generateResponseEntity;
        } finally {
            IOUtils.closeQuietly(multipartFile.getInputStream());
        }
    }


    /**
     * 이미지 파일을 삭제한다.
     *
     * @return Response<String> (relays response from storage api)
     */
    @DeleteMapping(V2_URL + "/thumbnail/{filename:.+}")
    @ResponseBody
    public ResponseEntity<String> deleteThumbnailImage(HttpServletRequest request, @PathVariable("filename") String thumbnailFilename) {
        String key = request.getParameter("key");
        final ResponseEntity<String> result = commonService.procStorageApiRestTemplateText(Integer.parseInt(key), thumbnailFilename, HttpMethod.DELETE, null);
        return result;
    }

    /**
     * 앱 샘플 파일을 가져온다.
     *
     * @param appSampleFilename
     * @return ResponseEntity<byte [ ]> (relays response from storage api)
     */
    @GetMapping(V2_URL + "/appsample/{filename}")
    @ResponseBody
    public ResponseEntity<byte[]> getAppSampleFile(HttpServletRequest request, @PathVariable("filename") String appSampleFilename) {
        String key = request.getParameter("key");
        ResponseEntity<byte[]> result = commonService.procStorageApiRestTemplateBinary(Integer.parseInt(key), appSampleFilename, HttpMethod.GET, null);
        switch (result.getHeaders().getContentType().toString().toLowerCase()) {
            // zip, tar, rar, bz, bz2, 7z
            case "application/zip":
            case "application/x-tar":
            case "application/x-rar-compressed":
            case "application/x-bzip":
            case "application/x-bzip2":
            case "application/x-7z-compressed":
                return result;
            default:
                return generateMethodNotAllowResponse();
        }
    }

    /**
     * 앱 샘플 파일을 업로드한다.
     *
     * @param multipartFile MultipartFile(Spring 클래스)
     * @return ResponseEntity<Map < String, Object>> (relays response from storage api)
     * @throws Exception Exception(자바클래스)
     */
    @SuppressWarnings("unchecked")
    @PostMapping(V2_URL + "/appsample")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadAppSampleFile(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        try {
            String key = request.getParameter("key");
            MultiValueMap<String, Object> requestBodyObject = new LinkedMultiValueMap<>();
            requestBodyObject.add("file", new MultipartFileResource(multipartFile));
            ResponseEntity<String> result = commonService.procStorageApiRestTemplateText(Integer.parseInt(key), null, HttpMethod.POST, requestBodyObject);
            Map<String, Object> resultMap = new ObjectMapper().readValue(result.getBody().toString(), Map.class);
            resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

            final ResponseEntity<Map<String, Object>> generateResponseEntity = new ResponseEntity<Map<String, Object>>(resultMap, result.getHeaders(), result.getStatusCode());
            return generateResponseEntity;
        } finally {
            IOUtils.closeQuietly(multipartFile.getInputStream());
        }
    }


    /**
     * 앱 샘플 파일을 삭제한다.
     *
     * @param appSampleFilename
     * @return ResponseEntity&lt;String&gt; (relays response from storage api)
     */
    @DeleteMapping(V2_URL + "/appsample/{filename}")
    @ResponseBody
    public ResponseEntity<String> deleteAppSampleFile(HttpServletRequest request, @PathVariable("filename") String appSampleFilename) {
        String key = request.getParameter("key");
        final ResponseEntity<String> result = commonService.procStorageApiRestTemplateText(Integer.parseInt(key), appSampleFilename, HttpMethod.DELETE, null);
        return result;
    }


    /*
     * ------------------------------------------------------------------------------------조회
     */


    /**
     * [앱 템플릿] 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/starterpacks")
    @ResponseBody
    public Map<String, Object> getStarterNamesList(HttpServletRequest request, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getStarterPacksList(Integer.parseInt(key), param);
    }


    /**
     * [앱 템플릿] 상세 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/starterpacks/{no}")
    @ResponseBody
    public Map<String, Object> getStarterNames(HttpServletRequest request, @PathVariable("no") int no, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getStarterPack(Integer.parseInt(key), no, param);
    }

    /**
     * [앱 개발환경] 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/developpacks")
    @ResponseBody
    public Map<String, Object> getBuildPackCatalogList(HttpServletRequest request, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getDevelopPackCatalogList(Integer.parseInt(key), param);
    }

    /**
     * [앱 개발환경] 상세 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/developpacks/{no}")
    @ResponseBody
    public Map<String, Object> getBuildPackCatalog(HttpServletRequest request, @PathVariable("no") int no, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getDevelopPackCatalog(Integer.parseInt(key), no, param);
    }

    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */

    @GetMapping(V2_URL + "/servicepacks")
    @ResponseBody
    public Map<String, Object> getServicePackCatalogList(HttpServletRequest request, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getServicePackCatalogList(Integer.parseInt(key), param);
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/servicepacks/{no}")
    @ResponseBody
    public Map<String, Object> getServicePackCatalog(HttpServletRequest request, @PathVariable("no") int no, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getServicePackCatalog(Integer.parseInt(key), no, param);
    }

    /**
     * [앱 템플릿] 해당 이름으로 등록된 [앱 템플릿] 개수를 조회한다.(등록 중복체크 용도)
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/starterpacks/count")
    @ResponseBody
    public Map<String, Object> getStarterNamesCount(HttpServletRequest request, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getStarterPackCount(Integer.parseInt(key), param);
    }

    /**
     * [앱 템플릿]에서 사용중인 [앱 개발환경] 개수를 조회한다.(앱 개발환경 삭제 가능체크 용도)
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/developpacks/count")
    @ResponseBody
    public Map<String, Object> getBuildPackCatalogCount(HttpServletRequest request, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getDevelopPackCatalogCount(Integer.parseInt(key), param);
    }

    /**
     * [앱 템플릿]에서 사용중인 [앱 서비스] 개수를 조회한다.(앱 서비스 삭제 가능체크 용도)
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/servicepacks/count")
    @ResponseBody
    public Map<String, Object> getServicePackCatalogCount(HttpServletRequest request, @ModelAttribute Catalog param) {
        String key = request.getParameter("key");
        return catalogService.getServicePackCatalogCount(Integer.parseInt(key), param);
    }

    /**
     * [앱 서비스] 서비스 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/services")
    @ResponseBody
    public Map<String, Object> getServices(HttpServletRequest request) {
        String key = request.getParameter("key");
        return catalogService.getServices(Integer.parseInt(key));
    }

    /*
     * ------------------------------------------------------------------------------------조회 끝
     */


    /*
     * ------------------------------------------------------------------------------------추가
     */

    /**
     * 스타터 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @PostMapping(value = {V2_URL + "/starterpacks"})
    @ResponseBody
    public Map<String, Object> insertStarterPackCatalog(HttpServletRequest request, @RequestBody Catalog param) throws Exception {
        String key = request.getParameter("key");
        LOGGER.info(param.toString());
        return catalogService.insertStarterPack(Integer.parseInt(key), param);
    }

    /**
     * 빌드 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @PostMapping(value = {V2_URL + "/developpacks"})
    @ResponseBody
    public Map<String, Object> insertBuildPackCatalog(HttpServletRequest request, @RequestBody Catalog param) throws Exception {
        String key = request.getParameter("key");
        LOGGER.info(param.toString());
        return catalogService.insertDevelopPackCatalog(Integer.parseInt(key), param);
    }

    /**
     * 서비스 팩을 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @PostMapping(value = {V2_URL + "/servicepacks"})
    @ResponseBody
    public Map<String, Object> insertServicePack(HttpServletRequest request, @RequestBody Catalog param) throws Exception {
        String key = request.getParameter("key");
        return catalogService.insertServicePackCatalog(Integer.parseInt(key), param);
    }


    /*
     * ------------------------------------------------------------------------------------추가 끝
     */



    /*
     * ------------------------------------------------------------------------------------수정
     */

    /**
     * 스타터 팩을 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @PutMapping(value = {V2_URL + "/starterpacks/{no}"})
    @ResponseBody
    public Map<String, Object> updateStarterPackCatalog(HttpServletRequest request, @PathVariable int no, @RequestBody Catalog param) throws Exception {
        String key = request.getParameter("key");
        return catalogService.updateStarterPack(Integer.parseInt(key), no, param);
    }

    /**
     * 빌드 팩을 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @PutMapping(value = {V2_URL + "/developpacks/{no}"})
    @ResponseBody
    public Map<String, Object> updateBuildPackCatalog(HttpServletRequest request, @PathVariable int no, @RequestBody Catalog param) throws Exception {
        String key = request.getParameter("key");
        return catalogService.updateDevelopPackCatalog(Integer.parseInt(key), no, param);
    }

    /**
     * 서비스 팩을 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @PutMapping(value = {V2_URL + "/servicepacks/{no}"})
    @ResponseBody
    public Map<String, Object> updateServicePack(HttpServletRequest request, @PathVariable int no, @RequestBody Catalog param) throws Exception {
        String key = request.getParameter("key");
        LOGGER.info("updateServicePack :::: " + param.toString());
        return catalogService.updateServicePackCatalog(Integer.parseInt(key), no, param);
    }

    /*
     * ------------------------------------------------------------------------------------수정 끝
     */

    /*
     * ------------------------------------------------------------------------------------삭제
     */

    /**
     * 스타터 팩을 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/starterpacks/{no}"})
    @ResponseBody
    public Map<String, Object> deleteStarterPackCatalog(HttpServletRequest request, @PathVariable int no) {
        String key = request.getParameter("key");
        return catalogService.deleteStarterPack(Integer.parseInt(key), no);
    }


    /**
     * 빌드 팩을 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/developpacks/{no}"})
    @ResponseBody
    public Map<String, Object> deleteBuildPackCatalog(HttpServletRequest request, @PathVariable int no) {
        String key = request.getParameter("key");
        return catalogService.deleteBuildPackCatalog(Integer.parseInt(key), no);
    }


    /**
     * 서비스 팩을 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/servicepacks/{no}"})
    @ResponseBody
    public Map<String, Object> deleteServicePackCatalog(HttpServletRequest request, @PathVariable int no) {
        String key = request.getParameter("key");
        return catalogService.deleteServicePackCatalog(Integer.parseInt(key), no);
    }

    /*
     * ------------------------------------------------------------------------------------삭제 끝
     */
    @ModelAttribute("configs")
    public List<User> configs() {
        return getServerInfos();
    }

}
