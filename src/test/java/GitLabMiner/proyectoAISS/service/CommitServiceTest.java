package GitLabMiner.proyectoAISS.service;

import GitLabMiner.proyectoAISS.model.Commit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CommitServiceTest {
    @Autowired
    CommitService commitService;

    @Test
    @DisplayName("Get commits")
        //https://gitlab.com/{projects}/:id/{repository}/{commits}
    void findCommit(){
      List<Commit> commits = commitService.findCommits("45940794");
      assertTrue(commits.size()>0, "Wrong size");
    }
}
