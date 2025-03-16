<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.findpublicwifiservice.dto.WiFiDto" %>
<%@ page import="java.util.List" %>
<%@ include file ="layout/header.jsp" %>
<h2 style="margin: 20px">북마크 관리</h2>
<%@ include file ="layout/nav.jsp" %>
<div class="col-md-12">
    <a href="${pageContext.request.contextPath}/bookmark-group-add.jsp" class="btn btn-secondary btn-sm w-45">북마크 그룹이름 추가</a>
    <br>
    <table class="table table-horizontal table-bordered">
        <thead class="thead-strong" style="background-color: #f5f5f5;">
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>와이파이명</th>
            <th>등록일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty bookmarkList}">
            <c:forEach var="bookmark" items="${bookmarkList}">
                <tr>
                    <td>${bookmark.id}</td>
                    <td>${bookmark.bookMarkGroupName}</td>
                    <td><a href="${pageContext.request.contextPath}/wifi?action=getWiFiDetail&mgrNo=${bookmark.wifiMgrNo}"/>${bookmark.wifiName}</td>
                    <td>${bookmark.createDate}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/bookmark-delete.jsp?id=${bookmark.id}"  class="btn btn-danger btn-sm">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty bookmarkList}">
            <tr>
                <td colspan="6" style="text-align: center;">정보가 존재하지 않습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<%@ include file ="layout/footer.jsp" %>