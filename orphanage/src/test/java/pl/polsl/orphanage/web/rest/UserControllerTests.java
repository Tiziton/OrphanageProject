package pl.polsl.orphanage.web.rest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.orphanage.OrphanageApplication;
import pl.polsl.orphanage.repository.UserRepository;
import pl.polsl.orphanage.service.dto.UserDTO;
import pl.polsl.orphanage.web.rest.requestbody.UserRequestBody;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrphanageApplication.class)
public class UserControllerTests {

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setup(){
       userRepository.deleteAllByExceptSystemUser();
    }

    @After
    public void oneTearDown(){
        userRepository.deleteAllByExceptSystemUser();
    }

    @Test
    public void createUserTest(){

        UserDTO userDTO = new UserDTO();
        List<String> roles = new ArrayList<>();
        roles.add("CARETAKER");
        try{
            userDTO =userController.createUser(new UserRequestBody(
                    "user",
                    "user",
                    roles)
            ).getBody();
        } catch(Exception e){
            Assert.assertTrue(false);
        }

        Assert.assertTrue(userDTO.getId() > 1);
    }

    @Test
    public void loginUserTest(){
        UserDTO userDTO = new UserDTO();
        List<String> roles = new ArrayList<>();
        roles.add("CARETAKER");
        try{
            userDTO =userController.createUser(new UserRequestBody(
                    "user",
                    "user",
                    roles)
            ).getBody();
        } catch(Exception e){
            Assert.assertTrue(false);
        }

        UserDTO gotUser = userController.getUser("user").getBody();

        Assert.assertEquals(userDTO.getId(),gotUser.getId());
        Assert.assertEquals(userDTO.getUsername(),gotUser.getUsername());
        Assert.assertEquals(userDTO.getAuthorities(),gotUser.getAuthorities());
    }

    @Test
    public void getAllUsersTests(){
        List<String> roles = new ArrayList<>();
        roles.add("CARETAKER");
        List<String> roles2 = new ArrayList<>();
        roles2.add("CARETAKER");
        roles2.add("ADMIN");
        try{
            userController.createUser(new UserRequestBody(
                    "user",
                    "user",
                    roles)
            ).getBody();

            userController.createUser(new UserRequestBody(
                    "user2",
                    "user2",
                    roles2)
            ).getBody();
        } catch(Exception e){
            Assert.assertTrue(false);
        }

        /* 3 cause we count also default system account*/
        Assert.assertTrue(userController.getUsers().getBody().size() == 3);
    }

    @Test
    public void deleteUserTest(){
        UserDTO userDTO = new UserDTO();
        List<String> roles = new ArrayList<>();
        roles.add("CARETAKER");
        try{
            userDTO =userController.createUser(new UserRequestBody(
                    "user",
                    "user",
                    roles)
            ).getBody();
        } catch(Exception e){
            Assert.assertTrue(false);
        }

        userController.deleteUser(userDTO.getUsername());

        Assert.assertFalse(userRepository.findByUsername(userDTO.getUsername()).isPresent());
    }
}
