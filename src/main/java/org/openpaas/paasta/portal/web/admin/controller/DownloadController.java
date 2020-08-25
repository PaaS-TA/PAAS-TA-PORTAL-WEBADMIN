package org.openpaas.paasta.portal.web.admin.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.openpaas.paasta.portal.web.admin.common.Common;
import org.openpaas.paasta.portal.web.admin.entity.ConfigEntity;
import org.openpaas.paasta.portal.web.admin.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * 다운로드 컨트롤러
 *
 * @version 1.0
 * @since 2016.09.12
 */
@Controller
public class DownloadController extends Common {
    /**
     * Download.
     *
     * @param url              the url
     * @param originalFileName the original file name
     * @param response         the response
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/download/url"}, method = RequestMethod.GET)
    public void download(@PathParam("url") String url, @PathParam("originalFileName") String originalFileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // MIME Type 을 application/octet-stream 타입으로 변경
        // 무조건 팝업(다운로드창)이 뜨게 된다.
        response.setContentType("application/octet-stream");

        // 브라우저 별로 파일명 인코딩 방식이 다르기 때문에 브라우저 별로 파일이름을 가져온다.
        String fileNameForBrowser = getDisposition(originalFileName, getBrowser(request));


        // 파일명 지정
        response.setHeader("Content-Disposition", "attachment; filename="+fileNameForBrowser);

        OutputStream os = response.getOutputStream();
        InputStream is = new URL(url).openStream();
        
        // the new way
        final int returnCode = IOUtils.copy( is, os );
        if (returnCode == -1) 
            IOUtils.copyLarge( is, os );

        IOUtils.closeQuietly( is );
        IOUtils.closeQuietly( os );
    }

    /**
     * 내부망에 있는 이미지 자원에 접근할수 있도록 컨트롤러를 통해 우회한다.
     *
     * @param imgPath  the img path
     * @param response the response
     * @throws IOException the io exception
     * @author Hyungu Cho (hgcho)
     * @since 2017-01-04
     */
    @GetMapping( "/download/{img-path:.+}" )
    @ResponseBody
    public Object getImage(@PathVariable( "img-path" ) final String imgPath) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-store");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        byte[] imgByte;
        if ( "".equals( imgPath.replaceAll( " ", "" ) ) ) {
            // prevents NullPointerException 
            imgByte = new byte[0];
            headers.setContentType( MediaType.APPLICATION_OCTET_STREAM );
            
            //파일 확장자에 따라 컨텐트 타입 변경
            /*
            int index = imgPath.lastIndexOf(".");
            String ext = "";
            if (index != -1) {
                ext  = imgPath.substring(index + 1);
            }
            
            if (imgByte.length > 0 ) {
                if ( ext.equals( "png" ) )
                    response.setContentType( "image/png" );
                if ( ext.equals( "jpg" ) || ext.equals( "jpeg" ) ) 
                    response.setContentType( "image/jpeg" );
            } else {
                response.setContentType( "application/octet-stream" );
            }
            */
        } else {
            ResponseEntity<byte[]> resultEntity = commonService.procStorageApiRestTemplateBinary( imgPath, HttpMethod.GET, null, getToken());
            imgByte = resultEntity.getBody();
            
            if (imgByte.length > 0)
                headers.setContentType( resultEntity.getHeaders().getContentType() );
        }
        
        
        final ResponseEntity<byte[]> responseEntity;
        if (imgByte.length <= 0)
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            responseEntity = new ResponseEntity<>(imgByte, headers, HttpStatus.CREATED);
        
        return responseEntity;
    }

    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        } else if (header.indexOf("Trident/7.0") > -1){
            //IE 11 이상 //IE 버전 별 체크 >> Trident/6.0(IE 10) , Trident/5.0(IE 9) , Trident/4.0(IE 8)
            return "MSIE";
        }

        return "Firefox";
    }

    private String getDisposition(String filename, String browser) throws Exception {
        String encodedFilename = null;

        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < filename.length(); i++) {
                char c = filename.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        } else {
            throw new RuntimeException("Not supported browser");
        }

        return encodedFilename;
    }


    @Autowired
    ConfigService configService;

    @ModelAttribute("configs")
    public List<ConfigEntity> configs(){
        return configService.getConfigs();
    }
}
