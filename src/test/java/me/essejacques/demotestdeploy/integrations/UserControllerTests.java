package me.essejacques.demotestdeploy.integrations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect( status().isOk())
                .andExpect( jsonPath( "$.length()").value(3))
                .andExpect(
                        jsonPath( "$.[0].name").value("Jordane")
                );

    }

    @Test
    public  void createUser() throws Exception {
        mockMvc.perform(
                post("/users"  )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"Saint\"," +
                                "\"email\":\"Saint@gmail.com\"," +
                                "\"password\":\"password\"" +
                                "}")
        )
        .andExpect( status().isOk())
        .andExpect( jsonPath( "$.name").value("Saint"));
    }
}
