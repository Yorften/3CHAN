
    // Script to open the add article modal
    document.getElementById('addArticleBtn').addEventListener('click', function() {
        document.getElementById('articleModal').classList.remove('hidden');
    });
    document.getElementById('closeModalBtn').addEventListener('click', function() {
        document.getElementById('articleModal').classList.add('hidden');
    });

    // Script to populate the update article modal
    document.addEventListener('DOMContentLoaded', function() {
        var updateButtons = document.querySelectorAll('[data-bs-target="#updateArticleModal"]');
        updateButtons.forEach(button => {
            button.addEventListener('click', function() {
                var id = button.getAttribute('data-id');
                var title = button.getAttribute('data-title');
                var content = button.getAttribute('data-content');
                var authorId = button.getAttribute('data-authorid');
                document.getElementById('update-article-id').value = id;
                document.getElementById('update-title').value = title;
                document.getElementById('update-content').value = content;
                document.getElementById('update-author_id').value = authorId;
            });
        });
    });
