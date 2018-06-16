//package pl.polsl.orphanage.web.rest;
//
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import pl.polsl.orphanage.OrphanageApplication;
//import pl.polsl.orphanage.domain.Caretaker;
//import pl.polsl.orphanage.repository.CaretakerRepository;
//import pl.polsl.orphanage.repository.UserRepository;
//import pl.polsl.orphanage.service.dto.CaretakerDTO;
//import pl.polsl.orphanage.service.dto.UserDTO;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OrphanageApplication.class)
//public class CaretakerControllerTests {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    UserController userController;
//
//    @Autowired
//    CaretakerRepository caretakerRepository;
//
//    @Autowired
//    CaretakerController caretakerController;
//
//    @Before
//    public void setup() {
//        caretakerRepository.deleteAll();
//        userRepository.deleteAllByExceptSystemUser();
//    }
//
//    @After
//    public void oneTimeTeardown(){
//        caretakerRepository.deleteAll();
//        userRepository.deleteAllByExceptSystemUser();
//    }
//
//    @Test
//    public void createCaretakerAndGetByUserIdTest(){
//
//        UserDTO userDTO = new UserDTO();
//        List<String> roles = new ArrayList<>();
//        roles.add("CARETAKER");
//
//        CaretakerDTO caretakerDTO = new CaretakerDTO();
//        caretakerDTO.setName("Barbara");
//        caretakerDTO.setLastname("Nowak");
//
//        CaretakerDTO caretakerDTO2 = new CaretakerDTO();
//
//        try{
//            userDTO =userController.createUser(
//                    "user",
//                    "user",
//                    roles
//            ).getBody();
//
//            caretakerDTO.setUserId(userDTO.getId());
//            caretakerDTO = caretakerController
//                    .updateCaretaker(caretakerDTO)
//                    .getBody();
//
//            caretakerDTO2 = caretakerController.getCaretakerByUser(userDTO.getId());
//
//        } catch(Exception e){
//            Assert.assertTrue(false);
//        }
//
//        Caretaker caretaker = caretakerRepository.findOneByUserId(userDTO.getId());
//
//        Assert.assertEquals(caretaker.getName() ,"Barbara");
//        Assert.assertEquals(caretaker.getLastname() ,"Nowak");
//
//        Assert.assertEquals(caretakerDTO2.getName() ,"Barbara");
//        Assert.assertEquals(caretakerDTO2.getLastname() ,"Nowak");
//
//    }
//
//
//    @Test
//    public void updateCaretakerTest(){
//        UserDTO userDTO = new UserDTO();
//        List<String> roles = new ArrayList<>();
//        roles.add("CARETAKER");
//
//        CaretakerDTO caretakerDTO = new CaretakerDTO();
//        caretakerDTO.setName("Barbara");
//        caretakerDTO.setLastname("Nowak");
//
//        try{
//            userDTO =userController.createUser(
//                    "user",
//                    "user",
//                    roles
//            ).getBody();
//
//            caretakerDTO.setUserId(userDTO.getId());
//            caretakerDTO = caretakerController
//                    .updateCaretaker(caretakerDTO)
//                    .getBody();
//
//            caretakerDTO.setLastname("Kowalska");
//
//            caretakerDTO = caretakerController
//                    .updateCaretaker(caretakerDTO)
//                    .getBody();
//        } catch(Exception e){
//            Assert.assertTrue(false);
//        }
//
//        Caretaker caretaker = caretakerRepository.findOneByUserId(userDTO.getId());
//
//        Assert.assertEquals(caretaker.getName() ,"Barbara");
//        Assert.assertEquals(caretaker.getLastname() ,"Kowalska");
//    }
//
//    @Test
//    public void deleteCaretakerById(){
//        UserDTO userDTO = new UserDTO();
//        List<String> roles = new ArrayList<>();
//        roles.add("CARETAKER");
//
//        CaretakerDTO caretakerDTO = new CaretakerDTO();
//        caretakerDTO.setName("Barbara");
//        caretakerDTO.setLastname("Nowak");
//
//        try{
//            userDTO =userController.createUser(
//                    "user",
//                    "user",
//                    roles
//            ).getBody();
//
//            caretakerDTO.setUserId(userDTO.getId());
//            caretakerDTO = caretakerController
//                    .updateCaretaker(caretakerDTO)
//                    .getBody();
//
//        } catch(Exception e){
//            Assert.assertTrue(false);
//        }
//
//        caretakerController.deleteCaretaker(caretakerDTO.getId());
//
//        Assert.assertEquals(caretakerRepository.findAll().size(),0);
//    }
//}
