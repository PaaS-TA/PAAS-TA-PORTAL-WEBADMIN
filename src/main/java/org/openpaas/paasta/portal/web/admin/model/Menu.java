package org.openpaas.paasta.portal.web.admin.model;

/**
 * 메뉴(모델클래스)
 * 메뉴 정보를 가지고 있는 데이터 모델 bean 클래스
 * Json 어노테이션을 사용해서 JSON 형태로 제공
 *
 * @version 1.0
 * @since 2016.09.29 최초작성
 */
public class Menu {

    private int no;
    private int id;
    private int parentNo;
    private int sortNo;
    private String menuName;
    private String text;
    private String menuPath;
    private String imagePath;
    private String openWindowYn;
    private String loginYn;
    private String useYn;
    private String description;
    private String userId;
    private String created;
    private String lastModified;
    private int childCount;
    private int maxNo;

    /**
     * GETTER 번호
     *
     * @return 번호
     */
    public int getNo() {
        return no;
    }

    /**
     * SETTER 번호
     *
     * @param no 번호
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * GETTER 아이디
     *
     * @return 아이디
     */
    public int getId() {
        return id;
    }

    /**
     * SETTER 아이디
     *
     * @param id 아이디
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * GETTER 상위 번호
     *
     * @return 상위 번호
     */
    public int getParentNo() {
        return parentNo;
    }

    /**
     * SETTER 상위 번호
     *
     * @param parentNo 상위 번호
     */
    public void setParentNo(int parentNo) {
        this.parentNo = parentNo;
    }

    /**
     * GETTER 정령 번호
     *
     * @return 정령 번호
     */
    public int getSortNo() {
        return sortNo;
    }

    /**
     * SETTER 정령 번호
     *
     * @param sortNo 정령 번호
     */
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * GETTER 메뉴명
     *
     * @return 메뉴명
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * SETTER 메뉴명
     *
     * @param menuName 메뉴명
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * GETTER 문자
     *
     * @return 문자
     */
    public String getText() {
        return text;
    }

    /**
     * SETTER 문자
     *
     * @param text 문자
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * GETTER 메뉴 경로
     *
     * @return 메뉴 경로
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * SETTER 메뉴 경로
     *
     * @param menuPath 메뉴 경로
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    /**
     * GETTER 이미지 경로
     *
     * @return 이미지 경로
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * SETTER 이미지 경로
     *
     * @param imagePath 이미지 경로
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * GETTER 새창 유무
     *
     * @return 새창 유무
     */
    public String getOpenWindowYn() {
        return openWindowYn;
    }

    /**
     * SETTER 새창 유무
     *
     * @param openWindowYn 새창 유무
     */
    public void setOpenWindowYn(String openWindowYn) {
        this.openWindowYn = openWindowYn;
    }

    /**
     * GETTER 로그인 유무
     *
     * @return 로그인 유무
     */
    public String getLoginYn() {
        return loginYn;
    }

    /**
     * SETTER 로그인 유무
     *
     * @param loginYn 로그인 유무
     */
    public void setLoginYn(String loginYn) {
        this.loginYn = loginYn;
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
     * GETTER 설명
     *
     * @return 설명
     */
    public String getDescription() {
        return description;
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
     * GETTER 하위 개수
     *
     * @return 하위 개수
     */
    public int getChildCount() {
        return childCount;
    }

    /**
     * SETTER 하위 개수
     *
     * @param childCount 하위 개수
     */
    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    /**
     * GETTER 최대값
     *
     * @return 최대값
     */
    public int getMaxNo() {
        return maxNo;
    }

    /**
     * SETTER 최대값
     *
     * @param maxNo 최대값
     */
    public void setMaxNo(int maxNo) {
        this.maxNo = maxNo;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "no=" + no +
                ", id=" + id +
                ", parentNo=" + parentNo +
                ", sortNo=" + sortNo +
                ", menuName='" + menuName + '\'' +
                ", text='" + text + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", openWindowYn='" + openWindowYn + '\'' +
                ", loginYn='" + loginYn + '\'' +
                ", useYn='" + useYn + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                ", created='" + created + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", childCount=" + childCount +
                ", maxNo=" + maxNo +
                '}';
    }
}
