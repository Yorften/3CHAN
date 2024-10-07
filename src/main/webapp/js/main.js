function dragstartHandler(ev) {
  // Add the target element's id to the data transfer object
  ev.dataTransfer.setData("text/plain", ev.target.id);

  ev.dataTransfer.dropEffect = "move";

  ev.dataTransfer.setDragImage(ev.target, 0, 0);
}

function dragoverHandler(ev) {
  ev.preventDefault();
  ev.dataTransfer.dropEffect = "move";
}

async function dropHandler(ev) {
  ev.preventDefault();

  const task_id = ev.dataTransfer.getData("text/plain");
  const draggedElement = document.getElementById(task_id);

  const dropZone = ev.target.closest(".drop-zone");

  const status = dropZone.getAttribute("drop-zone-status");

  const targetCard = ev.target.closest(".card");

  if (targetCard) {
    const bounding = targetCard.getBoundingClientRect();

    // Mouse Y position (px) - Top border to top view port distance (px)
    const offset = ev.clientY - bounding.top;

    // Change the task status before moving the card, in the case of an error the card won't be moved
    const data = await changeTaskStatus(task_id, status);

    if (data.error == "error") {
      alert("Unexpected error occured");
      return;
    }

    // If the offset is longer than half the height of the card the mouse was on the bottom half of the card
    if (offset < bounding.height / 2) {
      // Insert before the element
      dropZone.insertBefore(draggedElement, targetCard);
    } else {
      // Insert after the element
      targetCard.insertAdjacentElement("afterend", draggedElement);
    }
  } else {
    // Change the task status before moving the card, in the case of an error the card won't be moved
    const data = await changeTaskStatus(task_id, status);

    if (data.error == "error") {
      alert("Unexpected error occured");
      return;
    }

    dropZone.appendChild(draggedElement);
  }
}

window.addEventListener("DOMContentLoaded", () => {
  const elements = document.getElementsByClassName("card");

  Array.from(elements).forEach((element) => {
    // Make the element draggable
    element.setAttribute("draggable", "true");

    element.addEventListener("dragstart", dragstartHandler);
  });
});

function openTaskModal(
  id,
  title,
  description,
  priority,
  member_id,
  assignDate
) {
  console.log(member_id);

  document.getElementById("updatedTaskId").value = id;
  document.getElementById("deletedTaskId").value = id;
  document.getElementById("updatedTaskTitle").value = title;
  document.getElementById("updatedTaskDescription").value = description;
  document.getElementById("updatedTaskPriority").value = priority;
  document.getElementById("updatedSquadMember").value = member_id;
  document.getElementById("assignDate").innerHTML = assignDate;
}

function createTaskModal(status) {
  document.getElementById("taskStatus").value = status;
}

async function changeTaskStatus(task_id, status) {
  const url = `http://localhost:9080/CollabProject/projects/tasks/status?taskId=${task_id}&status=${status}`;

  const response = await fetch(url, {
    method: "POST",
  });

  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }

  const data = await response.json();

  return data;
}
