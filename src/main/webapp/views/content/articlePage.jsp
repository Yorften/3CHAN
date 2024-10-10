<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="popup" class="fixed w-full h-full top-0 left-0 items-center flex justify-center hidden z-50 bg-black/60">
    <div
        class="bg-white w-[90%] md:w-1/3 h-fit border-2 shadow-xl flex flex-col justify-start items-center overflow-y-auto rounded-2xl md:h-fit">
        <div class="w-[90%] md:w-1/3 h-8 fixed rounded-tr-2xl rounded-tl-2xl">
            <div class="flex justify-end">
                <span onclick="closePopup()" class="text-2xl font-bold cursor-pointer mr-3">&times;</span>
            </div>
        </div>
        <div class="mt-14 mb-4 text-center mx-auto w-[90%] md:w-[80%]">
            <p><b>Deleting the article is a permanent action.</b> Are you sure.</p>
        </div>
        <div class="my-4">
            <!-- Form to submit a delete request -->
            <%--
      <a
        href="../blogpages/deleteArticle.php?articleId=<?= $articleId ?>"
        class="px-8 py-2 bg-gray-500 border border-gray-600 text-white font-semibold rounded-lg"
        >Yes</a
      >
      --%>
            <!-- End of form -->
            <button onclick="closePopup()"
                class="px-8 py-2 bg-gray-500 border border-gray-600 text-white font-semibold rounded-lg">
                No
            </button>
        </div>
    </div>
</div>
<div class="w-[95%] md:w-4/5 mx-auto flex flex-col gap-2">
    <div id="article" class="w-full flex flex-col gap-2">
        <div class="flex flex-row w-full gap-4 items-end mt-8 pl-4">
            <h1 id="title" class="text-2xl md:text-3xl font-medium">
                ${article.title}
            </h1>
        </div>
        <div class="flex flex-col w-full shadow-xl rounded-xl border">
            <div class="w-full flex justify-between items-center">
                <p class="pl-8 py-4 font-medium text-lg text-gray-600">
                    ${article.author.firstName} ${article.author.lastName}
                </p>
                <div class="p-2">
                    <i onclick="openModal()"
                        class="bx bx-edit-alt text-gray-500 text-xl border-gray-500 cursor-pointer"></i>
                    <i onclick="openPopup()"
                        class="bx bx-message-alt-x text-gray-500 text-xl border-gray-500 cursor-pointer"></i>
                </div>
            </div>
            <p id="content" class="p-4 md:p-8 pt-0">${article.content}</p>
        </div>
    </div>
    <div class="flex flex-col mt-6">
        <h1 class="text-2xl font-medium pl-4">Comments</h1>
    </div>
    <div class="flex flex-col gap-4 w-[95%] mx-auto md:mx-0 md:w-4/5">
        <!-- Form to add comment -->
        <form action="" method="post">
            <div class="bg-red-500 mb-3 px-2 rounded-lg w-full">
                <p id="addErr" class="text-white text-lg text-center"></p>
            </div>
            <textarea name="content" cols="30" rows="5" class="w-full resize-none shadow-xl border-t-2 rounded-xl p-4"
                placeholder="Leave a comment!"></textarea>
            <input type="hidden" name="action" value="create_comment" />
            <input type="hidden" name="articleId" value="${article.id}" />
            <input type="hidden" name="pageNumber" value="${currentPage}" />
            <input type="hidden" name="authorId" value="1" />
            <div class="self-end">
                <button type="submit" id="addComment"
                    class="px-8 py-2 bg-gray-500 border border-gray-600 text-white font-semibold rounded-lg">
                    Comment
                </button>
            </div>
        </form>
        <!-- End of form -->
    </div>
    <div id="comments" class="flex flex-col gap-4 w-[95%] mx-auto md:mx-0 md:w-4/5 mt-6 p-2 bg-[#f5f5f5] rounded-lg">
        <c:if test="${pendingCommentsCount > 0}">
            <a href="article/pending?article_id=${articleId}&page=1"
                class="flex flex-col w-full shadow-lg border-t-2 p-2 py-6 pl-4 bg-red-400/50">
                <div class="flex w-full justify-between">
                </div>
                <p class="font-medium text-lg">There are ${pendingCommentsCount} pending comments awaiting approvals</p>
            </a>
        </c:if>
        <c:choose>
            <c:when test="${fn:length(approvedComments) > 0}">
                <c:forEach var="comment" items="${approvedComments}">
                    <div id="comment${comment.id}" class="flex flex-col w-full shadow-lg border-t-2 p-2 pl-4">
                        <div class="flex w-full justify-between">
                            <h1 id="user${comment.id}" class="text-gray-500">
                                <i class="bx bx-user text-gray-500 text-xl border-gray-500"></i>
                                <c:choose>
                                    <c:when test="${comment.author.firstName == null}">
                                        Deleted User
                                    </c:when>
                                    <c:otherwise>
                                        ${comment.author.firstName} ${comment.author.lastName}
                                    </c:otherwise>
                                </c:choose>
                            </h1>
                            <div>
                                <i onclick="editComment(${comment.id},${article.id});"
                                    class="bx bx-edit-alt text-gray-500 text-xl border-gray-500 cursor-pointer"></i>
                                <i onclick="deleteComment(${comment.id},${article.id})"
                                    class="bx bx-message-alt-x text-gray-500 text-xl border-gray-500 cursor-pointer"></i>
                            </div>
                        </div>
                        <p id="p${comment.id}">${comment.content}</p>
                        <div class="flex items-center self-start">
                            <p class="pl-5">${comment.creationDate}</p>
                        </div>
                    </div>
                </c:forEach>
                <div class="flex justify-center">
                    <ul class="flex items-center gap-2">
                        <c:if test="${currentPage > 1}">
                            <li
                                class="p-2 border rounded-md bg-transparent shadow hover:shadow-lg hover:bg-white transition-all duration-300">
                                <a class="page-link"
                                    href="article?article_id=${articleId}&page=${currentPage - 1}">Previous</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <c:choose>
                                <c:when test="${i == currentPage}">
                                    <li
                                        class="p-2 border rounded-md shadow-lg bg-white transition-all duration-300 ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="article?article_id=${articleId}&page=${i}">${i}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li
                                        class="p-2 border rounded-md bg-transparent shadow hover:shadow-lg hover:bg-white transition-all duration-300 ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="article?article_id=${articleId}&page=${i}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li
                                class="p-2 border rounded-md bg-transparent shadow hover:shadow-lg hover:bg-white transition-all duration-300">
                                <a class="page-link"
                                    href="article?article_id=${articleId}&page=${currentPage + 1}">Next</a></li>
                        </c:if>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <div class="flex flex-col w-full shadow-md rounded-lg border-t-2 p-2 pl-4 text-center bg-gray-200">
                    <p>No comments</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
