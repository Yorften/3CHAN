<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Not Found")
    request.setAttribute("contentPage", "/views/content/404.jsp"); 
%>

<jsp:include page="/views/layout/layout.jsp" />
