<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="./layout/header.jsp" %>
<h2 style="margin: 20px">error 페이지</h2>
<p>문제가 발생했습니다.</p>
<p><c:out value="${errorMessage}" /></p>
<%@ include file ="./layout/footer.jsp" %>