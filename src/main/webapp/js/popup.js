function openPopup(commentId) {
    document.getElementById("popup").classList.remove("hidden");
    document.getElementById("commentId").value = commentId;
  }
  
  function closePopup() {
    document.getElementById("popup").classList.add("hidden");
  }