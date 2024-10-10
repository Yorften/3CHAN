<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Articles");
    request.setAttribute("contentPage", "/views/content/article.jsp"); 
%>

<jsp:include page="/views/layout/layout.jsp" />