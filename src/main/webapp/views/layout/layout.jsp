<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>3CHAN | ${title != null ? title : 'Home'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tailwind/output.css" />
    <link rel="apple-touch-icon" sizes="180x180"
        href="${pageContext.request.contextPath}/assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32"
        href="${pageContext.request.contextPath}/assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16"
        href="${pageContext.request.contextPath}/assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="${pageContext.request.contextPath}/assets/favicon/site.webmanifest">
    
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

</head>

<body>
    <header id="header">
        <jsp:include page="../components/header.jsp" />
    </header>

    <main id="content">
        <c:if test="${not empty contentPage}">
            <jsp:include page="${contentPage}" />
        </c:if>
    </main>

    <footer id="footer">
        <jsp:include page="../components/footer.jsp" />
    </footer>

    <c:if test="${not empty mainJS}">
        <script src="${pageContext.request.contextPath}/js/${mainJS}"></script>
    </c:if>

</body>

</html>
