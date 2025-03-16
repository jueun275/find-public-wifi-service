<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<h2 style="margin: 20px">위치 히스토리 목록</h2>
<%@ include file="layout/nav.jsp" %>
<table class="table table-horizontal table-bordered">
    <thead class="thead-strong" style="background-color: #f5f5f5;">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="history" items="${historyList}" varStatus="status">
        <tr>
            <td>${history.id}</td>
            <td>${history.x}</td>
            <td>${history.y}</td>
            <td>${history.date}</td>
            <td>
                <form action="${pageContext.request.contextPath}/history" method="POST" style="display:inline;">
                    <input type="hidden" name="id" value="${history.id}">
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="layout/footer.jsp" %>