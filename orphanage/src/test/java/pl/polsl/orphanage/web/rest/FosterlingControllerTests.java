//package pl.polsl.orphanage.web.rest;
//
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import pl.polsl.orphanage.OrphanageApplication;
//import pl.polsl.orphanage.domain.Fosterling;
//import pl.polsl.orphanage.repository.CaretakerRepository;
//import pl.polsl.orphanage.repository.FosterlingRepository;
//import pl.polsl.orphanage.repository.UserRepository;
//import pl.polsl.orphanage.service.dto.CaretakerDTO;
//import pl.polsl.orphanage.service.dto.FosterlingDTO;
//import pl.polsl.orphanage.service.dto.UserDTO;
//import pl.polsl.orphanage.web.rest.requestbody.SibilingRequestBody;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OrphanageApplication.class)
//public class FosterlingControllerTests {
//
//    //region props
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
//    @Autowired
//    FosterlingRepository fosterlingRepository;
//
//    @Autowired
//    FosterlingController fosterlingController;
//
//    static CaretakerDTO caretakerDTO;
//
//    //endregion
//
//    //region init methods
//
//    @Before
//    public void setup(){
//        UserDTO userDTO = new UserDTO();
//        List<String> roles = new ArrayList<>();
//        roles.add("CARETAKER");
//
//        caretakerDTO = new CaretakerDTO();
//        caretakerDTO.setName("Barbara");
//        caretakerDTO.setLastname("Nowak");
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
//    }
//    @After
//    public void teardown(){
//        fosterlingRepository.deleteAll();
//        caretakerRepository.deleteAll();
//        userRepository.deleteAllByExceptSystemUser();
//    }
//
//    //endregion
//
//    @Test
//    public void createFosterlingTest(){
//
//        FosterlingDTO fosterlingDTO = new FosterlingDTO();
//        fosterlingDTO.setDateBirth(new Date());
//        fosterlingDTO.setLastname("Biedak");
//        fosterlingDTO.setLegalStatus("Legal");
//        fosterlingDTO.setName("Adam");
//        fosterlingDTO.setCaretakerId(caretakerDTO.getId());
//
//        try{
//
//            fosterlingDTO = fosterlingController
//                    .updateFosterling(fosterlingDTO)
//                    .getBody();
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//
//        Assert.assertTrue(fosterlingDTO.getId() > 0);
//    }
//
//    @Test
//    public void updateFosterlingTest(){
//
//        FosterlingDTO fosterlingDTO = new FosterlingDTO();
//        fosterlingDTO.setDateBirth(new Date());
//        fosterlingDTO.setLastname("Biedak");
//        fosterlingDTO.setLegalStatus("Legal");
//        fosterlingDTO.setName("Adam");
//        fosterlingDTO.setCaretakerId(caretakerDTO.getId());
//
//        try{
//
//            fosterlingDTO = fosterlingController
//                    .createFosterling(fosterlingDTO)
//                    .getBody();
//
//            fosterlingDTO.setName("Marcin");
//
//            fosterlingDTO = fosterlingController
//                    .updateFosterling(fosterlingDTO)
//                    .getBody();
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//
//        Assert.assertEquals(fosterlingDTO.getName(), "Marcin");
//    }
//
//    @Test
//    public void getFosterlingsTest(){
//
//        FosterlingDTO fosterlingDTO = new FosterlingDTO();
//        fosterlingDTO.setDateBirth(new Date());
//        fosterlingDTO.setLastname("Biedak");
//        fosterlingDTO.setLegalStatus("Legal");
//        fosterlingDTO.setName("Artur");
//        fosterlingDTO.setCaretakerId(caretakerDTO.getId());
//
//        FosterlingDTO fosterlingDTO2 = new FosterlingDTO();
//        fosterlingDTO2.setDateBirth(new Date());
//        fosterlingDTO2.setLastname("Biedak");
//        fosterlingDTO2.setLegalStatus("Legal");
//        fosterlingDTO2.setName("Adam");
//        fosterlingDTO2.setCaretakerId(caretakerDTO.getId());
//
//        try{
//
//            fosterlingDTO = fosterlingController
//                    .createFosterling(fosterlingDTO)
//                    .getBody();
//
//            fosterlingController
//                    .createFosterling(fosterlingDTO2)
//                    .getBody();
//
//            Assert.assertTrue(fosterlingController.getAllFosterling().size() == 2);
//
//            Assert.assertTrue(
//                    fosterlingController
//                            .getFosterlingById(fosterlingDTO.getId())
//                            .getName().equals("Artur")
//                    );
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//
//
//    }
//
//    @Test
//    public void deteleFosterlingById(){
//
//        FosterlingDTO fosterlingDTO = new FosterlingDTO();
//        fosterlingDTO.setDateBirth(new Date());
//        fosterlingDTO.setLastname("Biedak");
//        fosterlingDTO.setLegalStatus("Legal");
//        fosterlingDTO.setName("Adam");
//        fosterlingDTO.setCaretakerId(caretakerDTO.getId());
//
//        try{
//
//            fosterlingDTO = fosterlingController
//                    .createFosterling(fosterlingDTO)
//                    .getBody();
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//
//        fosterlingController.deleteFosterling(fosterlingDTO.getId());
//
//        Assert.assertTrue(
//                fosterlingRepository
//                        .findOneById(fosterlingDTO.getId()) == null);
//    }
//
//    @Test
//    public void manageSibiling(){
//
//        FosterlingDTO fosterlingDTO = new FosterlingDTO();
//        fosterlingDTO.setDateBirth(new Date());
//        fosterlingDTO.setLastname("Biedak");
//        fosterlingDTO.setLegalStatus("Legal");
//        fosterlingDTO.setName("Adam");
//        fosterlingDTO.setCaretakerId(caretakerDTO.getId());
//
//        FosterlingDTO sibilingDTO = new FosterlingDTO();
//        sibilingDTO.setDateBirth(new Date());
//        sibilingDTO.setLastname("Biedak");
//        sibilingDTO.setLegalStatus("Legal");
//        sibilingDTO.setName("Adam");
//        sibilingDTO.setCaretakerId(caretakerDTO.getId());
//
//        Fosterling fosterling;
//        Fosterling sibiling;
//
//        try{
//
//            fosterlingDTO = fosterlingController
//                    .createFosterling(fosterlingDTO)
//                    .getBody();
//
//            sibilingDTO = fosterlingController
//                    .createFosterling(sibilingDTO)
//                    .getBody();
//
//            fosterlingController.addSibiling(
//                    new SibilingRequestBody(
//                            fosterlingDTO.getId(),
//                            sibilingDTO.getId()
//                    ));
//
//            fosterling =
//                    fosterlingRepository.findOneById(fosterlingDTO.getId());
//
//            Assert.assertTrue(fosterling.getSibilings().size() == 1);
//
//            sibiling =
//                    fosterlingRepository.findOneById(sibilingDTO.getId());
//
//            Assert.assertTrue(sibiling.getSibilings().size() == 1);
//
//            // Check if DTO have sibiling object
//            Assert.assertTrue(fosterlingController
//                        .getAllFosterlingByCaretaker(caretakerDTO.getId())
//                        .size() == 2);
//
//            fosterlingController.deleteSibiling(
//                    new SibilingRequestBody(
//                            fosterlingDTO.getId(),
//                            sibilingDTO.getId()
//                    ));
//
//            fosterling =
//                    fosterlingRepository.findOneById(fosterlingDTO.getId());
//
//            Assert.assertTrue(fosterling.getSibilings().size() == 0);
//
//            sibiling =
//                    fosterlingRepository.findOneById(sibilingDTO.getId());
//
//            Assert.assertTrue(sibiling.getSibilings().size() == 0);
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//    }
//
//    @Test
//    public void searchFosterling(){
//
//        Date date = new Date(2014, 6, 20, 0, 0);
//
//
//        FosterlingDTO fosterlingDTO = new FosterlingDTO();
//        fosterlingDTO.setDateBirth(date);
//        fosterlingDTO.setLastname("Biedak");
//        fosterlingDTO.setLegalStatus("Legal");
//        fosterlingDTO.setName("Adam");
//        fosterlingDTO.setCaretakerId(caretakerDTO.getId());
//
//        try{
//
//            fosterlingController
//                    .createFosterling(fosterlingDTO)
//                    .getBody();
//
//            Assert.assertTrue(
//                    fosterlingController
//                        .searchFosterling("ied",null,null)
//                        .size() == 1);
//
//            Assert.assertTrue(
//                    fosterlingController
//                            .searchFosterling(
//                                    null,
//                                    new Date(2014, 6, 19, 0, 0),
//                                    new Date(2014, 6, 21, 0, 0))
//                            .size() == 1);
//
//            Assert.assertTrue(
//                    fosterlingController
//                            .searchFosterling(
//                                    null,
//                                    new Date(2015, 6, 19, 0, 0),
//                                    new Date(2015, 6, 21, 0, 0))
//                            .size() == 0);
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//    }
//}
