package pl.polsl.orphanage.web.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.orphanage.OrphanageApplication;
import pl.polsl.orphanage.repository.UserRepository;
import pl.polsl.orphanage.service.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrphanageApplication.class)
public class AccountControllerTests {

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;


    @Before
    public void setup(){

    }

    @Test
    public void createUser(){
        UserDTO userDTO = new UserDTO();
        List<String> roles = new ArrayList<>();
        roles.add("CARETAKER");
        try{
            userDTO =userController.createUser(
                    "user",
                    "user",
                    roles
            ).getBody();
        } catch(Exception e){
            Assert.assertTrue(true);
        }

        Assert.assertTrue(userDTO.getId() != null);
    }
}
