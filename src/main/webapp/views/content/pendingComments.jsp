<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="w-[95%] min-h-[45vh] h-full mx-auto flex flex-col gap-2">

    <c:if test="${message != null}">
        <div class="w-full shadow-lg rounded-sm border-t-2 p-2 py-6 pl-4 bg-red-400/50">
            <p class="font-medium">${message}</p>
        </div>
    </c:if>


    <div class="flex flex-col mt-6">
        <h1 class="text-3xl font-medium pl-4">Pending Comments</h1>
    </div>
  
    <div id="comments" class="flex flex-col gap-4 w-[95%] mx-auto md:mx-0 mt-6 p-2 bg-[#f5f5f5] rounded-lg">
        <c:choose>
            <c:when test="${fn:length(pendingComments) > 0}">
                <c:forEach var="comment" items="${pendingComments}">
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
                        </div>
                        <p id="p${comment.id}">${comment.content}</p>
                        <div class="flex items-center justify-between w-full">
                            <p class="pl-5">${comment.creationDate}</p>
                            <div class="flex items-center self-end gap-3">
                                <form action="" method="post">
                                    <input type="hidden" name="action" value="approve">
                                    <input type="hidden" name="commentId" value="${comment.id}">
                                    <input type="hidden" name="articleId" value="${articleId}">
                                    <input type="hidden" name="pageNumber" value="${currentPage}">
                                    <button type="submit" class="p-2 border-2 rounded-md bg-transparent hover:bg-white transition-all duration-300">Approve</button>
                                </form>
                                <form action="" method="post">
                                    <input type="hidden" name="action" value="reject">
                                    <input type="hidden" name="commentId" value="${comment.id}">
                                    <input type="hidden" name="articleId" value="${articleId}">
                                    <input type="hidden" name="pageNumber" value="${currentPage}">
                                    <button type="submit" class="p-2 border-2 rounded-md bg-transparent hover:bg-white transition-all duration-300">Reject</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="flex justify-center">
                    <ul class="flex items-center gap-2">
                        <c:if test="${currentPage > 1}">
                            <li class="p-2 border rounded-md bg-transparent shadow hover:shadow-lg hover:bg-white transition-all duration-300"><a class="page-link"
                                    href="article/pending?article_id=${articleId}&page=${currentPage - 1}">Previous</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <c:choose>
                                <c:when test="${i == currentPage}">
                                    <li class="p-2 border rounded-md shadow-lg bg-white transition-all duration-300 ${currentPage == i ? 'active' : ''}"><a class="page-link"
                                        href="article/pending?article_id=${articleId}&page=${i}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="p-2 border rounded-md bg-transparent shadow hover:shadow-lg hover:bg-white transition-all duration-300 ${currentPage == i ? 'active' : ''}"><a class="page-link"
                                        href="article/pending?article_id=${articleId}&page=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li class="p-2 border rounded-md bg-transparent shadow hover:shadow-lg hover:bg-white transition-all duration-300"><a class="page-link"
                                    href="article/pending?article_id=${articleId}&page=${currentPage + 1}">Next</a></li>
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
