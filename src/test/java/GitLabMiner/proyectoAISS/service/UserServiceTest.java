package GitLabMiner.proyectoAISS.service;

import GitLabMiner.proyectoAISS.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService; //creamos instancia del servicio que hemos creado y será necesario para invocar la operacion

    @Test
    @DisplayName("Get user")
        // https://gitlab.com/api/v4/users
    void findUser(){
        User user = userService.findUser("14535304");
        assertEquals(user.getName(), "sunil kumar", "Wrong name");
        assertEquals(user.getWebUrl(), "https://gitlab.com/sunil.expertrating", "Wrong web-url");
    }
}
