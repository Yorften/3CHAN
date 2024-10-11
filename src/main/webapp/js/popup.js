function openPopup(commentId) {
  document.getElementById("popup").classList.remove("hidden");
  document.getElementById("commentId").value = commentId;
}

function closePopup() {
  document.getElementById("popup").classList.add("hidden");
}

function editComment(commentId) {
  let comment = document.getElementById("p" + commentId).textContent;
  let user = document.getElementById("user" + commentId).textContent;
  let date = document.getElementById("date" + commentId).textContent;
  document.getElementById("comment" + commentId).innerHTML = `
    <div class="bg-red-500 mb-3 px-2 rounded-lg w-full">
                  <p id="modErr" class="text-white text-lg text-center"></p>
    </div>
    <textarea name="comment" id="newcomment${commentId}" cols="30" rows="5" class="w-full resize-none shadow-xl border-t-2 rounded-xl p-4 mb-4" placeholder="Leave a comment!">${comment}</textarea>
    <p id="error${commentId}" class="text-xs text-red-600"></p>
    <div class="w-full flex justify-end gap-4">
        <button onclick="applyNewComment(${commentId},'${user}', '${date}')" class="px-8 py-2 bg-gray-500 border border-gray-600 text-white font-semibold rounded-lg ">Apply</button>
        <button onclick="cancelEdit(${commentId},'${comment}','${user}', '${date}')" class="px-8 py-2 bg-gray-500 border border-gray-600 text-white font-semibold rounded-lg ">Cancel</button>
    </div>
    `;
}

function cancelEdit(commentId, comment, user, date) {
  document.getElementById("comment" + commentId).innerHTML = `
    <div class="flex w-full justify-between">
      <div class="flex items-center gap-2 text-gray-500">
        <i class="bx bx-user text-gray-500 text-xl border-gray-500"></i>
        <p id="user${commentId}">${user}</p>
      </div>
      <div>
          <i onclick="editComment(${commentId})" class="bx bx-edit-alt text-gray-500 text-xl border-gray-500 cursor-pointer"></i>
          <i onclick="openPopup(${commentId})" class="bx bx-message-alt-x text-gray-500 text-xl border-gray-500 cursor-pointer"></i>
      </div>
    </div>
    <p id="p${commentId}">${comment}</p>
    <div class="flex items-center self-start mt-4">
      <p id="date${commentId}" class="pl-2">${date}</p>
    </div>
    `;
}

async function applyNewComment(commentId, user, date) {
  let host = window.location.hostname;
  let port = window.location.port;
  let urlPathname = "3CHAN/comment/update";

  let url = `${window.location.protocol}//${host}:${port}/${urlPathname}`;

  let content = document.getElementById("newcomment" + commentId).value;

  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ commentId: commentId, content: content }),
  });

  if (!response.ok) {
    const data = await response.json();

    let errorElement = document.getElementById("error" + commentId);
    console.log(errorElement);
    errorElement.textContent = data.error;

    throw new Error(
      `HTTP error! Status: ${response.status}, Message: ${data.error}`
    );
  }

  const data = await response.json();

  document.getElementById("comment" + commentId).innerHTML = `
    <div class="flex w-full justify-between">
      <div class="flex items-center gap-2 text-gray-500">
        <i class="bx bx-user text-gray-500 text-xl border-gray-500"></i>
        <p id="user${commentId}">${user}</p>
      </div>
      <div>
          <i onclick="editComment(${commentId})" class="bx bx-edit-alt text-gray-500 text-xl border-gray-500 cursor-pointer"></i>
          <i onclick="openPopup(${commentId})" class="bx bx-message-alt-x text-gray-500 text-xl border-gray-500 cursor-pointer"></i>
      </div>
    </div>
    <p id="p${commentId}">${content}</p>
    <div class="flex items-center self-start mt-4">
      <p id="date${commentId}" class="pl-2">${date}</p>
    </div>
  `;
}
