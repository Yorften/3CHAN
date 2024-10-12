 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

 <section>

     <div class="flex flex-col gap-10 items-center w-10/12 mx-auto">
         <a class="flex items-center gap-2" href="${pageContext.request.contextPath}">
             <img src="${pageContext.request.contextPath}/assets/favicon/android-chrome-192x192.png" class="w-20 h-20"
                 alt="">
             <p style="margin-bottom: 0px;" class="text-6xl">3CHAN</p>
         </a>
         <div class="flex flex-col md:flex-row gap-5 items-center ml-5">
             <div class="w-10/12 md:w-[53%] flex flex-col gap-5">
                 <h1 class="text-5xl font-medium">Welcome to 3CHAN</h1>
                 <p class="text-xl">3CHAN is an open forum designed for unfiltered conversations and creative expression. Whether you're
                     discussing the latest trends, sharing your thoughts, or just looking for a space to post
                     anonymously!</p>
                 <a href="${pageContext.request.contextPath}/articles">
                     <button class="rounded bg-black text-gray-100 hover:bg-gray-950 shadow hover:shadow-lg transition-all duration-300 text-xl p-3">
                         Explore 3CHAN Now!
                     </button>
                 </a>
             </div>
             <div class="w-10/12 md:w-[47%]">
                 <img src="${pageContext.request.contextPath}/assets/img/hero-img.png" class="img-fluid animated"
                     alt="">
             </div>
         </div>
     </div>

 </section><!-- /Hero Section -->
