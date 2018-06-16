//package pl.polsl.orphanage.web.rest;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import pl.polsl.orphanage.OrphanageApplication;
//import pl.polsl.orphanage.repository.CaretakerRepository;
//import pl.polsl.orphanage.repository.DocumentRepository;
//import pl.polsl.orphanage.repository.FosterlingRepository;
//import pl.polsl.orphanage.repository.UserRepository;
//import pl.polsl.orphanage.service.dto.CaretakerDTO;
//import pl.polsl.orphanage.service.dto.DocumentDTO;
//import pl.polsl.orphanage.service.dto.FosterlingDTO;
//import pl.polsl.orphanage.service.dto.UserDTO;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OrphanageApplication.class)
//public class DocumentControllerTests {
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
//    @Autowired
//    DocumentController documentController;
//
//    @Autowired
//    DocumentRepository documentRepository;
//
//    FosterlingDTO fosterlingDTO;
//
//    @Before
//    public void setup(){
//        UserDTO userDTO = new UserDTO();
//        List<String> roles = new ArrayList<>();
//        roles.add("CARETAKER");
//
//        CaretakerDTO caretakerDTO = new CaretakerDTO();
//        caretakerDTO.setName("Barbara");
//        caretakerDTO.setLastname("Nowak");
//
//        fosterlingDTO = new FosterlingDTO();
//        fosterlingDTO.setName("Papryk");
//        fosterlingDTO.setLegalStatus("Legal");
//        fosterlingDTO.setDateBirth(new Date(2016,10,10,0,0));
//        fosterlingDTO.setLastname("Wojteaszek");
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
//                    .createCaretaker(caretakerDTO)
//                    .getBody();
//
//            fosterlingDTO.setCaretakerId(caretakerDTO.getId());
//            fosterlingDTO = fosterlingController
//                    .createFosterling(fosterlingDTO)
//                    .getBody();
//
//        } catch(Exception e){
//            Assert.assertTrue(false);
//        }
//    }
//
//    @After
//    public void teardown(){
//        documentRepository.deleteAll();
//        fosterlingRepository.deleteAll();
//        caretakerRepository.deleteAll();
//        userRepository.deleteAllByExceptSystemUser();
//    }
//
//    @Test
//    public void createHoliday(){
//
//        DocumentDTO documentDTO = new DocumentDTO();
//        documentDTO.setDate(new Date(2016,10,10,0,0));
//        documentDTO.setType("zabawa na calego");
//        documentDTO.setFosterlingId(fosterlingDTO.getId());
//
//        try{
//            documentDTO = documentController
//                    .updateDocument(documentDTO)
//                    .getBody();
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//
//        Assert.assertTrue(documentDTO.getId() > 0);
//    }
//
//    @Test
//    public void updateHoliday(){
//
//
//        DocumentDTO documentDTO = new DocumentDTO();
//        documentDTO.setDate(new Date(2016,10,10,0,0));
//        documentDTO.setType("zabawa na calego");
//        documentDTO.setFosterlingId(fosterlingDTO.getId());
//
//        try{
//            documentDTO = documentController
//                    .updateDocument(documentDTO)
//                    .getBody();
//
//            documentDTO.setType("Alkohol pijo");
//
//            documentDTO = documentController
//                    .updateDocument(documentDTO)
//                    .getBody();
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//        Assert.assertEquals(documentDTO.getType(),"Alkohol pijo");
//    }
//
//    @Test
//    public void deleteHoliday(){
//        DocumentDTO documentDTO = new DocumentDTO();
//        documentDTO.setDate(new Date(2016,10,10,0,0));
//        documentDTO.setType("zabawa na calego");
//        documentDTO.setFosterlingId(fosterlingDTO.getId());
//
//        try{
//            documentDTO = documentController
//                    .createDocument(documentDTO)
//                    .getBody();
//
//            documentController.deleteDocument(documentDTO.getId());
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//        Assert.assertEquals(documentRepository.findById(documentDTO.getId()).isPresent(),false);
//    }
//}
