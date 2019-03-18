package org.openpaas.paasta.portal.web.admin.model;

/**
 * 관리자 메인(모델클래스)
 *
 * @version 1.0
 * @since 2016.09.08 최초작성
 */
public class AdminMain {

    private int organizationId;
    private String organizationName;
    private int spaceId;
    private String spaceName;
    private int organizationCount;
    private int spaceCount;
    private int applicationCount;
    private int userCount;

    /**
     * GETTER 조직 아이디
     *
     * @return 조직 아이디
     */
    public int getOrganizationId() {
        return organizationId;
    }

    /**
     * SETTER 조직 아이디
     *
     * @param organizationId 조직 아이디
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * GETTER 조직명
     *
     * @return 조직명
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * SETTER 조직명
     *
     * @param organizationName 조직명
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * GETTER 공간 아이디
     *
     * @return 공간 아이디
     */
    public int getSpaceId() {
        return spaceId;
    }

    /**
     * SETTER 공간 아이디
     *
     * @param spaceId 공간 아이디
     */
    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    /**
     * GETTER 공간명
     *
     * @return 공간명
     */
    public String getSpaceName() {
        return spaceName;
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
     * GETTER 조직 개수
     *
     * @return 조직 개수
     */
    public int getOrganizationCount() {
        return organizationCount;
    }

    /**
     * SETTER 조직 개수
     *
     * @param organizationCount 조직 개수
     */
    public void setOrganizationCount(int organizationCount) {
        this.organizationCount = organizationCount;
    }

    /**
     * GETTER 공간 개수
     *
     * @return 공간 개수
     */
    public int getSpaceCount() {
        return spaceCount;
    }

    /**
     * SETTER 공간 개수
     *
     * @param spaceCount 공간 개수
     */
    public void setSpaceCount(int spaceCount) {
        this.spaceCount = spaceCount;
    }

    /**
     * GETTER 애플리케이션 개수
     *
     * @return 애플리케이션 개수
     */
    public int getApplicationCount() {
        return applicationCount;
    }

    /**
     * SETTER 애플리케이션 개수
     *
     * @param applicationCount 애플리케이션 개수
     */
    public void setApplicationCount(int applicationCount) {
        this.applicationCount = applicationCount;
    }

    /**
     * GETTER 사용자 수
     *
     * @return 사용자 수
     */
    public int getUserCount() {
        return userCount;
    }

    /**
     * SETTER 사용자 수
     *
     * @param userCount 사용자 수
     */
    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    @Override
    public String toString() {
        return "AdminMain{" +
                "organizationId=" + organizationId +
                ", organizationName='" + organizationName + '\'' +
                ", spaceId=" + spaceId +
                ", spaceName='" + spaceName + '\'' +
                ", organizationCount=" + organizationCount +
                ", spaceCount=" + spaceCount +
                ", applicationCount=" + applicationCount +
                ", userCount=" + userCount +
                '}';
    }
}
