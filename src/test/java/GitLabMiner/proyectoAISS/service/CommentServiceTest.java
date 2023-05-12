package GitLabMiner.proyectoAISS.service;

import GitLabMiner.proyectoAISS.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get comments")
//    https://gitlab.com/api/v4/projects/:id/issues/:issue_iid/notes
    void findComments() {
        List<Comment> comments = commentService.findComments("45763376", "1");
        assertTrue(comments.size()>0, "Wrong size");
    }
}
