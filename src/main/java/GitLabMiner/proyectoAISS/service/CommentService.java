package GitLabMiner.proyectoAISS.service;

import GitLabMiner.proyectoAISS.model.Author;
import GitLabMiner.proyectoAISS.model.Comment;
import GitLabMiner.proyectoAISS.modelsToGitMiner.CommentGitLab;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static GitLabMiner.proyectoAISS.utilsPagination.UtilPag.getNextPageUrl;
import static GitLabMiner.proyectoAISS.utilsPagination.UtilPag.getResponseEntity;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${gitlab-miner.token}")
    private String token;

    public List<Comment> findComments(String id, String issue_iid) {

        String uri= "https://gitlab.com/api/v4/projects/";

        HttpHeaders headers = new HttpHeaders();

        // Setting token header
        if (token!="")
            headers.set("PRIVATE-TOKEN", token);

        // Send Request
        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        String finalUri = uri + id + "/issues/" + issue_iid + "/notes";
        System.out.println(finalUri);
        ResponseEntity<Comment[]> response = restTemplate
                .exchange(finalUri, HttpMethod.GET, request, Comment[].class);

        return Arrays.asList(response.getBody());
    }

    public List<Comment> groupIssueComments(String id, String issue_iid, Integer maxPages) throws HttpClientErrorException {
        List<Comment> comments = new ArrayList<>();
        Integer defaultPages;
        String uri = "https://gitlab.com/api/v4/projects/" + id + "/issues/" + issue_iid + "/notes";

        ResponseEntity<Comment[]> response = getResponseEntity(uri, Comment[].class);
        List<Comment> pageComments = Arrays.stream(response.getBody()).toList();
        comments.addAll(pageComments);

        //2..n pages
        String nextPageURL = getNextPageUrl(response.getHeaders());

        if(maxPages!=null){
            defaultPages=maxPages;
        }
        else{
            defaultPages=2;
        }

        int page = 2;
        while(nextPageURL != null && page <= defaultPages){
            response = getResponseEntity(nextPageURL,Comment[].class);
            pageComments = Arrays.stream(response.getBody()).toList();
            comments.addAll(pageComments);

            nextPageURL = getNextPageUrl(response.getHeaders());
            page++;
        }

        return comments;
    }

}
