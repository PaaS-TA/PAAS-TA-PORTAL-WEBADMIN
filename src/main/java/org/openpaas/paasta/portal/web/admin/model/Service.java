package org.openpaas.paasta.portal.web.admin.model;

import java.util.UUID;

/**
 * 서비스 모델
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.5.30 최초작성
 */
public class Service {
    private String name;
    private String newName;

    private String orgName;
    private String spaceName;

    private UUID guid;

    private String servicePlanName;

    private String serviceLabel;

    private int boundAppCount;

    private ServicePlan servicePlan;

    private String serviceName;

    public int getBoundAppCount() {
        return boundAppCount;
    }

    public void setBoundAppCount(int boundAppCount) {
        this.boundAppCount = boundAppCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServicePlan getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(ServicePlan servicePlan) {
        this.servicePlan = servicePlan;
    }

    public String getServicePlanName() {
        return servicePlanName;
    }

    public void setServicePlanName(String servicePlanName) {
        this.servicePlanName = servicePlanName;
    }

    public String getServiceLabel() {
        return serviceLabel;
    }

    public void setServiceLabel(String serviceLabel) {
        this.serviceLabel = serviceLabel;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public static class ServicePlan {

        private String name;

        private ServiceInfo service;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ServiceInfo getService() {
            return service;
        }

        public void setService(ServiceInfo service) {
            this.service = service;
        }

        public class ServiceInfo {

            private String label;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }

    }
}
