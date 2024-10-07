 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

 <section class="">

     <div class="w-11/12 mx-auto">
         <div class="flex items-center">
             <div class="w-1/2 flex flex-col gap-3">
                 <h1>Better Solutions For Your Business</h1>
                 <p>Empowering teams with seamless project management tools, making collaboration easier, faster, and
                     more effective. Transform the way you organize now!</p>
                 <div class="d-flex align-items-center gap-3">
                     <p class="fs-3" style="margin-bottom: 0px;">Get Started</p>
                     <a href="${pageContext.request.contextPath}/projects">
                         <button class="btn btn-primary py-2">
                             Create your project now!
                         </button>
                     </a>
                 </div>
             </div>
             <div class="w-1/2">
                 <img src="${pageContext.request.contextPath}/assets/img/hero-img.png" class="img-fluid animated"
                     alt="">
             </div>
         </div>
     </div>

 </section><!-- /Hero Section -->
    