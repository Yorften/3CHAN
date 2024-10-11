<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Header -->
<header
	class="flex flex-col justify-between items-center h-[30vh] md:h-[40vh] py-8 bg-white shadow-lg text-center">
	<div
		class="flex justify-between items-center gap-4 sm:gap-4 md:w-[60%] mx-auto">
		<h1 class="text-3xl">Theme Name</h1>
		<button id="addArticleBtn"
			class="px-4 py-2 bg-[#9fff30] font-semibold rounded-lg border-2 border-[#6da22f]">Add
			article +</button>
	</div>

<!-- Search bar -->
<div class="flex items-center justify-center bg-gray-100 rounded border border-gray-200 mt-4 w-1/4 mx-auto">
    <form action="articles" method="post" class="flex">  
        <input type="hidden" name="action" value="search">  
        
        <input id="search-bar-title" type="text" name="title" placeholder="Search"
            class="flex items-center justify-center bg-transparent py-1 text-gray-600 px-4 focus:outline-none w-full" />
        
        <button type="submit"
            class="py-2 px-4 bg-[#bdff72] text-black rounded-r border-l border-gray-200 hover:bg-gray-50 active:bg-gray-200 disabled:opacity-50 inline-flex items-center focus:outline-none">
            Search
        </button>
    </form>
</div>




</header>

<!-- Add Article Modal -->
<div id="articleModal"
	class="fixed inset-0 bg-gray-500 bg-opacity-75 flex items-center justify-center hidden">
	<div class="bg-white rounded-lg p-6 w-11/12 md:w-1/2">
		<h2 class="text-2xl font-bold mb-4">Add Article</h2>
		<form action="articles?action=add" method="post">
			<div class="mb-4">
				<label for="title" class="block text-sm font-medium text-gray-700">Title</label>
				<input type="text" id="title" name="title" required
					class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50" />
			</div>
			<div class="mb-4">
				<label for="content" class="block text-sm font-medium text-gray-700">Content</label>
				<textarea id="content" name="content" required rows="4"
					class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50"></textarea>
			</div>
			<div class="mb-4">
				<label for="author_id"
					class="block text-sm font-medium text-gray-700">Author</label> <select
					id="author_id" name="author_id"
					class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring focus:ring-opacity-50">
					<option value="">Select an author</option>
					<c:forEach var="author" items="${authors}">
						<option value="${author.id}">${author.firstName}
							${author.lastName}</option>
					</c:forEach>
				</select>


			</div>
			<div class="flex justify-end">
				<button type="button" id="closeModalBtn"
					class="px-4 py-2 bg-gray-300 rounded-md mr-2">Cancel</button>
				<button type="submit"
					class="px-4 py-2 bg-blue-600 text-white rounded-md">Add
					Article</button>
			</div>
		</form>
		<c:if test="${not empty errorMessage}">
			<div class="mt-4 text-red-600">${errorMessage}</div>
		</c:if>
	</div>
</div>

 
<!-- Articles List -->
<div class="flex flex-col justify-between items-center min-h-[90vh]">
    <div class="w-11/12 mx-auto article">
        <c:choose>
            <c:when test="${not empty articles}">
                <c:forEach var="article" items="${articles}">
                    <div class="bg-white shadow-lg shadow-gray-300 m-4 p-4 rounded-lg">
                        <a href="#"
                            class="flex justify-between text-black font-medium hover:text-gray-500">
                            ${article.title} <span><i class="bx bx-message-dots h-8 w-8"></i></span>
                        </a>
                        <p class="text-gray-800 m-2">
                            ${fn:substring(article.content, 0, 200)}... <span><a
                                href="article?article_id=${article.id}&page=1"
                                class="hover:text-gray-500 font-medium">Read more</a></span>
                        </p>
                        <div class="flex justify-between m-1">
                            <!-- Update and Delete Actions -->
                            <div class="flex space-x-2">
                                <button type="button"
                                    class="bg-blue-500 text-white py-1 px-3 rounded hover:bg-blue-600"
                                    data-bs-toggle="modal" data-bs-target="#updateArticleModal"
                                    data-id="${article.id}" data-title="${article.title}"
                                    data-content="${article.content}"
                                    data-authorid="${article.author.id}">Update</button>

                                <form action="articles" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="delete"> 
                                    <input type="hidden" name="id" value="${article.id}">
                                    <button type="submit"
                                        class="bg-red-500 text-white py-1 px-3 rounded hover:bg-red-600"
                                        onclick="return confirm('Are you sure you want to delete this article?');">
                                        Delete</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="bg-white shadow-lg shadow-gray-300 m-4 p-4 rounded-lg text-center">
                    <p class="text-gray-800">Search not found</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>


	<!-- Pagination -->
	<div class="w-full flex flex-col items-center my-4">
		<div class="pl-6">Showing ${currentPage} of ${totalPages}</div>
		<nav aria-label="Page navigation example">
			<ul class="flex items-center -space-x-px h-8 text-sm">
				<li><c:if test="${currentPage > 1}">
						<a href="articles?action=list&page=1"
							class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-s-lg hover:bg-gray-100">
							First </a>
					</c:if></li>
				<li><c:if test="${currentPage > 1}">
						<a href="articles?action=list&page=${currentPage - 1}"
							class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100">
							Previous </a>
					</c:if></li>
				<c:forEach begin="1" end="${totalPages}" var="page">
					<li><c:choose>
							<c:when test="${page == currentPage}">
								<a href="#" aria-current="page"
									class="z-10 flex items-center justify-center px-3 h-8 leading-tight text-blue-600 border border-blue-300 bg-blue-50">
									${page} </a>
							</c:when>
							<c:otherwise>
								<a href="articles?action=list&page=${page}"
									class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100">
									${page} </a>
							</c:otherwise>
						</c:choose></li>
				</c:forEach>
				<li><c:if test="${currentPage < totalPages}">
						<a href="articles?action=list&page=${currentPage + 1}"
							class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100">
							Next </a>
					</c:if></li>
			</ul>
		</nav>
	</div>
</div>



