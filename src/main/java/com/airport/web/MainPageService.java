package com.airport.web;

/**
 * Created by Vic on 19.01.2015.
 */
public class MainPageService {
    private String url;
    private String icon;
    private Boolean isForAdmin;
    private String nameService;

    public Boolean getIsForAdmin() {
        return isForAdmin;
    }

    public void setIsForAdmin(Boolean isForAdmin) {
        this.isForAdmin = isForAdmin;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }
}
