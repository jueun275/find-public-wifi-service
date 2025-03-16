<%@ page import="com.example.findpublicwifiservice.service.BookmarkService" %>
<%@ page import="com.example.findpublicwifiservice.dto.BookmarkGroupDto" %>
<%@ page import="com.example.findpublicwifiservice.dto.BookmarkDto" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="layout/header.jsp" %>
<%
    String id = request.getParameter("id");
    BookmarkService service = new BookmarkService();
    BookmarkDto bookmark = service.selectBookmark(Integer.parseInt(id));
%>
<h2 class="mb-4">북마크 삭제</h2>
<%@ include file="layout/nav.jsp" %>

    <table class="table table-bordered">
        <tbody>
        <tr>
            <th class="text-center">북마크 이름</th>
            <td><label><%= bookmark.getBookMarkGroupName() %></label></td>
        </tr>
        <tr>
            <th class="text-center">와이파이명</th>
            <td><label><%= bookmark.getWifiName() %></label></td>
        </tr>
        <tr>
            <th class="text-center">등록일자</th>
            <td><label><%= bookmark.getCreateDate() %></label></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <form action="${pageContext.request.contextPath}/bookmark" method="POST">
            <input type="hidden" name="action" value="delete" />
            <input type="hidden" name="id" value="<%= bookmark.getId() %>" />
            <td colspan="2" class="text-center">
                <a href="${pageContext.request.contextPath}/bookmark" class="btn btn-secondary btn-sm w-45">돌아가기</a>
                <button type="submit"  class="btn btn-danger btn-sm">삭제</button>
            </td>
            </form>
        </tr>
        </tfoot>
    </table>

<%@ include file="layout/footer.jsp" %>