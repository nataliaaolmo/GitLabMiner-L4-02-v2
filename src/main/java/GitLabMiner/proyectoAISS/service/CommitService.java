package GitLabMiner.proyectoAISS.service;

import GitLabMiner.proyectoAISS.model.Commit;
import GitLabMiner.proyectoAISS.modelsToGitMiner.CommitGitLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static GitLabMiner.proyectoAISS.utilsPagination.UtilPag.getNextPageUrl;
import static GitLabMiner.proyectoAISS.utilsPagination.UtilPag.getResponseEntity;

@Service
public class CommitService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${gitlab-miner.token}")
    private String token;

    public List<Commit> findCommits(String id){

        String uri= "https://gitlab.com/api/v4/projects/";

        HttpHeaders headers = new HttpHeaders();
        //Setting token header
        if(token!="")
            headers.set("PRIVATE-TOKEN", token);

        //Send Request
        HttpEntity<Commit> request = new HttpEntity<>(null, headers);
        String finalUri = uri + id + "/repository/commits/";
        System.out.println(finalUri);
        ResponseEntity<Commit[]> response = restTemplate
                .exchange(finalUri, HttpMethod.GET, request, Commit[].class);

        return Arrays.asList(response.getBody());
    }

    //conjunto de commits paginados
    public List<Commit> groupAllCommits(String id, Integer since, Integer maxPages) throws HttpClientErrorException {
        List<Commit> commits = new ArrayList<>();
        Integer defaultPages;
        String uri= "https://gitlab.com/api/v4/projects/";
        String finalUri = uri + id + "/repository/commits/";

        if (since != null) {
            finalUri += "?since=" + LocalDateTime.now().minusDays(since);
        } else {
            //añadimos 5 por defecto porque nos lo indica la uri
            finalUri += "?since=" + LocalDateTime.now().minusDays(5);
        }

        ResponseEntity<Commit[]> response = getResponseEntity(finalUri, Commit[].class);
        //paginamos commits
        List<Commit> pageCommits = Arrays.stream(response.getBody()).toList();
        commits.addAll(pageCommits);

        //2..n pages
        String nextPageURL = getNextPageUrl(response.getHeaders());

        if(maxPages!=null){
            defaultPages=maxPages;
        }
        else{
            defaultPages=2;
        }

        int page = 2;
        while (nextPageURL != null && page <= defaultPages) {
            response = getResponseEntity(nextPageURL,Commit[].class);
            pageCommits = Arrays.stream(response.getBody()).toList();
            commits.addAll(pageCommits);

            nextPageURL = getNextPageUrl(response.getHeaders());
            page++;

        }
        return commits;

    }

}
