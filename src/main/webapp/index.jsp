<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="layout/header.jsp" %>
<h2 style="margin: 20px">와이파이 정보구하기</h2>
<%@ include file ="layout/nav.jsp" %>
<div class="col-md-12">
    <div class="row">
        <div class="col-md-6">
            <form></form>
<%--            lat--%>
<%--            <input type="text" class="form-control"aria-describedby="y-location">--%>
<%--            loc--%>
<%--            <input type="text" class="form-control"aria-describedby="y-location">--%>
            <label for="lat">LAT:</label>
            <input id="lat" type="text"/>
            <label for="lnt">LNT:</label>
            <input id="lnt" type="text"/>
            <a href="#" role="button" class="btn btn-primary btn-sm">내 위치 가져오기</a>
            <a href="#" role="button" class="btn btn-primary btn-sm">근처 WIPI 정보 보기</a>
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
        <tbody id="tbody">

        <tr>
            <th>거리</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th><a href="#">와이파이명</a></th>
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
        </tbody>
    </table>
</div>

<%@ include file ="layout/footer.jsp" %>