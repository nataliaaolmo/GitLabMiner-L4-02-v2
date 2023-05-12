package GitLabMiner.proyectoAISS.service;

import GitLabMiner.proyectoAISS.model.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IssueServiceTest {

    @Autowired
    IssueService issueService;
    @Test
    @DisplayName("Get all users")
//    https://gitlab.com/api/v4/projects/{id}/issues
    void findIssue() {
        List<Issue> issues = issueService.findIssues("45763376");
        assertTrue(!issues.isEmpty(), "The list of issues is empty!");
    }
    /*
    @Test
    @DisplayName("Get user")
    void findIssueByID() {
//        Issues user = service.findIssues("2");
//        assertEquals(user.getFirstName(), "Janet", "Wrong name");
//        assertEquals(user.getEmail(), "janet.weaver@reqres.in", "Wrong e-mail address");
    }
    @Test
    void findIssuesByAuthor(){
    }
    @Test
    void findIssuesByState() {
    }
     */
}
