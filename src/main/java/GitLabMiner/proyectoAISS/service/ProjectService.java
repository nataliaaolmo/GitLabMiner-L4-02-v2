package GitLabMiner.proyectoAISS.service;

import GitLabMiner.proyectoAISS.model.Commit;
import GitLabMiner.proyectoAISS.model.Issue;
import GitLabMiner.proyectoAISS.model.Project;
import GitLabMiner.proyectoAISS.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    //inyeccion de dependencias: definimos los objetos de los que depende nuestra clase
    //y el framwork automáticamente se recarga de incializarlos y pasárselo a la clase
    //esto permite un código más limpio y facilita algunas pruebas de aplicaciones

    RestTemplate restTemplate;//instancia de restTemplate para interacionar con la API
    CommitService commitService;
    CommentService commentService;
    IssueService issueService;
   @Value("${gitlab-miner.token}")
    private String token;

   //tenga de parametros tmb list commits, list issues, list comments y los llama a estos metodos a través
    //de los service
    public Project findProject(String id){

        String uri= "https://gitlab.com/api/v4/projects/";

        HttpHeaders headers = new HttpHeaders();
        //Setting token header
        if(token!="")
            headers.set("PRIVATE-TOKEN", token);

        //Send Request
        HttpEntity<Project> request = new HttpEntity<>(null, headers);
        ResponseEntity<Project> response = restTemplate
                .exchange(uri + id, HttpMethod.GET, request, Project.class);

        return response.getBody();
    }


    //los paramatreos van con path variable request param=?
    public Project getProjectService(String id, List<Commit> commitsList, List<Issue> issuesList,
                                     List<Comment> commentsList){
        Project project= null;
        commitsList= commitService.findCommits(id);
        issuesList=issueService.findIssues(id);
        for(Issue issue: issuesList){
            commentsList=commentService.findComments(id, issue.getIid().toString() );
        }


        return project;
    }

}
