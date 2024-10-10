<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container mx-auto p-5">
    <h1 class="text-2xl font-bold mb-5 text-center">List of Authors</h1>

    <!-- Check if the list is empty -->
    <c:if test="${empty authors}">
        <p class="text-center">No authors found.</p>
    </c:if>

    <!-- Table to display authors -->
    <div class="overflow-x-auto">
        <table class="min-w-full bg-white border border-gray-300 mx-auto">
            <thead>
                <tr class="bg-gray-200 text-gray-700">
                    <th class="py-3 px-4 border-b">First Name</th>
                    <th class="py-3 px-4 border-b">Last Name</th>
                    <th class="py-3 px-4 border-b">Email</th>
                    <th class="py-3 px-4 border-b">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="author" items="${authors}">
                    <tr class="hover:bg-gray-100">
                        <td class="py-3 px-4 border-b">${author.firstName}</td>
                        <td class="py-3 px-4 border-b">${author.lastName}</td>
                        <td class="py-3 px-4 border-b">${author.email}</td>
                        <td class="py-3 px-4 border-b flex space-x-2">
                            <a href="updateAuthor?id=${author.id}" class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-700 transition duration-200">Update</a>
                            <form action="deleteAuthor" method="post" onsubmit="return confirm('Are you sure you want to delete this author?');">
                                <input type="hidden" name="id" value="${author.id}"/>
                                <button type="submit" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-700 transition duration-200">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
