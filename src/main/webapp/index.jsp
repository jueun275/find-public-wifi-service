<%@ page import="com.example.findpublicwifiservice.dto.WiFiDto" %>
<%@ page import="java.util.List" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="layout/header.jsp" %>
<h2 style="margin: 20px">와이파이 정보구하기</h2>
<%@ include file ="layout/nav.jsp" %>
<div class="col-md-12">
    <div class="row">
        <div class="col-md-6">
            <form action="wifi-load" method="GET">
                <label for="lat">LAT:</label>
                <input type="text" id="lat" name="lat" required>

                <label for="lnt">LNT</label>
                <input type="text" id="lnt" name="lnt" required>

                <button class="btn btn-primary btn-sm"  >내 위치 가져오기</button>
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
            <c:when test="${not empty wifiList}">
                <c:forEach var="wifi" items="${wifiList}" varStatus="status">
                    <tr>
                        <td>${wifi.distance}</td>
                        <td>${wifi.mgrNo}</td>
                        <td>${wifi.district}</td>
                        <td><a href="/wifi"/>{wifi.wifiName}</td>
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

<%@ include file ="layout/footer.jsp" %>