package com.vugido.homeservices.models.homepage;

import java.util.List;

public class ServiceDetailsModel {

    int service_id;
    String service;

    public ServiceDetailsModel(int service_id, String service, boolean checked) {
        this.service_id = service_id;
        this.service = service;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    boolean checked;

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }


}
