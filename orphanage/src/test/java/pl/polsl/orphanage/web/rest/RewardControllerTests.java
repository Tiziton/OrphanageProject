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
//import pl.polsl.orphanage.repository.FosterlingRepository;
//import pl.polsl.orphanage.repository.RewardRepository;
//import pl.polsl.orphanage.repository.UserRepository;
//import pl.polsl.orphanage.service.dto.CaretakerDTO;
//import pl.polsl.orphanage.service.dto.FosterlingDTO;
//import pl.polsl.orphanage.service.dto.RewardDTO;
//import pl.polsl.orphanage.service.dto.UserDTO;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OrphanageApplication.class)
//public class RewardControllerTests {
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
//    RewardController rewardController;
//
//    @Autowired
//    RewardRepository rewardRepository;
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
//        fosterlingDTO.setCaretakerId(caretakerDTO.getId());
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
//        rewardRepository.deleteAll();
//        fosterlingRepository.deleteAll();
//        caretakerRepository.deleteAll();
//        userRepository.deleteAllByExceptSystemUser();
//    }
//
//    @Test
//    public void createHoliday(){
//
//        RewardDTO rewardDTO = new RewardDTO();
//        rewardDTO.setDate(new Date(2016,10,10,0,0));
//        rewardDTO.setType("heheh");
//        rewardDTO.setNotes("trolololol");
//        rewardDTO.setFosterlingId(fosterlingDTO.getId());
//
//        try{
//            rewardDTO = rewardController
//                    .updateReward(rewardDTO)
//                    .getBody();
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//
//        Assert.assertTrue(rewardDTO.getId() > 0);
//    }
//
//    @Test
//    public void updateHoliday(){
//
//
//        RewardDTO rewardDTO = new RewardDTO();
//        rewardDTO.setDate(new Date(2016,10,10,0,0));
//        rewardDTO.setType("heheh");
//        rewardDTO.setNotes("trolololol");
//        rewardDTO.setFosterlingId(fosterlingDTO.getId());
//
//        try{
//            rewardDTO = rewardController
//                    .updateReward(rewardDTO)
//                    .getBody();
//
//            rewardDTO.setType("meh");
//
//            rewardDTO = rewardController
//                    .updateReward(rewardDTO)
//                    .getBody();
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//        Assert.assertTrue(rewardDTO.getType().equals("meh"));
//    }
//
//    @Test
//    public void deleteHoliday(){
//        RewardDTO rewardDTO = new RewardDTO();
//        rewardDTO.setDate(new Date(2016,10,10,0,0));
//        rewardDTO.setType("heheh");
//        rewardDTO.setNotes("trolololol");
//        rewardDTO.setFosterlingId(fosterlingDTO.getId());
//
//        try{
//            rewardDTO = rewardController
//                    .updateReward(rewardDTO)
//                    .getBody();
//
//            rewardController.deleteReward(rewardDTO.getId());
//
//        } catch (Exception e) {
//            Assert.assertTrue(false);
//        }
//        Assert.assertEquals(rewardRepository.findById(rewardDTO.getId()).isPresent(),false);
//    }
//}
