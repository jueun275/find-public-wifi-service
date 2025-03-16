<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.findpublicwifiservice.dto.WiFiDto" %>
<%@ page import="java.util.List" %>
<%@ include file ="layout/header.jsp" %>
<h2 style="margin: 20px">북마크 그룹 관리</h2>
<%@ include file ="layout/nav.jsp" %>
<div class="col-md-12">
    <a href="${pageContext.request.contextPath}/bookmark-group-add.jsp" class="btn btn-secondary btn-sm w-45">북마크 그룹이름 추가</a>
    <br>
    <table class="table table-horizontal table-bordered">
        <thead class="thead-strong" style="background-color: #f5f5f5;">
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty bookmarkGroupList}">
            <c:forEach var="bookmark" items="${bookmarkGroupList}">
                <tr>
                    <td>${bookmark.id}</td>
                    <td>${bookmark.name}</td>
                    <td>${bookmark.orderValue}</td>
                    <td>${bookmark.createDate}</td>
                    <td>${bookmark.updateDate}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/bookmark-group" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete" />
                            <input type="hidden" name="id" value="${bookmark.id}" />
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
                        </form>
                        <a href="${pageContext.request.contextPath}/bookmark-group-edit.jsp?id=${bookmark.id}" class="btn btn-warning btn-sm">수정</a>
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