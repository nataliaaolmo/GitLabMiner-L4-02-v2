package GitLabMiner.proyectoAISS.controller;

import GitLabMiner.proyectoAISS.model.*;
import GitLabMiner.proyectoAISS.modelsToGitMiner.*;
import GitLabMiner.proyectoAISS.service.CommentService;
import GitLabMiner.proyectoAISS.service.CommitService;
import GitLabMiner.proyectoAISS.service.IssueService;
import GitLabMiner.proyectoAISS.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="GitLab Project", description="GitLabMiner OpenAPI")
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
    @Operation(summary= "Retrieve project",
            description= "Get a projects ",
            tags= { "projects", "get" })

    @ApiResponses({
            @ApiResponse(responseCode = "200", description= "Listado de projects",
                    content= { @Content(schema = @Schema(implementation = ProjectGitLab.class), mediaType= "application/json") })
            ,@ApiResponse(responseCode = "404", description="Project no encontrado",
            content= { @Content(schema = @Schema()) })})

    // GET http://localhost:8081/gitlab/{id}[?since=5&updatedAfter=30&maxPages=3]
    @GetMapping("/{id}")
    public ProjectGitLab getProject(@PathVariable String id,
                                    @RequestParam(required = false, name= "sinceCommits") Integer sinceCommits,
                                    @RequestParam(required = false, name= "sinceIssues") Integer sinceIssues,
                                    @RequestParam(required = false, name= "maxPages") Integer maxPages) {
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

    @Operation(summary= "Create project",
            description= "Create a projects ",
            tags= { "projects", "post" })

    @ApiResponses({
            @ApiResponse(responseCode = "201", description= "Listado de projects",
                    content= { @Content(schema = @Schema(implementation = ProjectGitLab.class), mediaType= "application/json") })
            ,@ApiResponse(responseCode = "400", description="Project no encontrado",
            content= { @Content(schema = @Schema()) })})

    //POST http://localhost:8081/gitlab/{id}[?sinceCommits=5&sinceIssues=30&maxPages=2]
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectGitLab SendProject(@PathVariable String id,
                                     @RequestParam(required = false, name="sinceCommits") Integer sinceCommits,
                                     @RequestParam(required = false, name="sinceIssues") Integer sinceIssues,
                                     @RequestParam(required = false, name="maxPages") Integer maxPages){

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
