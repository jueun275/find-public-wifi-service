package com.example.findpublicwifiservice.service;

import com.example.findpublicwifiservice.config.DatabaseConnection;
import com.example.findpublicwifiservice.dao.WiFiDao;
import com.example.findpublicwifiservice.model.WiFiModel;
import com.google.gson.*;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiFiService {
    private static final String API_URL = "http://openapi.seoul.go.kr:8088/736a78764866666636316d58525554/json/TbPublicWifiInfo/";
    private static final int PAGE_SIZE = 1000; // OpenAPI에서 한 번에 조회할 수 있는 최대 데이터 수
    private OkHttpClient client; // HTTP 클라이언트 객체
    private WiFiDao wifiDao; // WiFi 데이터 처리 DAO 객체

    // 생성자: OkHttpClient와 WiFiDao를 초기화
    public WiFiService() {
        this.client = new OkHttpClient(); // OkHttpClient 초기화
        this.wifiDao = new WiFiDao(); // WiFiDao 객체 생성
    }

    public void processAllWifiData() {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            int totalRecords = getTotalRecords(); // 전체 데이터 개수 조회
            int startNum = 1; // 시작 번호
            int endNum = PAGE_SIZE; // 끝 번호 (한 번에 조회할 최대 개수)

            // 전체 데이터를 페이지 단위로 나누어 처리
            while (startNum <= totalRecords) {
                Optional<List<WiFiModel>> wifiData = getWifiData(startNum, endNum);
//                wifiData.ifPresent(this::insertDataToDatabase); // 데이터를 DB에 삽입
                insertDataToDatabase(wifiData.get());
                startNum += PAGE_SIZE; // 페이지 번호를 증가
                endNum = Math.min(startNum + PAGE_SIZE - 1, totalRecords); // 마지막 데이터 번호 설정
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외가 발생하면 로그 출력
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<List<WiFiModel>> getWifiData(int startNum, int endNum) {
        String url = buildUrl(startNum, endNum); // OpenAPI 요청 URL을 생성합니다.
        try {
            // HTTP 요청을 보내고 응답을 받습니다.
            Request wifiRequest = new Request.Builder().url(url).build();
            Response response = client.newCall(wifiRequest).execute();
            if (response == null) return Optional.empty(); // 응답이 null인 경우 빈 Optional 반환

            // Gson을 이용해 JSON 응답을 파싱합니다.
            Gson gson = new Gson();
            assert response.body() != null;
//            ResponseBody responseBody = response.body();
            String responseBody = response.body().string();
            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
//            JsonElement jsonElement = JsonParser.parseString(responseBody.string());
            JsonArray rows = jsonResponse.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
//            JsonArray rows = responseBody.getAsJsonObject().get("TbPublicWifiInfo")
//                .getAsJsonObject().get("row")
//                .getAsJsonArray();

            // 각 WiFi 데이터를 WiFiModel 객체로 변환하여 리스트에 추가합니다.
            List<WiFiModel> wifiList = new ArrayList<>();
            for (JsonElement element : rows) {
                wifiList.add(parseWifiData(element.getAsJsonObject()));
            }
            return Optional.of(wifiList); // WiFi 모델 리스트를 반환
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty(); // 예외가 발생하면 빈 Optional 반환
        }
    }

    private int getTotalRecords() {
        // 전체 데이터 개수를 받아오는 OpenAPI 요청을 실행합니다.
        // 예시로 14497개의 데이터가 있다고 가정합니다.
        return 14497; // 실제 API로부터 값을 받아와야 함
    }

    private void insertDataToDatabase(List<WiFiModel> wifiData) throws SQLException, ClassNotFoundException {
        wifiDao.insert(wifiData); // WiFiDao를 사용하여 DB에 데이터 삽입
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
            .build(); // Builder 패턴을 사용하여 WiFiModel 객체 생성
    }

    private String buildUrl(int startNum, int endNum) {
        // API 요청 URL을 생성합니다.
        return  API_URL+ startNum + "/" + endNum;
    }
}
