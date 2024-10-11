 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

 <nav class="flex items-center justify-between p-2 shadow-lg">
     <a class="flex items-center gap-2" href="${pageContext.request.contextPath}">
         <img src="${pageContext.request.contextPath}/assets/favicon/android-chrome-192x192.png" alt=""
             style="width: 35px; height: 35px;">
         <p style="margin-bottom: 0px;">3CHAN</p>
     </a>
     <ul class="flex items-center gap-4">
         <li>
             <a class="text-gray-800 hover:text-gray-500" aria-current="page" href="${pageContext.request.contextPath}">Home</a>
         </li>
         <li>
             <a class="text-gray-800 hover:text-gray-500" href="${pageContext.request.contextPath}/articles">Articles</a>
         </li>
         <li>
             <a class="text-gray-800 hover:text-gray-500" href="${pageContext.request.contextPath}/authors">Authors</a>
         </li>
     </ul>
     <div></div>
 </nav>
