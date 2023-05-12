package GitLabMiner.proyectoAISS.service;
import GitLabMiner.proyectoAISS.model.Project;
import GitLabMiner.proyectoAISS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${gitlab-miner.token}")
    private String token;

    public User findUser(String id){

        String uri= "https://gitlab.com/api/v4/users/";

        HttpHeaders headers = new HttpHeaders();
        //Setting token header
        if(token!="")
            headers.set("PRIVATE-TOKEN", token);

        //Send Request
        HttpEntity<User> request = new HttpEntity<>(null, headers);
        ResponseEntity<User> response = restTemplate
                .exchange(uri + id, HttpMethod.GET, request, User.class);

        return response.getBody();
    }
}
