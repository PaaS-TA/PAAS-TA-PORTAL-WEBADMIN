package org.openpaas.paasta.portal.web.admin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 공간 모델
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.5.30 최초작성
 */
public class Space {
    private String orgName;
    private String spaceName;
    private String newSpaceName;

    private UUID guid;
    private String name;

    private int serviceCount = 0;

    private int appCount = 0;

    private int appCountStarted = 0;
    private int appCountStopped = 0;
    private int appCountCrashed = 0;

    private int memDevTotal;

    private int memProdTotal;

    private int memoryUsage;
    private int memoryLimit;

    private List<App> apps = new ArrayList<App>();

    private List<Service> services = new ArrayList<Service>();

    public Space() {
        //empty
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getNewSpaceName() {
        return newSpaceName;
    }

    public void setNewSpaceName(String newSpaceName) {
        this.newSpaceName = newSpaceName;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(int serviceCount) {
        this.serviceCount = serviceCount;
    }

    public int getAppCount() {
        return appCount;
    }

    public void setAppCount(int appCount) {
        this.appCount = appCount;
    }

    public int getAppCountStarted() {
        return appCountStarted;
    }

    public void setAppCountStarted(int appCountStarted) {
        this.appCountStarted = appCountStarted;
    }

    public int getAppCountStopped() {
        return appCountStopped;
    }

    public void setAppCountStopped(int appCountStopped) {
        this.appCountStopped = appCountStopped;
    }

    public int getAppCountCrashed() {
        return appCountCrashed;
    }

    public void setAppCountCrashed(int appCountCrashed) {
        this.appCountCrashed = appCountCrashed;
    }

    public int getMemDevTotal() {
        return memDevTotal;
    }

    public void setMemDevTotal(int memDevTotal) {
        this.memDevTotal = memDevTotal;
    }

    public int getMemProdTotal() {
        return memProdTotal;
    }

    public void setMemProdTotal(int memProdTotal) {
        this.memProdTotal = memProdTotal;
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public int getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(int memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
