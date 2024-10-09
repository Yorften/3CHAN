package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Comment;
import repository.interfaces.CommentRepository;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceTest.class);

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private CommentRepository commentRepository;

    private CommentService commentService;

    private Comment comment;
    private List<Comment> commentsList;

    @Before
    public void setUp() {
        comment = new Comment();
        comment.setId(1L);
        comment.setContent("This is a test comment");

        commentsList = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setId(2L);
        comment1.setContent("Second test comment");

        Comment comment2 = new Comment();
        comment2.setId(3L);
        comment2.setContent("Third test comment");

        commentsList.add(comment1);
        commentsList.add(comment2);

        commentService = new CommentService(commentRepository);
    }

    @Test
    public void getCommentByIdTest() {

        // Arrange-Act-Assert (AAA) pattern for mocking tests

        when(commentRepository.get(1L)).thenReturn(Optional.of(comment));

        Optional<Comment> result = commentService.getComment(1L);

        assertTrue(result.isPresent());
        assertEquals("This is a test comment", result.get().getContent());

        verify(commentRepository).get(1L);
    }

    @Test
    public void getAllComentsTest() {
        when(commentRepository.getAll(1L)).thenReturn(commentsList);

        List<Comment> result = commentService.getAllComments(1L);

        assertEquals(2, result.size());
        assertEquals("Second test comment", result.get(0).getContent());
        assertEquals("Third test comment", result.get(1).getContent());

        verify(commentRepository).getAll(1L);
    }

    @Test
    public void saveCommentTest() {

        Comment commentToSave = new Comment();
        commentToSave.setId(1L);
        commentToSave.setContent("This is a test comment to save");

        commentService.saveComment(commentToSave);

        verify(commentRepository).save(commentToSave);
    }

    @Test
    public void updateCommentTest() {

        Comment commentToUpdate = new Comment();
        commentToUpdate.setId(1L);
        commentToUpdate.setContent("This is a test comment to update");

        commentService.updateComment(commentToUpdate);

        verify(commentRepository).update(commentToUpdate);
    }

    @Test
    public void deleteCommentTest() {

        Comment commentToDelete = new Comment();
        commentToDelete.setId(1L);
        commentToDelete.setContent("This is a test comment to delete");

        commentService.deleteComment(commentToDelete.getId());

        verify(commentRepository).delete(commentToDelete.getId());
    }

}
