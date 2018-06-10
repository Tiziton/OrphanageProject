package pl.polsl.orphanage.web.rest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.polsl.orphanage.OrphanageApplication;
import pl.polsl.orphanage.repository.CaretakerRepository;
import pl.polsl.orphanage.repository.FosterlingRepository;
import pl.polsl.orphanage.repository.HolidayRepository;
import pl.polsl.orphanage.repository.UserRepository;
import pl.polsl.orphanage.service.dto.CaretakerDTO;
import pl.polsl.orphanage.service.dto.FosterlingDTO;
import pl.polsl.orphanage.service.dto.HolidayDTO;
import pl.polsl.orphanage.service.dto.UserDTO;
import pl.polsl.orphanage.web.rest.requestbody.UserRequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrphanageApplication.class)
public class HolidayControllerTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @Autowired
    CaretakerRepository caretakerRepository;

    @Autowired
    CaretakerController caretakerController;

    @Autowired
    FosterlingRepository fosterlingRepository;

    @Autowired
    FosterlingController fosterlingController;

    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    HolidayController holidayController;

    FosterlingDTO fosterlingDTO;

    @Before
    public void setup(){
        UserDTO userDTO = new UserDTO();
        List<String> roles = new ArrayList<>();
        roles.add("CARETAKER");

        CaretakerDTO caretakerDTO = new CaretakerDTO();
        caretakerDTO.setName("Barbara");
        caretakerDTO.setLastname("Nowak");

        fosterlingDTO = new FosterlingDTO();
        fosterlingDTO.setName("Papryk");
        fosterlingDTO.setLegalStatus("Legal");
        fosterlingDTO.setDateBirth(new Date(2016,10,10,0,0));
        fosterlingDTO.setLastname("Wojteaszek");

        try{
            userDTO =userController.createUser(new UserRequestBody(
                    "user",
                    "user",
                    roles)
            ).getBody();

            caretakerDTO.setUserId(userDTO.getId());
            caretakerDTO = caretakerController
                    .createCaretaker(caretakerDTO)
                    .getBody();

            fosterlingDTO.setCaretakerId(caretakerDTO.getId());
            fosterlingDTO = fosterlingController
                    .createFosterling(fosterlingDTO)
                    .getBody();

        } catch(Exception e){
            Assert.assertTrue(false);
        }
    }

    @After
    public void teardown(){
        holidayRepository.deleteAll();
        fosterlingRepository.deleteAll();
        caretakerRepository.deleteAll();
        userRepository.deleteAllByExceptSystemUser();
    }


    @Test
    public void createHoliday(){

        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setIsReturned(true);
        holidayDTO.setStartDate(new Date(2016,10,10,0,0));
        holidayDTO.setEndDate(new Date(2016,10,15,0,0));
        holidayDTO.setNotes("trolololol");
        holidayDTO.setFosterlingId(fosterlingDTO.getId());

        try{
            holidayDTO = holidayController
                    .updateHoliday(holidayDTO)
                    .getBody();
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

        Assert.assertTrue(holidayDTO.getId() > 0);
    }

    @Test
    public void updateHoliday(){


        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setIsReturned(true);
        holidayDTO.setStartDate(new Date(2016,10,10,0,0));
        holidayDTO.setEndDate(new Date(2016,10,15,0,0));
        holidayDTO.setNotes("trolololol");
        holidayDTO.setFosterlingId(fosterlingDTO.getId());

        try{
            holidayDTO = holidayController
                    .updateHoliday(holidayDTO)
                    .getBody();

            holidayDTO.setIsReturned(false);

            holidayDTO = holidayController
                    .updateHoliday(holidayDTO)
                    .getBody();

        } catch (Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(holidayDTO.getIsReturned(),false);
    }

    @Test
    public void deleteHoliday(){
        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setIsReturned(true);
        holidayDTO.setStartDate(new Date(2016,10,10,0,0));
        holidayDTO.setEndDate(new Date(2016,10,15,0,0));
        holidayDTO.setNotes("trolololol");
        holidayDTO.setFosterlingId(fosterlingDTO.getId());

        try{
            holidayDTO = holidayController
                    .updateHoliday(holidayDTO)
                    .getBody();

            holidayController.deleteHoliday(holidayDTO.getId());

        } catch (Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals(holidayRepository.findById(holidayDTO.getId()).isPresent(),false);
    }
}
