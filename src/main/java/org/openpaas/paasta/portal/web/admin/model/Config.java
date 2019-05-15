package org.openpaas.paasta.portal.web.admin.model;

/**
 * 구성 모델
 * 구성 정보를 가지고 있는 데이터 모델 bean 클래스
 *
 * @version 2.0
 * @since 2019.05.15 최초작성
 */
public class Config {
    private int key;
    private String name;
    private String apiUrl;
    private String uaaUrl;
    private String desc;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getUaaUrl() {
        return uaaUrl;
    }

    public void setUaaUrl(String uaaUrl) {
        this.uaaUrl = uaaUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Config{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", uaaUrl='" + uaaUrl + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }


}
