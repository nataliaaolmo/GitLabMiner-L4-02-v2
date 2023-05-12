package GitLabMiner.proyectoAISS.service;

import GitLabMiner.proyectoAISS.model.Author;
import GitLabMiner.proyectoAISS.model.Comment;
import GitLabMiner.proyectoAISS.model.Issue;
import GitLabMiner.proyectoAISS.modelsToGitMiner.CommentGitLab;
import GitLabMiner.proyectoAISS.modelsToGitMiner.IssueGitLab;
import GitLabMiner.proyectoAISS.modelsToGitMiner.UserGitLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static GitLabMiner.proyectoAISS.utilsPagination.UtilPag.getNextPageUrl;
import static GitLabMiner.proyectoAISS.utilsPagination.UtilPag.getResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    static
    CommentService commentService;
    @Autowired
    RestTemplate restTemplate;

    @Value("${gitlab-miner.token}")
    private String token;

    public List<Issue> findIssues(String id) {
        String uri= "https://gitlab.com/api/v4/projects/";

        HttpHeaders headers = new HttpHeaders();

        // Setting token header
        if (token!="")
            headers.set("PRIVATE-TOKEN", token);

        // Send Request
        HttpEntity<Issue> request = new HttpEntity<>(null, headers);
        String finalUri = uri + id + "/issues/";
        System.out.println(finalUri);
        ResponseEntity<Issue[]> response = restTemplate
                .exchange(finalUri, HttpMethod.GET, request, Issue[].class);

        return Arrays.asList(response.getBody());
    }

    //conjunto de issues paginado
    //TODO: investigar como meter los comments dentro de las issues para mandarlo
    public List<Issue> groupAllIssues(String id, Integer sinceIssues, Integer maxPages) throws HttpClientErrorException {
        List<Issue> issues = new ArrayList<>();
        Integer defaultPages;
        String uri = "https://gitlab.com/api/v4/projects/";
        String finalUri = uri + id + "/issues/";

        if (sinceIssues != null) {
            finalUri += "?since=" + LocalDateTime.now().minusDays(sinceIssues);
        } else {
            //añadimos 30 por defecto porque nos lo indica la uri
            finalUri += "?since=" + LocalDateTime.now().minusDays(30);
        }
        ResponseEntity<Issue[]> response = getResponseEntity(finalUri, Issue[].class);
        //paginamos issues
        List<Issue> pageIssues = Arrays.stream(response.getBody()).toList();
        issues.addAll(pageIssues);

        //2..n pages
        String nextPageURL = getNextPageUrl(response.getHeaders());

        if (maxPages != null) {
            defaultPages = maxPages;
        } else {
            defaultPages = 2;
        }

        int page = 2;
        while (nextPageURL != null && page <= defaultPages) {
            response = getResponseEntity(nextPageURL, Issue[].class);
            pageIssues = Arrays.stream(response.getBody()).toList();
            issues.addAll(pageIssues);

            nextPageURL = getNextPageUrl(response.getHeaders());
            page++;

        }
        return issues;
    }



}
