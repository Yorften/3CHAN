<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Article");
    request.setAttribute("contentPage", "/views/content/articlePage.jsp"); 
%>

<jsp:include page="/views/layout/layout.jsp" />
