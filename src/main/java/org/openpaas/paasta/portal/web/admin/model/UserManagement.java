package org.openpaas.paasta.portal.web.admin.model;

/**
 * 사용자 관리(모델클래스)
 * 사용자 관리 정보를 가지고 있는 데이터 모델 bean 클래스
 * Json 어노테이션을 사용해서 JSON 형태로 제공
 *
 * @version 1.0
 * @since 2016.08.31 최초작성
 */
public class UserManagement {

    private String userId;
    private String userGuid;
    private String status;
    private String statusValue;
    private String tellPhone;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String userName;
    private String adminYn;
    private String refreshToken;
    private int pageNo;
    private int pageSize;
    private String searchKeyword;
    private int totalCount;
    private String active;


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
     * GETTER 사용자 GUID
     *
     * @return 사용자 GUID
     */
    public String getUserGuid() {
        return userGuid;
    }

    /**
     * SETTER 사용자 GUID
     *
     * @param userGuid 사용자 GUID
     */
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    /**
     * GETTER 상태
     *
     * @return 상태
     */
    public String getStatus() {
        return status;
    }

    /**
     * SETTER 상태
     *
     * @param status 상태
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * GETTER 상태값
     *
     * @return 상태값
     */
    public String getStatusValue() {
        return statusValue;
    }

    /**
     * SETTER 상태값
     *
     * @param statusValue 상태값
     */
    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    /**
     * GETTER 전화번호
     *
     * @return 전화번호
     */
    public String getTellPhone() {
        return tellPhone;
    }

    /**
     * SETTER 전화번호
     *
     * @param tellPhone 전화번호
     */
    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }

    /**
     * GETTER 우편번호
     *
     * @return 우편번호
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * SETTER 우편번호
     *
     * @param zipCode 우편번호
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * GETTER 주소
     *
     * @return 주소
     */
    public String getAddress() {
        return address;
    }

    /**
     * SETTER 주소
     *
     * @param address 주소
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * GETTER 주소 상세
     *
     * @return 주소 상세
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * SETTER 주소 상세
     *
     * @param addressDetail 주소 상세
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    /**
     * GETTER 유저명
     *
     * @return 유저명
     */
    public String getUserName() {
        return userName;
    }

    /**
     * SETTER 유저명
     *
     * @param userName 유저명
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * GETTER 관리자 유무
     *
     * @return 관리자 유무
     */
    public String getAdminYn() {
        return adminYn;
    }

    /**
     * SETTER 관리자 유무
     *
     * @param adminYn 관리자 유무
     */
    public void setAdminYn(String adminYn) {
        this.adminYn = adminYn;
    }

    /**
     * GETTER 갱신 토큰
     *
     * @return 갱신 토큰
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * SETTER 갱신 토큰
     *
     * @param refreshToken 갱신 토큰
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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
     * GETTER 전체 개수
     *
     * @return 전체 개수
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * SETTER 전체 개수
     *
     * @param totalCount 전체 개수
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * GETTER 로그인 가능 유무
     *
     * @return 로그인 가능 유무
     */
    public String getActive() {
        return active;
    }

    /**
     * SETTER 로그인 가능 유무
     *
     * @param active 로그인 가능 유무
     */
    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserManagement{" +
                "userId='" + userId + '\'' +
                ", userGuid='" + userGuid + '\'' +
                ", status='" + status + '\'' +
                ", statusValue='" + statusValue + '\'' +
                ", tellPhone='" + tellPhone + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", userName='" + userName + '\'' +
                ", adminYn='" + adminYn + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", totalCount=" + totalCount +
                ", active=" + active +
                '}';
    }
}
