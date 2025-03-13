package com.example.findpublicwifiservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WiFiDTO {
    private String mgrNo;
    private String district;
    private String wifiName;
    private String address1;
    private String address2;
    private String installFloor;
    private String installType;
    private String installAgency;
    private String serviceType;
    private String connectionType;
    private int installYear;
    private String indoorOutdoor;
    private String remarks;
    private double latitude;
    private double longitude;
    private String workDatetime;

    @Builder
    public WiFiDTO(String mgrNo, String district, String wifiName, String address1, String address2,
                   String installFloor, String installType, String installAgency, String serviceType,
                   String connectionType, int installYear, String indoorOutdoor, String remarks,
                   double latitude, double longitude, String workDatetime) {
        this.mgrNo = mgrNo;
        this.district = district;
        this.wifiName = wifiName;
        this.address1 = address1;
        this.address2 = address2;
        this.installFloor = installFloor;
        this.installType = installType;
        this.installAgency = installAgency;
        this.serviceType = serviceType;
        this.connectionType = connectionType;
        this.installYear = installYear;
        this.indoorOutdoor = indoorOutdoor;
        this.remarks = remarks;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workDatetime = workDatetime;
    }
}
