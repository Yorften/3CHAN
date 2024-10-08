package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Before
    public void setUp() {
        comment = new Comment();
        comment.setId(1L);
        comment.setContent("This is a test comment");

        commentService = new CommentService(commentRepository);
    }

    @Test
    public void getCommentByIdTest() {

        // Arrange-Act-Assert (AAA) pattern for mocking tests

        when(commentRepository.get(1L)).thenReturn(Optional.of(comment));

        Optional<Comment> result = commentService.getComment(1L);

        System.out.println(result);

        assertTrue(result.isPresent());
        assertEquals("This is a test comment", result.get().getContent());

        verify(commentRepository).get(1L);
    }

}
