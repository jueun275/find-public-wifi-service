<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="layout/header.jsp" %>
<h3>${count}개의 와이파이 정보를 정상적으로 저장했습니다.</h3>
<a href="${pageContext.request.contextPath}/index.jsp" role="button" class="btn btn-primary btn-sm">홈으로 가기</a>
<%@ include file ="layout/footer.jsp" %>