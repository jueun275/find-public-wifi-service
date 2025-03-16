<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="layout/header.jsp" %>

<h2 class="mb-4" style="margin: 20px">북마크그룹 추가</h2>

<%@ include file="layout/nav.jsp" %>

<form action="${pageContext.request.contextPath}/bookmark-group" method="POST" accept-charset="UTF-8">
    <table class="table table-bordered">
        <tbody>
        <tr>
            <th class="text-center" style="background-color: #f5f5f5;">북마크 이름</th>
            <td><input type="text" name="bookmarkName" class="form-control" required/></td>
        </tr>
        <tr>
            <th class="text-center" style="background-color: #f5f5f5;">순서</th>
            <td><input type="text" name="bookmarkOrder" class="form-control" required/></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="2" class="text-center">
                <button type="submit" class="btn btn-primary btn-lg" style="width: 20%;">추가</button>
            </td>
        </tr>
        </tfoot>
    </table>
</form>
<%@ include file="layout/footer.jsp" %>