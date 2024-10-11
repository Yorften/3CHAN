<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Articles");
    request.setAttribute("contentPage", "/views/content/articles.jsp"); 
    request.setAttribute("mainJS", "articles.js"); 

%>

<jsp:include page="/views/layout/layout.jsp" />