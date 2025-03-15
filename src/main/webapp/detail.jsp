<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="./layout/header.jsp" %>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
        table-layout: fixed; /* 테이블 레이아웃 고정 */
    }

    th {
        width: 15%; /* 너비를 15%로 설정 */
        background-color: #f2f2f2;
        text-align: center;
        padding: 10px;
        word-wrap: break-word; /* 긴 텍스트 줄바꿈 처리 */
    }
</style>
<h2 style="margin: 20px">와이파이 정보 구하기</h2>
<%@ include file="./layout/nav.jsp" %>
<table class="table table-horizontal table-bordered">
    <tr>
        <th>관리번호</th>
        <td>${wifi.mgrNo}</td>
    </tr>
    <tr>
        <th>자치구</th>
        <td>${wifi.district}</td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td>${wifi.wifiName}</td>
    </tr>
    <tr>
        <th>도로명 주소</th>
        <td>${wifi.address1}</td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td>${wifi.address2}</td>
    </tr>
    <tr>
        <th>설치 층 (installFloor)</th>
        <td>${wifi.installFloor}</td>
    </tr>
    <tr>
        <th>설치 타입 (installType)</th>
        <td>${wifi.installType}</td>
    </tr>
    <tr>
        <th>설치 기관</th>
        <td>${wifi.installAgency}</td>
    </tr>
    <tr>
        <th>서비스 타입 (serviceType)</th>
        <td>${wifi.serviceType}</td>
    </tr>
    <tr>
        <th>연결 타입 (connectionType)</th>
        <td>${wifi.connectionType}</td>
    </tr>
    <tr>
        <th>설치 연도 (installYear)</th>
        <td>${wifi.installYear}</td>
    </tr>
    <tr>
        <th>실내/실외 (indoorOutdoor)</th>
        <td>${wifi.indoorOutdoor}</td>
    </tr>
    <tr>
        <th>비고 (remarks)</th>
        <td>${wifi.remarks}</td>
    </tr>
    <tr>
        <th>X 좌표</th>
        <td>${wifi.latitude}</td>
    </tr>
    <tr>
        <th>Y 좌표</th>
        <td>${wifi.longitude}</td>
    </tr>
    <tr>
        <th>작업 일자</th>
        <td>${wifi.workDatetime}</td>
    </tr>
</table>

<br>
<a href="${pageContext.request.contextPath}/">뒤로 가기</a>
<%@ include file="./layout/footer.jsp" %>