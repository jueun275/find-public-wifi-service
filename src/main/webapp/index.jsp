<%@ page import="com.example.findpublicwifiservice.dto.WiFiDto" %>
<%@ page import="java.util.List" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="layout/header.jsp" %>
<h2 style="margin: 20px">와이파이 정보구하기</h2>
<%@ include file ="layout/nav.jsp" %>
<div class="col-md-12">
    <div class="row">
        <div class="col-md-6">
            <form action="wifi" method="GET">
                <input type="hidden" name="action" value="getWiFiList">
                <label for="lat">LAT:</label>
                <input type="text" id="lat" name="lat" required>

                <label for="lnt">LNT</label>
                <input type="text" id="lnt" name="lnt" required>

                <button class="btn btn-primary btn-sm" type="button" onclick="getLocation();" >내 위치 가져오기</button>
                <button class="btn btn-primary btn-sm" type="submit">근처 WIPI 정보 보기</button>
            </form>

        </div>
    </div>
    <br>
    <table class="table table-horizontal table-bordered">
        <thead class="thead-strong" style="background-color: #f5f5f5;">
        <tr>
            <th>거리</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty sessionScope.wifiList}">
                <c:forEach var="wifi" items="${sessionScope.wifiList}" varStatus="status">
                    <tr>
                        <td>${wifi.distance}</td>
                        <td>${wifi.mgrNo}</td>
                        <td>${wifi.district}</td>
                        <td><a href="${pageContext.request.contextPath}/wifi?action=getWiFiDetail&mgrNo=${wifi.mgrNo}"/>${wifi.wifiName}</td>
                        <td>${wifi.address1}</td>
                        <td>${wifi.address2}</td>
                        <td>${wifi.installFloor}</td>
                        <td>${wifi.installType}</td>
                        <td>${wifi.installAgency}</td>
                        <td>${wifi.serviceType}</td>
                        <td>${wifi.connectionType}</td>
                        <td>${wifi.installYear}</td>
                        <td>${wifi.indoorOutdoor}</td>
                        <td>${wifi.remarks}</td>
                        <td>${wifi.latitude}</td>
                        <td>${wifi.longitude}</td>
                        <td>${wifi.workDatetime}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="18">데이터를 저장하고 실행해주세요</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
<script>
    function getLocation() {
        // Geolocation API가 지원되는지 확인
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                // 위도와 경도를 폼에 채워넣기
                var latitude = position.coords.latitude;  // 위도
                var longitude = position.coords.longitude;  // 경도

                // X, Y 좌표 입력 필드에 위도와 경도 값 설정
                document.getElementById('lat').value = latitude;
                document.getElementById('lnt').value = longitude;
                // 세션 스토리지에 저장
                sessionStorage.setItem('lat', latitude);
                sessionStorage.setItem('lnt', longitude);
            }, function(error) {
                // 위치를 가져오는 데 실패한 경우
                alert("위치 정보를 가져올 수 없습니다: " + error.message);
            });
        } else {
            alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
        }
    }

    window.onload = function() {
        // 페이지 로드 시 세션 스토리지에 저장된 값이 있는지 확인
        var savedLat = sessionStorage.getItem('lat');
        var savedLnt = sessionStorage.getItem('lnt');

        // 만약 저장된 값이 있으면, lat, lnt 입력 필드에 그 값을 채워넣기
        if (savedLat && savedLnt) {
            document.getElementById('lat').value = savedLat;
            document.getElementById('lnt').value = savedLnt;
        }
    };
</script>
<%@ include file ="layout/footer.jsp" %>