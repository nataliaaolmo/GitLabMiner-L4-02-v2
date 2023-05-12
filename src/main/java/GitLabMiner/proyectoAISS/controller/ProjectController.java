package GitLabMiner.proyectoAISS.controller;

import GitLabMiner.proyectoAISS.model.*;
import GitLabMiner.proyectoAISS.modelsToGitMiner.*;
import GitLabMiner.proyectoAISS.service.CommentService;
import GitLabMiner.proyectoAISS.service.CommitService;
import GitLabMiner.proyectoAISS.service.IssueService;
import GitLabMiner.proyectoAISS.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@RestController
@RequestMapping("/gitlab")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    CommitService commitService;
    @Autowired
    IssueService issueService;
    @Autowired
    CommentService commentService;
    @Autowired
    RestTemplate restTemplate;

    // GET http://localhost:8081/gitlab/{id}[?since=5&updatedAfter=30&maxPages=3]
    @GetMapping("/{id}")
    public ProjectGitLab getProject(@PathVariable String id,
                                    @RequestParam(required = false, name= "sinceCommits") Integer sinceCommits,
                                    @RequestParam(required = false, name= "sinceIssues") Integer sinceIssues,
                                    @RequestParam(required = false, name= "max_pages") Integer maxPages) {
        //TODO: cuando esté hecho probar a comprobar si no son nulos aquñi en vez de en los métodos de service
        Project project = projectService.findProject(id);
        String projectId = project.getId().toString();
        String projectName = project.getName();
        String project_webUrl = project.getWebUrl();
        //parseamos commits y issues para que tengan el formato de GitMiner
        List<CommitGitLab> commits = commitService.groupAllCommits(id,sinceCommits,maxPages).stream().map(x->parseoCommit(x)).toList();
        List<IssueGitLab> issues = issueService.groupAllIssues(id, sinceIssues, maxPages).stream().map(x->parseoIssue(x,id,maxPages)).toList();

        return new ProjectGitLab(projectId,projectName,project_webUrl,commits,issues);
    }
    private CommitGitLab parseoCommit(Commit commit){
        return new CommitGitLab(commit.getId(),commit.getTitle(),commit.getMessage(),
                commit.getAuthorName(),commit.getAuthorEmail(),commit.getAuthoredDate(),
                commit.getCommitterName(),commit.getCommitterEmail(),commit.getCommittedDate(),commit.getWebUrl());

    }

    //parseamos issues para añadirle los comentarios de esas issues. Además, a partir de la propiedad Author,
    // de Issue encontraremos los parámetros que necesitamos para User según el modelo de datos
    private IssueGitLab parseoIssue(Issue issue, String id, Integer maxPages){
        String issueId = issue.getId().toString();
        String issueIid = issue.getIid().toString();
        String title = issue.getTitle();
        String description = issue.getDescription();
        String state = issue.getState();
        String created_at = issue.getCreatedAt();
        String updated_at = issue.getUpdatedAt();
        String closed_at = issue.getClosedAt();
        List<String> labels = issue.getLabels();
        //cogemos las proiedades que nos interesan según el modelo de User de una issue
        UserGitLab author = new UserGitLab(issue.getAuthor().getId().toString(), issue.getAuthor().getUsername(),issue.getAuthor().getName(),issue.getAuthor().getAvatarUrl(),issue.getAuthor().getWebUrl());

        //coger el primer assigne que tenemos en issue
        UserGitLab assignee = issue.getAssignee()==null?null:new UserGitLab(issue.getAssignee().getId().toString(), issue.getAssignee().getUsername(), issue.getAssignee().getName(), issue.getAssignee().getAvatarUrl(), issue.getAssignee().getWebUrl());
        //GitLabMinerUser assignee = null;
        Integer upvotes = issue.getUpvotes();
        Integer downvotes = issue.getDownvotes();
        String web_url = issue.getWebUrl();
        List<CommentGitLab> comments= commentService.groupIssueComments(id,issue.getIid().toString(),maxPages).stream().map(x->parseaComment(x)).toList();


        return new IssueGitLab(issueId, issueIid, title, description, state, created_at, updated_at, closed_at, labels,
                author,assignee,upvotes,downvotes,web_url,comments);
    }

    private CommentGitLab parseaComment(Comment comment){
        String id = comment.getId().toString();
        String body = comment.getBody();
        UserGitLab author = new UserGitLab(comment.getAuthor().getId().toString(), comment.getAuthor().getUsername(),comment.getAuthor().getName(),comment.getAuthor().getAvatarUrl(),comment.getAuthor().getWebUrl());
        String created_at = comment.getCreatedAt();
        String updated_at = comment.getUpdatedAt();
        return new CommentGitLab(id, body, author, created_at, updated_at);
    }

    //POST http://localhost:8081/gitlab/{id}[?sinceCommits=5&sinceIssues=30&maxPages=2]
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectGitLab SendProject(@PathVariable String id,
                                     @RequestParam(required = false, name="sinceCommits") Integer sinceCommits,
                                     @RequestParam(required = false, name="sinceIssues") Integer sinceIssues,
                                     @RequestParam(required = false, name="max_pages") Integer maxPages){

        Project project = projectService.findProject(id);
        String projectId = project.getId().toString();
        String projectName = project.getName();
        String project_webUrl = project.getWebUrl();
        List<CommitGitLab> commits = commitService.groupAllCommits(id,sinceCommits,maxPages).stream().map(x->parseoCommit(x)).toList();
        List<IssueGitLab> issues = issueService.groupAllIssues(id, sinceIssues, maxPages).stream().map(x->parseoIssue(x, id, maxPages)).toList();

        ProjectGitLab proyectoParseado = new ProjectGitLab(projectId,projectName,project_webUrl,commits,issues);

        ProjectGitLab toGitMinerProject = restTemplate.postForObject("http://localhost:8080/gitminer/projects", proyectoParseado, ProjectGitLab.class);

        return toGitMinerProject;

    }
    /*
    public ProjectController(ProjectService projectService){

    } Esto lo usaba el profesor, hago algo con esto?

     */


}
