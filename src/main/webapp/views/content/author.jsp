<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container mx-auto p-5">
    <h1 class="text-2xl font-bold mb-5">List of Authors</h1>

    <!-- Check if the list is empty -->
    <c:if test="${empty authors}">
        <p>No authors found.</p>
    </c:if>

    <!-- Table to display authors -->
    <table class="min-w-full bg-white border border-gray-300">
        <thead>
            <tr class="bg-gray-200 text-gray-700">
                <th class="py-3 px-4 border-b">ID</th>
                <th class="py-3 px-4 border-b">Name</th>
                <th class="py-3 px-4 border-b">Email</th>
                <th class="py-3 px-4 border-b">Comment</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="author" items="${authors}">
                <tr class="hover:bg-gray-100">
                    <td class="py-3 px-4 border-b">${author.id}</td>
                    <td class="py-3 px-4 border-b">${author.firstName} ${author.lastName}</td>
                    <td class="py-3 px-4 border-b">${author.email}</td>
                    <td class="py-3 px-4 border-b">${author.comments[1].content}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
