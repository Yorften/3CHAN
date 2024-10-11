<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Author");
    request.setAttribute("contentPage", "/views/content/author.jsp");
%>

<jsp:include page="/views/layout/layout.jsp" />