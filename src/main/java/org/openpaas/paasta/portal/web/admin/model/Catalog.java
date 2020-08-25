package org.openpaas.paasta.portal.web.admin.model;

import java.util.List;
import java.util.UUID;

/**
 * 카탈로그(모델클래스)
 * 카탈로그 정보를 가지고 있는 데이터 모델 bean 클래스
 * Json 어노테이션을 사용해서 JSON 형태로 제공
 *
 */
public class Catalog {

    private int no;
    private String name;
    private String classification;
    private String classificationValue;
    private String classificationSummary;
    private String summary;
    private String description;
    private String thumbImgName;
    private String thumbImgPath;
    private String useYn;
    private String userId;
    private String created;
    private String lastModified;
    private String buildPackName;
    private String servicePackName;
    private int starterCategoryNo;
    private int servicePackCategoryNo;
    private int buildPackCategoryNo;
    private String searchKeyword;
    private String searchTypeColumn;
    private String searchTypeUseYn;
    private List<Integer> servicePackCategoryNoList;
    private int catalogNo;
    private String catalogType;
    private String servicePlan;
    private String appName;
    private String orgName;
    private String spaceName;
    private String serviceInstanceName;
    private UUID appGuid;
    private UUID serviceInstanceGuid;
    private List<Catalog> servicePlanList;
    private int limitSize;
    private String hostName;
    private String domainName;
    private String routeName;
    private String appSampleStartYn;
    private String appSampleFileName;
    private String appSampleFilePath;
    private int appSampleFileSize;
    private String docFileUrl;
    private String appBindYn;
    private String parameter;
    private String appBindParameter;
    private int diskSize;
    private int MemorySize;
    private String dashboardUseYn;
    private String tagsParam;
    private String onDemandYn;

    /**
     * GETTER 카탈로그 번호
     *
     * @return 카탈로그 번호
     */
    public int getNo() {
        return this.no;
    }

    /**
     * SETTER 카탈로그 번호
     *
     * @param no 카탈로그 번호
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * GETTER 카탈로그명
     *
     * @return 카탈로그명
     */
    public String getName() {
        return this.name;
    }

    /**
     * SETTER 카탈로그명
     *
     * @param name 카탈로그명
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * GETTER 구분
     *
     * @return 구분
     */
    public String getClassification() {
        return this.classification;
    }

    /**
     * SETTER 구분
     *
     * @param classification 구분
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * GETTER 구분값
     *
     * @return 구분값
     */
    public String getClassificationValue() {
        return this.classificationValue;
    }

    /**
     * SETTER 구분값
     *
     * @param classificationValue 구분값
     */
    public void setClassificationValue(String classificationValue) {
        this.classificationValue = classificationValue;
    }

    /**
     * GETTER 구분 요약
     *
     * @return 구분 요약
     */
    public String getClassificationSummary() {
        return this.classificationSummary;
    }

    /**
     * SETTER 구분 요약
     *
     * @param classificationSummary 구분 요약
     */
    public void setClassificationSummary(String classificationSummary) {
        this.classificationSummary = classificationSummary;
    }

    /**
     * GETTER 요약
     *
     * @return 요약
     */
    public String getSummary() {
        return this.summary;
    }

    /**
     * SETTER 요약
     *
     * @param summary 요약
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * GETTER 설명
     *
     * @return 설명
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * SETTER 설명
     *
     * @param description 설명
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * GETTER 이미지명
     *
     * @return 이미지명
     */
    public String getThumbImgName() {
        return this.thumbImgName;
    }

    /**
     * SETTER 이미지명
     *
     * @param thumbImgName 이미지명
     */
    public void setThumbImgName(String thumbImgName) {
        this.thumbImgName = thumbImgName;
    }

    /**
     * GETTER 이미지 경로
     *
     * @return 이미지 경로
     */
    public String getThumbImgPath() {
        return this.thumbImgPath;
    }

    /**
     * SETTER 이미지 경로
     *
     * @param thumbImgPath 이미지 경로
     */
    public void setThumbImgPath(String thumbImgPath) {
        this.thumbImgPath = thumbImgPath;
    }

    /**
     * GETTER 문서 파일 경로
     *
     * @return docFileUrl 문서 파일 경로
     */
    public String getDocFileUrl() {
        return docFileUrl;
    }

    /**
     * SETTER 문서 파일 경로
     *
     * @param docFileUrl 문서 파일 경로
     */
    public void setDocFileUrl(String docFileUrl) {
        this.docFileUrl = docFileUrl;
    }

    /**
     * GETTER 태그 파라미터 리스트
     *
     * @return 태그 파라미터 리스트
     */
    public String getTagsParam() {
        return tagsParam;
    }

    /**
     * SETTER 태그 파라미터 리스트
     *
     * @param tagsParam 태그 파라미터 리스트
     */
    public void setTagsParam(String tagsParam) {
        this.tagsParam = tagsParam;
    }

    /**
     * GETTER 사용유무
     *
     * @return 사용유무
     */
    public String getUseYn() {
        return this.useYn;
    }

    /**
     * SETTER 사용유무
     *
     * @param useYn 사용유무
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * GETTER 사용자 아이디
     *
     * @return 사용자 아이디
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * SETTER 사용자 아이디
     *
     * @param userId 사용자 아이디
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * GETTER 생성일
     *
     * @return 생성일
     */
    public String getCreated() {
        return this.created;
    }

    /**
     * SETTER 생성일.
     *
     * @param created 생성일
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * GETTER 수정일
     *
     * @return 수정일
     */
    public String getLastModified() {
        return this.lastModified;
    }

    /**
     * SETTER 수정일
     *
     * @param lastModified 수정일
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getBuildPackName() {
        return buildPackName;
    }

    public void setBuildPackName(String buildPackName) {
        this.buildPackName = buildPackName;
    }

    public String getServicePackName() {
        return servicePackName;
    }

    public void setServicePackName(String servicePackName) {
        this.servicePackName = servicePackName;
    }

    /**
     * GETTER 스타터 카테고리 번호
     *
     * @return 스타터 카테고리 번호
     */
    public int getStarterCategoryNo() {
        return this.starterCategoryNo;
    }

    /**
     * SETTER 스타터 카테고리 번호
     *
     * @param starterCategoryNo 스타터 카테고리 번호
     */
    public void setStarterCategoryNo(int starterCategoryNo) {
        this.starterCategoryNo = starterCategoryNo;
    }

    /**
     * GETTER 서비스팩 카테고리 번호
     *
     * @return 서비스팩 카테고리 번호
     */
    public int getServicePackCategoryNo() {
        return this.servicePackCategoryNo;
    }

    /**
     * SETTER 서비스팩 카테고리 번호
     *
     * @param servicePackCategoryNo 서비스팩 카테고리 번호
     */
    public void setServicePackCategoryNo(int servicePackCategoryNo) {
        this.servicePackCategoryNo = servicePackCategoryNo;
    }

    /**
     * GETTER 빌드팩 카테고리 번호
     *
     * @return 빌드팩 카테고리 번호
     */
    public int getBuildPackCategoryNo() {
        return this.buildPackCategoryNo;
    }

    /**
     * SETTER 빌드팩 카테고리 번호
     *
     * @param buildPackCategoryNo 빌드팩 카테고리 번호
     */
    public void setBuildPackCategoryNo(int buildPackCategoryNo) {
        this.buildPackCategoryNo = buildPackCategoryNo;
    }

    /**
     * GETTER 검색어
     *
     * @return 검색어
     */
    public String getSearchKeyword() {
        return this.searchKeyword;
    }

    /**
     * SETTER 검색어
     *
     * @param searchKeyword 검색어
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * GETTER 검색 형태 컬럼
     *
     * @return 검색 형태 컬럼
     */
    public String getSearchTypeColumn() {
        return this.searchTypeColumn;
    }

    /**
     * SETTER 검색 형태 컬럼
     *
     * @param searchTypeColumn 검색 형태 컬럼
     */
    public void setSearchTypeColumn(String searchTypeColumn) {
        this.searchTypeColumn = searchTypeColumn;
    }

    /**
     * GETTER 검색 형태 사용유무
     *
     * @return 검색 형태 사용유무
     */
    public String getSearchTypeUseYn() {
        return this.searchTypeUseYn;
    }

    /**
     * SETTER 검색 형태 사용유무
     *
     * @param searchTypeUseYn 검색 형태 사용유무
     */
    public void setSearchTypeUseYn(String searchTypeUseYn) {
        this.searchTypeUseYn = searchTypeUseYn;
    }

    /**
     * GETTER 서비스팩 카테고리 번호 목록
     *
     * @return 서비스팩 카테고리 번호 목록
     */
    public List<Integer> getServicePackCategoryNoList() {
        return this.servicePackCategoryNoList;
    }

    /**
     * SETTER 서비스팩 카테고리 번호 목록
     *
     * @param servicePackCategoryNoList 서비스팩 카테고리 번호 목록
     */
    public void setServicePackCategoryNoList(List<Integer> servicePackCategoryNoList) {
        this.servicePackCategoryNoList = servicePackCategoryNoList;
    }

    /**
     * GETTER 카탈로그 번호
     *
     * @return 카탈로그 번호
     */
    public int getCatalogNo() {
        return this.catalogNo;
    }

    /**
     * SETTER 카탈로그 번호
     *
     * @param catalogNo 카탈로그 번호
     */
    public void setCatalogNo(int catalogNo) {
        this.catalogNo = catalogNo;
    }

    /**
     * GETTER 카탈로그 타입
     *
     * @return 카탈로그 타입
     */
    public String getCatalogType() {
        return this.catalogType;
    }

    /**
     * SETTER 카탈로그 타입
     *
     * @param catalogType 카탈로그 타입
     */
    public void setCatalogType(String catalogType) {
        this.catalogType = catalogType;
    }

    /**
     * GETTER 서비스 플랜
     *
     * @return 서비스 플랜
     */
    public String getServicePlan() {
        return this.servicePlan;
    }

    /**
     * SETTER 서비스 플랜
     *
     * @param servicePlan 서비스 플랜
     */
    public void setServicePlan(String servicePlan) {
        this.servicePlan = servicePlan;
    }

    /**
     * GETTER 애플리케이션명
     *
     * @return 애플리케이션명
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * SETTER 애플리케이션명
     *
     * @param appName 애플리케이션명
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * GETTER 조직명
     *
     * @return 조직명
     */
    public String getOrgName() {
        return this.orgName;
    }

    /**
     * SETTER 조직명
     *
     * @param orgName 조직명
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * GETTER 공간명
     *
     * @return 공간명
     */
    public String getSpaceName() {
        return this.spaceName;
    }

    /**
     * SETTER 공간명
     *
     * @param spaceName 공간명
     */
    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    /**
     * GETTER 서비스 인스턴스명
     *
     * @return 서비스 인스턴스명
     */
    public String getServiceInstanceName() {
        return this.serviceInstanceName;
    }

    /**
     * SETTER 서비스 인스턴스명
     *
     * @param serviceInstanceName 서비스 인스턴스명
     */
    public void setServiceInstanceName(String serviceInstanceName) {
        this.serviceInstanceName = serviceInstanceName;
    }

    /**
     * GETTER 애플리케이션 GUID
     *
     * @return 애플리케이션 GUID
     */
    public UUID getAppGuid() {
        return this.appGuid;
    }

    /**
     * SETTER 애플리케이션 GUID
     *
     * @param appGuid 애플리케이션 GUID
     */
    public void setAppGuid(UUID appGuid) {
        this.appGuid = appGuid;
    }

    /**
     * GETTER 서비스 인스턴스 GUID
     *
     * @return 서비스 인스턴스 GUID
     */
    public UUID getServiceInstanceGuid() {
        return this.serviceInstanceGuid;
    }

    /**
     * SETTER 서비스 인스턴스 GUID
     *
     * @param serviceInstanceGuid 서비스 인스턴스 GUID
     */
    public void setServiceInstanceGuid(UUID serviceInstanceGuid) {
        this.serviceInstanceGuid = serviceInstanceGuid;
    }

    /**
     * GETTER 서비스 플랜 목록
     *
     * @return 서비스 플랜 목록
     */
    public List<Catalog> getServicePlanList() {
        return this.servicePlanList;
    }

    /**
     * SETTER 서비스 플랜 목록
     *
     * @param servicePlanList 서비스 플랜 목록
     */
    public void setServicePlanList(List<Catalog> servicePlanList) {
        this.servicePlanList = servicePlanList;
    }

    /**
     * GETTER 제한 크기
     *
     * @return 제한 크기
     */
    public int getLimitSize() {
        return this.limitSize;
    }

    /**
     * SETTER 제한 크기
     *
     * @param limitSize 제한 크기
     */
    public void setLimitSize(int limitSize) {
        this.limitSize = limitSize;
    }

    /**
     * GETTER 호스트명
     *
     * @return 호스트명
     */
    public String getHostName() {
        return this.hostName;
    }

    /**
     * SETTER 호스트명
     *
     * @param hostName 호스트명
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * GETTER 도메인명
     *
     * @return 도메인명
     */
    public String getDomainName() {
        return this.domainName;
    }

    /**
     * SETTER 도메인명
     *
     * @param domainName 도메인명
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * GETTER 라우트명
     *
     * @return 라우트명
     */
    public String getRouteName() {
        return this.routeName;
    }

    /**
     * SETTER 라우트명
     *
     * @param routeName 라우트명
     */
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /**
     * GETTER 애플리케이션 샘플 시작 유무
     *
     * @return 애플리케이션 샘플 시작 유무
     */
    public String getAppSampleStartYn() {
        return this.appSampleStartYn;
    }

    /**
     * SETTER 애플리케이션 샘플 시작 유무
     *
     * @param appSampleStartYn 애플리케이션 샘플 시작 유무
     */
    public void setAppSampleStartYn(String appSampleStartYn) {
        this.appSampleStartYn = appSampleStartYn;
    }

    /**
     * GETTER 애플리케이션 샘플 파일명
     *
     * @return 애플리케이션 샘플 파일명
     */
    public String getAppSampleFileName() {
        return this.appSampleFileName;
    }

    /**
     * SETTER 애플리케이션 샘플 파일명
     *
     * @param appSampleFileName 애플리케이션 샘플 파일명
     */
    public void setAppSampleFileName(String appSampleFileName) {
        this.appSampleFileName = appSampleFileName;
    }

    /**
     * GETTER 애플리케이션 샘플 파일 경로
     *
     * @return 애플리케이션 샘플 파일 경로
     */
    public String getAppSampleFilePath() {
        return this.appSampleFilePath;
    }

    /**
     * SETTER 애플리케이션 샘플 파일 경로
     *
     * @param appSampleFilePath 애플리케이션 샘플 파일 경로
     */
    public void setAppSampleFilePath(String appSampleFilePath) {
        this.appSampleFilePath = appSampleFilePath;
    }

    /**
     * GETTER 애플리케이션 샘플 파일 크기
     *
     * @return 애플리케이션 샘플 파일 크기
     */
    public int getAppSampleFileSize() {
        return this.appSampleFileSize;
    }

    /**
     * SETTER 애플리케이션 샘플 파일 크기
     *
     * @param appSampleFileSize 애플리케이션 샘플 파일 크기
     */
    public void setAppSampleFileSize(int appSampleFileSize) {
        this.appSampleFileSize = appSampleFileSize;
    }

    /**
     * GETTER 애플리케이션 바인드 유무
     *
     * @return 애플리케이션 바인드 유무
     */
    public String getAppBindYn() {
        return this.appBindYn;
    }

    /**
     * SETTER 애플리케이션 바인드 유무
     *
     * @param appBindYn 애플리케이션 바인드 유무
     */
    public void setAppBindYn(String appBindYn) {
        this.appBindYn = appBindYn;
    }

    /**
     * GETTER 파라미터
     *
     * @return 파라미터
     */
    public String getParameter() {
        return this.parameter;
    }

    /**
     * SETTER 파라미터
     *
     * @param parameter 파라미터
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * GETTER 파라미터
     *
     * @return 파라미터
     */
    public String getAppBindParameter() {
        return appBindParameter;
    }

    /**
     * SETTER 파라미터
     *
     * @param appBindParameter 파라미터
     */

    public void setAppBindParameter(String appBindParameter) {
        this.appBindParameter = appBindParameter;
    }

    /**
     * GETTER 디스크 크기
     *
     * @return 디스크 크기
     */
    public int getDiskSize() {
        return this.diskSize;
    }

    /**
     * SETTER 디스크 크기
     *
     * @param diskSize 디스크 크기
     */
    public void setDiskSize(int diskSize) {
        this.diskSize = diskSize;
    }

    /**
     * GETTER 메모리 크기
     *
     * @return 메모리 크기
     */
    public int getMemorySize() {
        return this.MemorySize;
    }

    /**
     * SETTER 메모리 크기
     *
     * @param memorySize 메모리 크기
     */
    public void setMemorySize(int memorySize) {
        this.MemorySize = memorySize;
    }

    /**
     * GETTER 대시보드 사용유무
     *
     * @return 대시보드 사용유무
     */
    public String getDashboardUseYn() {
        return dashboardUseYn;
    }

    /**
     * SETTER 대시보드 사용유무
     *
     * @param dashboardUseYn 대시보드 사용유무
     */
    public void setDashboardUseYn(String dashboardUseYn) {
        this.dashboardUseYn = dashboardUseYn;
    }


    public String getOnDemandYn() { return onDemandYn; }

    public void setOnDemandYn(String onDemandYn) { this.onDemandYn = onDemandYn; }

    @Override
    public String toString() {
        return "Catalog{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", classification='" + classification + '\'' +
                ", classificationValue='" + classificationValue + '\'' +
                ", classificationSummary='" + classificationSummary + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", thumbImgName='" + thumbImgName + '\'' +
                ", thumbImgPath='" + thumbImgPath + '\'' +
                ", useYn='" + useYn + '\'' +
                ", userId='" + userId + '\'' +
                ", created='" + created + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", buildPackName='" + buildPackName + '\'' +
                ", servicePackName='" + servicePackName + '\'' +
                ", starterCategoryNo=" + starterCategoryNo +
                ", servicePackCategoryNo=" + servicePackCategoryNo +
                ", buildPackCategoryNo=" + buildPackCategoryNo +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", searchTypeColumn='" + searchTypeColumn + '\'' +
                ", searchTypeUseYn='" + searchTypeUseYn + '\'' +
                ", servicePackCategoryNoList=" + servicePackCategoryNoList +
                ", catalogNo=" + catalogNo +
                ", catalogType='" + catalogType + '\'' +
                ", servicePlan='" + servicePlan + '\'' +
                ", appName='" + appName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", spaceName='" + spaceName + '\'' +
                ", serviceInstanceName='" + serviceInstanceName + '\'' +
                ", appGuid=" + appGuid +
                ", serviceInstanceGuid=" + serviceInstanceGuid +
                ", servicePlanList=" + servicePlanList +
                ", limitSize=" + limitSize +
                ", hostName='" + hostName + '\'' +
                ", domainName='" + domainName + '\'' +
                ", routeName='" + routeName + '\'' +
                ", appSampleStartYn='" + appSampleStartYn + '\'' +
                ", appSampleFileName='" + appSampleFileName + '\'' +
                ", appSampleFilePath='" + appSampleFilePath + '\'' +
                ", appSampleFileSize=" + appSampleFileSize +
                ", docFileUrl=" + docFileUrl +
                ", appBindYn='" + appBindYn + '\'' +
                ", parameter='" + parameter + '\'' +
                ", appBindParameter='" + appBindParameter + '\'' +
                ", diskSize=" + diskSize +
                ", MemorySize=" + MemorySize +
                ", dashboardUseYn='" + dashboardUseYn + '\'' +
                '}';
    }
}
