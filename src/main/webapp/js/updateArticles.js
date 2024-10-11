// Script to open the update article modal
document.addEventListener('DOMContentLoaded', function() {
    var updateButtons = document.querySelectorAll('[data-bs-target="#updateArticleModal"]');
    updateButtons.forEach(button => {
        button.addEventListener('click', function() {
            //var id = button.getAttribute('data-id');
            var title = button.getAttribute('data-title');
            var content = button.getAttribute('data-content');

            // Populate the fields with the current data
            //document.getElementById('update-article-id').value = id;
            document.getElementById('update-title').value = title;
            document.getElementById('update-content').value = content;

            // Show the modal
            document.getElementById('updateArticleModal').classList.remove('hidden');
        });
    });

    // Close update modal
    document.getElementById('closeUpdateModalBtn').addEventListener('click', function() {
        document.getElementById('updateArticleModal').classList.add('hidden');
    });
});
