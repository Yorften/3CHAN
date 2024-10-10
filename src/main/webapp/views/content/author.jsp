<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container mx-auto p-5">
    <h1 class="text-2xl font-bold mb-5 text-center">List of Authors</h1>

    <!-- Check if the list is empty -->
    <c:if test="${empty authors}">
        <p class="text-center">No authors found.</p>
    </c:if>

      <c:if test="${not empty successMessage}">
             <div class="text-center text-green-600 font-bold mb-4">
                 ${successMessage}
             </div>
         </c:if>

    <button data-modal-target="add-author-modal" data-modal-toggle="add-author-modal" class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">
        Add author
    </button>

    <!-- Add margin below the button -->
    <div class="mb-5"></div>

    <!-- Table to display authors -->
    <div class="overflow-x-auto">
        <table class="min-w-full bg-white border border-gray-300 mx-auto">
            <thead>
                <tr class="bg-gray-200 text-gray-700 ">
                    <th class="py-3 px-4 border-b">First Name</th>
                    <th class="py-3 px-4 border-b">Last Name</th>
                    <th class="py-3 px-4 border-b">Email</th>
                    <th class="py-3 px-4 border-b">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="author" items="${authors}">
                    <tr class="hover:bg-gray-100 text-center">
                        <td class="py-3 px-4 border-b">${author.firstName}</td>
                        <td class="py-3 px-4 border-b">${author.lastName}</td>
                        <td class="py-3 px-4 border-b">${author.email}</td>
                        <td class="py-3 px-4 border-b flex space-x-2  ">
                            <a href="updateAuthor?id=${author.id}" class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-700 transition duration-200">Update</a>

                          <form action="" method="post">
                              <input type="hidden" name="id" value="${author.id}"/>
                              <input type="hidden" name="action" value="delete">
                              <button type="submit" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-700 transition duration-200">Delete</button>
                          </form>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>



<!-- Add Author Modal -->
<div id="add-author-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
    <div class="relative p-4 w-full max-w-md max-h-full">
        <!-- Modal content -->
        <div class="relative bg-black rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                <h3 class="text-xl font-semibold text-white dark:text-white">
                    Add Author
                </h3>
                <button type="button" class="end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-hide="add-author-modal">
                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                    </svg>
                    <span class="sr-only">Close modal</span>
                </button>
            </div>
            <!-- Modal body -->
            <div class="p-4 md:p-5">
           <form  class="space-y-4" action="" method="post">
            <input type="hidden" name="action" value="add">
                  <div>
                      <label for="firstName" class="block mb-2 text-sm font-medium text-gray-300 dark:text-white">First Name</label>
                      <input type="text" name="firstName" id="firstName" class="bg-gray-800 border border-gray-600 text-white text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400" required />
                  </div>

                  <div>
                      <label for="lastName" class="block mb-2 text-sm font-medium text-gray-300 dark:text-white">Last Name</label>
                      <input type="text" name="lastName" id="lastName" class="bg-gray-800 border border-gray-600 text-white text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400" required />
                  </div>

                  <div>
                      <label for="email" class="block mb-2 text-sm font-medium text-gray-300 dark:text-white">Email</label>
                      <input type="email" name="email" id="email" class="bg-gray-800 border border-gray-600 text-white text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400" placeholder="name@company.com" required />
                  </div>

                  <div>
                      <label for="dateOfBirth" class="block mb-2 text-sm font-medium text-gray-300 dark:text-white">Date of Birth</label>
                      <input type="date" name="dateOfBirth" id="dateOfBirth" class="bg-gray-800 border border-gray-600 text-white text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400" required />
                  </div>

                  <div>
                      <label for="role" class="block mb-2 text-sm font-medium text-gray-300 dark:text-white">Role</label>
                      <select name="role" id="role" class="bg-gray-800 border border-gray-600 text-white text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400" required>
                          <option value="CONTRIBUTOR">Contributor</option>
                          <option value="EDITOR">Editor</option>
                      </select>
                  </div>

                  <button type="submit" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Add Author</button>
              </form>
            </div>
        </div>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
