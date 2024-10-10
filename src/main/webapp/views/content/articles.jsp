<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
 <!DOCTYPE html>
<html lang="en">

 

    <header class="flex flex-col justify-between items-center h-[30vh] md:h-[40vh] py-8 bg-white shadow-lg text-center">
        <div class="flex justify-between items-center gap-4 sm:gap-4 md:w-[60%] mx-auto">
            <h1 class="text-3xl">Theme Name</h1>
            <a href="#" class="px-4 py-2 bg-[#9fff30] font-semibold rounded-lg border-2 border-[#6da22f]">Add article +</a>
        </div>
     
        <div class="flex items-center justify-center bg-gray-100 rounded border border-gray-200 mt-4 w-1/4 mx-auto">
            <input id="search-bar" type="text" name="search" placeholder="Search" class="flex items-center align-middle justify-center bg-transparent py-1 text-gray-600 px-4 focus:outline-none w-full" />
            <button class="py-2 px-4 bg-[#bdff72] text-black rounded-r border-l border-gray-200 hover:bg-gray-50 active:bg-gray-200 disabled:opacity-50 inline-flex items-center focus:outline-none">
                Search
            </button>
        </div>
    </header>

    <div class="flex flex-col justify-between items-center min-h-[90vh]">
        <div class="w-11/12 mx-auto article">
            <!-- Article Example -->
            <div class="bg-white shadow-lg shadow-gray-300 m-4 p-4 rounded-lg">
                <a href="#" class="flex justify-between text-black font-medium hover:text-gray-500">Article Title
                    <span>
                        <i class="bx bx-message-dots h-8 w-8" ></i>
                    </span>
                </a>
                <p class="text-gray-800 m-2">Article content preview...<span><a href="#" class="hover:text-gray-500 font-medium">Read more</a></span></p>
                <div class="flex justify-between m-1">
                    <small class="text-gray-500 flex items-center">
                        <i class='bx bx-user text-black text-xl rounded-xl border-black'></i>
                        <p>Posted By User Name</p>
                    </small>
                </div>
            </div>
        </div>

        <!-- Pagination Example -->
        <div class="w-[70%] mx-auto">
            <div class="pl-6">Showing 1 of 5</div>
            <div class="flex flex-row justify-center items-center gap-3">
                <a href="#" class="cursor-pointer">First</a>
                <a href="#" class="cursor-pointer">Previous</a>
                <a href="#" class="cursor-pointer">1</a>
                <a href="#" class="cursor-pointer">Next</a>
                <a href="#" class="cursor-pointer">Last</a>
            </div>
        </div>
    </div>

 
 