package org.openpaas.paasta.portal.web.admin.model;

import java.util.List;
import java.util.Map;

/**
 * 앱 모델
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.5.30 최초작성
 */
public class App {
    private int appInstanceIndex;
    private String guid;
    private String name;
    private String newName;
    private String orgName;
    private String spaceName;
    private String serviceName;
    private String serviceParam;
    private Map<String, String> environment;
    private List<String> urls;
    private List<String> services;
    private int instances;
    private int memory;
    private int diskQuota;
    private String state;
    private String createdAt;
    private String updatedAt;
    private int totalUserCount;
    private String buildPack;
    private String stackName;
    private Staging staging;
    private String host;
    private String domainName;

    public int getAppInstanceIndex() {
        return appInstanceIndex;
    }

    public void setAppInstanceIndex(int appInstanceIndex) {
        this.appInstanceIndex = appInstanceIndex;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets memory.
     *
     * @return the memory
     */
    public int getMemory() {
        return memory;
    }

    /**
     * Sets memory.
     *
     * @param memory the memory
     */
    public void setMemory(int memory) {
        this.memory = memory;
    }

    /**
     * Gets instances.
     *
     * @return the instances
     */
    public int getInstances() {
        return instances;
    }

    /**
     * Sets instances.
     *
     * @param instances the instances
     */
    public void setInstances(int instances) {
        this.instances = instances;
    }

    /**
     * Gets disk quota.
     *
     * @return the disk quota
     */
    public int getDiskQuota() {
        return diskQuota;
    }

    /**
     * Sets disk quota.
     *
     * @param diskQuota the disk quota
     */
    public void setDiskQuota(int diskQuota) {
        this.diskQuota = diskQuota;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getTotalUserCount() {
        return totalUserCount;
    }

    public void setTotalUserCount(int totalUserCount) {
        this.totalUserCount = totalUserCount;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public Staging getStaging() {
        return staging;
    }

    public void setStaging(Staging staging) {
        this.staging = staging;
    }

    public String getBuildPack() {
        return buildPack;
    }

    public void setBuildPack(String buildPack) {
        this.buildPack = buildPack;
    }

    public String getStackName() {
        return stackName;
    }

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Map<String, String> getEnvironment() {
        return environment;
    }

    public void setEnvironment(Map<String, String> environment) {
        this.environment = environment;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getServiceParam() {
        return serviceParam;
    }

    public void setServiceParam(String serviceParam) {
        this.serviceParam = serviceParam;
    }

    public class Staging {
        private String detectedBuildpack;
        private String stack;

        public String getDetectedBuildpack() {
            return detectedBuildpack;
        }

        public void setDetectedBuildpack(String detectedBuildpack) {
            this.detectedBuildpack = detectedBuildpack;
            setBuildPack(detectedBuildpack);
        }

        public String getStack() {
            return stack;
        }

        public void setStack(String stack) {
            this.stack = stack;
            setStackName(stack);
        }
    }
}
