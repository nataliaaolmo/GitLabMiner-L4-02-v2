package GitLabMiner.proyectoAISS.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import GitLabMiner.proyectoAISS.model.Project;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService; //creamos instancia del servicio que hemps creado y será necesario para invocar la operacion

    @Test
    @DisplayName("Get project")
    //https://gitlab.com/{projects}/:id
    void findProject(){
    Project project = projectService.findProject("45940794");
    assertEquals(project.getName(), "Kelompok0", "Wrong name");
    assertEquals(project.getWebUrl(), "https://gitlab.com/alatif2/kelompok0", "Wrong web-url");
    }


}