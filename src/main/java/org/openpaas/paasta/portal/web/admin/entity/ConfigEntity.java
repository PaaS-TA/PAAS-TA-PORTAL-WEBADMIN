package org.openpaas.paasta.portal.web.admin.entity;

import javax.persistence.*;

/**
 * 구성 모델
 * 구성 정보를 가지고 있는 데이터 모델 bean 클래스
 *
 * @version 2.0
 * @since 2019.05.15 최초작성
 */

@Entity
@Table(name = "infra_config")
public class ConfigEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int key;

    @Column(name = "name")
    private String name;

    @Column(name = "authorization")
    private String authorization;

    @Column(name = "api_uri")
    private String apiUri;

    @Column(name = "uaa_uri")
    private String uaaUri;

    @Column(name = "description")
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

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }

    public String getUaaUri() {
        return uaaUri;
    }

    public void setUaaUri(String uaaUri) {
        this.uaaUri = uaaUri;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ConfigEntity{" + "key=" + key + ", name='" + name + '\'' + ", authorization='" + authorization + '\'' + ", apiUri='" + apiUri + '\'' + ", uaaUri='" + uaaUri + '\'' + ", desc='" + desc + '\'' + '}';
    }
}
