<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    request.setAttribute("title", "Pending Comments");
    request.setAttribute("contentPage", "/views/content/pendingComments.jsp"); 
%>

<jsp:include page="/views/layout/layout.jsp" />
