package org.openpaas.paasta.portal.web.admin.model;

/**
 * 쿼터 모델
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.5.30 최초작성
 */
public class Quota extends Entity {

    private boolean nonBasicServicesAllowed = false;
    private int totalServices;
    private int totalRoutes;
    private long memoryLimit;

    public Quota() {
        //empty
    }

    public Quota(Meta meta, String name, boolean nonBasicServicesAllowed,
                 int totalServices, int totalRoutes, long memoryLimit) {
        super(meta, name);
        this.totalServices = totalServices;
        this.totalRoutes = totalRoutes;
        this.memoryLimit = memoryLimit;
        this.nonBasicServicesAllowed = nonBasicServicesAllowed;

    }

    /**
     * Default value :"memory_limit":0,"total_routes":0,"total_services":0,"non_basic_services_allowed":false
     *
     * @param meta
     * @param name
     */
    public Quota(Meta meta, String name) {
        super(meta, name);
    }

    public int getTotalServices() {
        return totalServices;
    }

    public void setTotalServices(int totalServices) {
        this.totalServices = totalServices;
    }

    public int getTotalRoutes() {
        return totalRoutes;
    }

    public void setTotalRoutes(int totalRoutes) {
        this.totalRoutes = totalRoutes;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public boolean isNonBasicServicesAllowed() {
        return nonBasicServicesAllowed;
    }

    public void setNonBasicServicesAllowed(boolean nonBasicServicesAllowed) {
        this.nonBasicServicesAllowed = nonBasicServicesAllowed;
    }


}