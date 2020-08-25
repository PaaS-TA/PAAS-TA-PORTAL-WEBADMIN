package org.openpaas.paasta.portal.web.admin.model;

import java.util.List;

/**
 * 공통코드(모델클래스)
 * 공통코드 정보를 가지고 있는 데이터 모델 bean 클래스
 * Json 어노테이션을 사용해서 JSON 형태로 제공
 *
 * @version 1.0
 * @since 2016.06.17 최초작성
 */
public class CommonCode {

    private String id;
    private int no;
    private String orgId;
    private String name;
    private String key;
    private String orgKey;
    private String value;
    private String groupId;
    private String group_id;
    private String useYn;
    private int order;
    private String summary;
    private String userId;
    private String created;
    private String lastModified;
    private String procType;
    private int pageNo;
    private int pageSize;
    private String searchType;
    private String searchKeyword;
    private String reqCud;
    private List<CommonCode> commonCodeList;

    /**
     * GETTER 아이디
     *
     * @return 아이디
     */
    public String getId() {
        return id;
    }

    /**
     * SETTER 아이디
     *
     * @param id 아이디
     */
    public void setId(String id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    /**
     * GETTER 조직 아이디
     *
     * @return 조직 아이디
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * SETTER 조직 아이디
     *
     * @param orgId 조직 아이디
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * GETTER 이름
     *
     * @return 이름
     */
    public String getName() {
        return name;
    }

    /**
     * SETTER 이름
     *
     * @param name 이름
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * GETTER 키
     *
     * @return 키
     */
    public String getKey() {
        return key;
    }

    /**
     * SETTER 키
     *
     * @param key 키
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * GETTER 원본 키
     *
     * @return 원본 키
     */
    public String getOrgKey() {
        return orgKey;
    }

    /**
     * SETTER 원본 키
     *
     * @param orgKey 원본 키
     */
    public void setOrgKey(String orgKey) {
        this.orgKey = orgKey;
    }

    /**
     * GETTER 값
     *
     * @return 값
     */
    public String getValue() {
        return value;
    }

    /**
     * SETTER 값
     *
     * @param value 값
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * GETTER 그룹 아이디
     *
     * @return 그룹 아이디
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * SETTER 그룹 아이디
     *
     * @param groupId 그룹 아이디
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * GETTER 사용 유무
     *
     * @return 사용 유무
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * SETTER 사용 유무
     *
     * @param useYn 사용 유무
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * GETTER 순서
     *
     * @return 순서
     */
    public int getOrder() {
        return order;
    }

    /**
     * SETTER 순서
     *
     * @param order 순서
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * GETTER 요약
     *
     * @return 요약
     */
    public String getSummary() {
        return summary;
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
     * GETTER 사용자 아이디
     *
     * @return 사용자 아이디
     */
    public String getUserId() {
        return userId;
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
        return created;
    }

    /**
     * SETTER 생성일
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
        return lastModified;
    }

    /**
     * SETTER 수정일
     *
     * @param lastModified 수정일
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * GETTER 처리 형태
     *
     * @return 처리 형태
     */
    public String getProcType() {
        return procType;
    }

    /**
     * SETTER 처리 형태
     *
     * @param procType 처리 형태
     */
    public void setProcType(String procType) {
        this.procType = procType;
    }

    /**
     * GETTER 페이지 번호
     *
     * @return 페이지 번호
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * SETTER 페이지 번호
     *
     * @param pageNo 페이지 번호
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * GETTER 페이지 크기
     *
     * @return 페이지 크기
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * SETTER 페이지 크기
     *
     * @param pageSize 페이지 크기
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * GETTER 검색 형태
     *
     * @return 검색 형태
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * SETTER 검색 형태
     *
     * @param searchType 검색 형태
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     * GETTER 검색어
     *
     * @return 검색어
     */
    public String getSearchKeyword() {
        return searchKeyword;
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
     * GETTER 요청 등록/수정/삭제
     *
     * @return 요청 등록/수정/삭제
     */
    public String getReqCud() {
        return reqCud;
    }

    /**
     * SETTER 요청 등록/수정/삭제
     *
     * @param reqCud 요청 등록/수정/삭제
     */
    public void setReqCud(String reqCud) {
        this.reqCud = reqCud;
    }

    /**
     * GETTER 공통코드 목록
     *
     * @return 공통코드 목록
     */
    public List<CommonCode> getCommonCodeList() {
        return commonCodeList;
    }

    /**
     * SETTER 공통코드 목록
     *
     * @param commonCodeList 공통코드 목록
     */
    public void setCommonCodeList(List<CommonCode> commonCodeList) {
        this.commonCodeList = commonCodeList;
    }



    @Override
    public String toString() {
        return "CommonCode{" +
                "id='" + id + '\'' +
                ", no='" + no + '\'' +
                ", orgId='" + orgId + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", orgKey='" + orgKey + '\'' +
                ", value='" + value + '\'' +
                ", groupId='" + groupId + '\'' +
                ", useYn='" + useYn + '\'' +
                ", order=" + order +
                ", summary='" + summary + '\'' +
                ", userId='" + userId + '\'' +
                ", created='" + created + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", procType='" + procType + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", searchType='" + searchType + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", reqCud='" + reqCud + '\'' +
                ", commonCodeList=" + commonCodeList +
                '}';
    }
}
