<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-body-tertiary" >
    <div class="container-fluid" style="border: 1px solid black;">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="#">홈</a>
                <a class="nav-link" href="#">위치 히스토리 목록</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
            </div>
        </div>
    </div>
</nav>