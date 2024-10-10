  <div class="container mx-auto p-5">
        <h1 class="text-2xl font-bold mb-5">List of Authors</h1>
        <table class="min-w-full bg-white border border-gray-300">
            <thead>
                <tr class="bg-gray-200 text-gray-700">
                    <th class="py-3 px-4 border-b">ID</th>
                    <th class="py-3 px-4 border-b">Name</th>
                    <th class="py-3 px-4 border-b">Email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="author" items="${authors}">
                    <tr class="hover:bg-gray-100">
                        <td class="py-3 px-4 border-b">${author.id}</td>
                        <td class="py-3 px-4 border-b">${author.name}</td>
                        <td class="py-3 px-4 border-b">${author.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>