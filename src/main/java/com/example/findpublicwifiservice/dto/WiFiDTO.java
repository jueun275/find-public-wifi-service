package com.example.findpublicwifiservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
@Setter
@Getter
public class WiFiDto {
    private double distance = 0.0;
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
    public WiFiDto(String mgrNo, String district, String wifiName, String address1, String address2,
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

    public static WiFiDto fromResultSet(ResultSet rs) throws SQLException {
        return WiFiDto.builder()
            .mgrNo(rs.getString("X_SWIFI_MGR_NO"))
            .district(rs.getString("X_SWIFI_WRD_OF"))
            .wifiName(rs.getString("X_SWIFI_MAIN_NM"))
            .address1(rs.getString("X_SWIFI_ADRES1"))
            .address2(rs.getString("X_SWIFI_ADRES2"))
            .installFloor(rs.getString("X_SWIFI_INSTL_FLOOR"))
            .installType(rs.getString("X_SWIFI_INSTL_TY"))
            .installAgency(rs.getString("X_SWIFI_INSTL_MBY"))
            .serviceType(rs.getString("X_SWIFI_SVC_SE"))
            .connectionType(rs.getString("X_SWIFI_CMCWR"))
            .installYear(rs.getInt("X_SWIFI_CNSTC_YEAR"))
            .indoorOutdoor(rs.getString("X_SWIFI_INOUT_DOOR"))
            .remarks(rs.getString("X_SWIFI_REMARS3"))
            .latitude(rs.getDouble("LAT"))
            .longitude(rs.getDouble("LNT"))
            .workDatetime(rs.getString("WORK_DTTM"))
            .build();
    }


}
