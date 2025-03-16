<%@ page import="com.example.findpublicwifiservice.service.BookmarkGroupService" %>
<%@ page import="com.example.findpublicwifiservice.dto.BookmarkGroupDto" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="layout/header.jsp" %>
<%
    String id = request.getParameter("id");
    BookmarkGroupService service = new BookmarkGroupService();
    BookmarkGroupDto bookmarkGroup = service.getBookMarkGroup(id);
%>
<h2 class="mb-4">북마크그룹 수정</h2>
<%@ include file="layout/nav.jsp" %>

<form action="${pageContext.request.contextPath}/bookmark-group" method="POST">
    <table class="table table-bordered">
        <tbody>
        <tr>
            <th class="text-center">북마크 이름</th>
            <td><input type="text" name="bookmarkName" value="${bookmarkGroup.name}" class="form-control" required/></td>
        </tr>
        <tr>
            <th class="text-center">순서</th>
            <td><input type="number" name="bookmarkOrder" value="${bookmarkGroup.order}" class="form-control" required/></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <input type="hidden" name="action" value="update" />
            <input type="hidden" name="id" value="${bookmarkGroup.id}" />
            <td colspan="2" class="text-center">
                <a href="${pageContext.request.contextPath}/bookmark-group.jsp" class="btn btn-secondary btn-sm w-45">돌아가기</a>
                <button type="submit" class="btn btn-primary btn-lg w-45">수정</button>
            </td>
        </tr>
        </tfoot>
    </table>
</form>

<%@ include file="layout/footer.jsp" %>