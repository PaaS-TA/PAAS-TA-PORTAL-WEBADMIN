package org.openpaas.paasta.portal.web.admin.model;

import java.util.UUID;

/**
 * 서비스브로커 모델
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.5.30 최초작성
 */
public class ServiceBroker {
    private String name;
    private String newName;
    private String url;
    private String username;
    private String password;
    private UUID guid;

    private boolean publiclyVisible;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public boolean getPubliclyVisible() {
        return publiclyVisible;
    }

    public void setPubliclyVisible(boolean publiclyVisible) {
        this.publiclyVisible = publiclyVisible;
    }


}
