<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="./layout/header.jsp" %>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
        table-layout: fixed;
    }

    th {
        width: 15%;
        background-color: #f2f2f2;
        text-align: center;
        padding: 10px;
        word-wrap: break-word;
    }
    .form-group {
        margin-bottom: 20px;
    }
    .form-group select, .form-group button {
        font-size: 14px;
        height: 40px;
    }
    .form-group select {
        width: auto; /* 드롭다운 너비는 텍스트 길이에 맞게 자동으로 설정 */
    }
    .form-group button {
        padding: 10px 20px;
    }
</style>

<h2 style="margin: 20px">와이파이 정보 구하기</h2>
<%@ include file="./layout/nav.jsp" %>

<form action="${pageContext.request.contextPath}/bookmark" method="POST">
    <div class="form-group">
        <div style="display: flex; align-items: center; gap: 10px;">
            <!-- 드롭다운 -->
            <select class="form-control" name="bookmarkGroup">
                <option value="" selected disabled>북마크 그룹 이름 선택</option>
                <c:forEach var="bookmark" items="${bookmarkGroupList}">
                    <option value="${bookmark.id}">${bookmark.name}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="add" />
            <input type="hidden" name="mgrNo" value="${wifi.mgrNo}" />
            <button type="submit" class="btn btn-primary">북마크 추가하기</button>
        </div>
    </div>
</form>

<!-- 와이파이 정보 테이블 -->
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