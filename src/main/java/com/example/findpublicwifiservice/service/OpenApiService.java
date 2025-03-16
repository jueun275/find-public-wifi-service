package com.example.findpublicwifiservice.service;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import com.example.findpublicwifiservice.dao.WiFiDao;
import com.example.findpublicwifiservice.model.WiFiModel;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OpenApiService {
    private static final int PAGE_SIZE = 1000; // OpenAPI에서 한 번에 조회할 수 있는 최대 데이터 수
    String accessKey;
    private OkHttpClient client;
    private WiFiDao wifiDao;


    public OpenApiService(ServletContext context){
        this.wifiDao = new WiFiDao();
        this.client = new OkHttpClient();
        this.wifiDao = new WiFiDao();
        accessKey = context.getInitParameter("openapi.accessKey");
    }

    public int processAllWifiData() {
        int totalRecords = 0;
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            totalRecords = getTotalRecords();
            int startNum = 1;
            int endNum = PAGE_SIZE; // 끝 번호 (한 번에 조회할 최대 개수)

            // 전체 데이터를 페이지 단위로 나누어 처리
            while (startNum <= totalRecords) {
                Optional<List<WiFiModel>> wifiData = getWifiData(startNum, endNum);
                insertDataToDatabase(wifiData.get());
                startNum += PAGE_SIZE; // 페이지 번호를 증가
                endNum = Math.min(startNum + PAGE_SIZE - 1, totalRecords); // 마지막 데이터 번호 설정
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외가 발생하면 로그 출력
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        return totalRecords;
    }


    private Optional<List<WiFiModel>> getWifiData(int startNum, int endNum) {
        String url = buildUrl(startNum, endNum);
        try {
            Request wifiRequest = new Request.Builder().url(url).build();
            Response response = client.newCall(wifiRequest).execute();
            if (response == null) return Optional.empty(); // 응답이 null인 경우 빈 Optional 반환

            Gson gson = new Gson();
            assert response.body() != null;
            String responseBody = response.body().string();
            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
            JsonArray rows = jsonResponse.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");

            // 각 WiFi 데이터를 WiFiModel 객체로 변환하여 리스트에 추가합니다.
            List<WiFiModel> wifiList = new ArrayList<>();
            for (JsonElement element : rows) {
                wifiList.add(parseWifiData(element.getAsJsonObject()));
            }
            return Optional.of(wifiList);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private int getTotalRecords() throws IOException {
        String url = "http://openapi.seoul.go.kr:8088/" + this.accessKey + "/json/TbPublicWifiInfo/1/1";

        Request wifiRequest = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(wifiRequest).execute();
            if (response.body() == null) {
                System.out.println("Response body is empty");
                return -1;
            }
            String responseBody = response.body().string();
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);

            int listTotalCount = jsonResponse.getAsJsonObject("TbPublicWifiInfo")
                .get("list_total_count")
                .getAsInt();

            return listTotalCount;

        } catch (Exception e) {
            e.printStackTrace();
            return -1; // 예외 발생 시 오류 표시
        }
    }


    private void insertDataToDatabase(List<WiFiModel> wifiData) throws SQLException, ClassNotFoundException {
        wifiDao.insert(wifiData);
    }

    private WiFiModel parseWifiData(JsonObject wifiObj) {
        return WiFiModel.builder()
            .mgrNo(wifiObj.get("X_SWIFI_MGR_NO").getAsString())
            .district(wifiObj.get("X_SWIFI_WRDOFC").getAsString())
            .wifiName(wifiObj.get("X_SWIFI_MAIN_NM").getAsString())
            .address1(wifiObj.get("X_SWIFI_ADRES1").getAsString())
            .address2(wifiObj.get("X_SWIFI_ADRES2").getAsString())
            .installFloor(wifiObj.get("X_SWIFI_INSTL_FLOOR").getAsString())
            .installType(wifiObj.get("X_SWIFI_INSTL_TY").getAsString())
            .installAgency(wifiObj.get("X_SWIFI_INSTL_MBY").getAsString())
            .serviceType(wifiObj.get("X_SWIFI_SVC_SE").getAsString())
            .connectionType(wifiObj.get("X_SWIFI_CMCWR").getAsString())
            .installYear(wifiObj.get("X_SWIFI_CNSTC_YEAR").getAsInt())
            .indoorOutdoor(wifiObj.get("X_SWIFI_INOUT_DOOR").getAsString())
            .remarks(wifiObj.get("X_SWIFI_REMARS3").getAsString())
            .latitude(wifiObj.get("LAT").getAsDouble())
            .longitude(wifiObj.get("LNT").getAsDouble())
            .workDatetime(wifiObj.get("WORK_DTTM").getAsString())
            .build();
    }

    private String buildUrl(int startNum, int endNum) {
        return  "http://openapi.seoul.go.kr:8088/" + this.accessKey + "/json/TbPublicWifiInfo/" +startNum + "/" + endNum;
    }

    public String getAccessKeyForTesting(){
        return this.accessKey;
    }

}
