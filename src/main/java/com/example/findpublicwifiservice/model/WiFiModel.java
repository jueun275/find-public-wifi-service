package com.example.findpublicwifiservice.model;


import com.example.findpublicwifiservice.dto.WiFiDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WiFiModel {
    private String mgrNo;            // 관리번호 (X_SWIFI_MGR_NO)
    private String district;         // 자치구 (X_SWIFI_WRDOFC)
    private String wifiName;         // 와이파이명 (X_SWIFI_MAIN_NM)
    private String address1;         // 도로명 주소 (X_SWIFI_ADRES1)
    private String address2;         // 상세 주소 (X_SWIFI_ADRES2)
    private String installFloor;     // 설치 층수 (X_SWIFI_INSTL_FLOOR)
    private String installType;      // 설치 유형 (X_SWIFI_INSTL_TY)
    private String installAgency;    // 설치 기관 (X_SWIFI_INSTL_MBY)
    private String serviceType;      // 서비스 구분 (X_SWIFI_SVC_SE)
    private String connectionType;   // 망 종류 (X_SWIFI_CMCWR)
    private int installYear;         // 설치년도 (X_SWIFI_CNSTC_YEAR)
    private String indoorOutdoor;    // 실내/실외 구분 (X_SWIFI_INOUT_DOOR)
    private String remarks;          // 비고 (X_SWIFI_REMARS3)
    private double latitude;         // 위도 (LAT)
    private double longitude;        // 경도 (LNT)
    private String workDatetime;     // 작업 일시 (WORK_DTTM)

    @Builder
    public WiFiModel(String mgrNo, String district, String wifiName, String address1, String address2,
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

    public WiFiDTO toDTO() {
        return WiFiDTO.builder()
            .mgrNo(mgrNo)
            .district(district)
            .wifiName(wifiName)
            .address1(address1)
            .address2(address2)
            .installFloor(installFloor)
            .installType(installType)
            .installAgency(installAgency)
            .serviceType(serviceType)
            .connectionType(connectionType)
            .installYear(installYear)
            .indoorOutdoor(indoorOutdoor)
            .remarks(remarks)
            .latitude(latitude)
            .longitude(longitude)
            .workDatetime(workDatetime)
            .build();
    }
}
