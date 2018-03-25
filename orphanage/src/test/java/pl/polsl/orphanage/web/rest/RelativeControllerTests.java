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
import pl.polsl.orphanage.repository.RelativeRepository;
import pl.polsl.orphanage.repository.UserRepository;
import pl.polsl.orphanage.service.dto.CaretakerDTO;
import pl.polsl.orphanage.service.dto.FosterlingDTO;
import pl.polsl.orphanage.service.dto.RelativeDTO;
import pl.polsl.orphanage.service.dto.UserDTO;
import pl.polsl.orphanage.web.rest.requestbody.RelativeRequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrphanageApplication.class)
public class RelativeControllerTests {

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
    RelativeController relativeController;

    @Autowired
    RelativeRepository relativeRepository;

    FosterlingDTO fosterlingDTO;

    @Before
    public void setup() {
        UserDTO userDTO = new UserDTO();
        List<String> roles = new ArrayList<>();
        roles.add("CARETAKER");

        CaretakerDTO caretakerDTO = new CaretakerDTO();
        caretakerDTO.setName("Barbara");
        caretakerDTO.setLastname("Nowak");

        fosterlingDTO = new FosterlingDTO();
        fosterlingDTO.setName("Papryk");
        fosterlingDTO.setLegalStatus("Legal");
        fosterlingDTO.setDateBirth(new Date(2016, 10, 10, 0, 0));
        fosterlingDTO.setLastname("Wojteaszek");

        try {
            userDTO = userController.createUser(
                    "user",
                    "user",
                    roles
            ).getBody();

            caretakerDTO.setUserId(userDTO.getId());
            caretakerDTO = caretakerController
                    .createCaretaker(caretakerDTO)
                    .getBody();

            fosterlingDTO.setCaretakerId(caretakerDTO.getId());
            fosterlingDTO = fosterlingController
                    .createFosterling(fosterlingDTO)
                    .getBody();

        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @After
    public void teardown() {
        relativeRepository.deleteAll();
        fosterlingRepository.deleteAll();
        caretakerRepository.deleteAll();
        userRepository.deleteAllByExceptSystemUser();
    }

    @Test
    public void createAndUpdateRelativeTest() {
        RelativeDTO relativeDTO = new RelativeDTO();
        relativeDTO.setGender('K');
        relativeDTO.setLastname("Kowalczyk");
        relativeDTO.setName("Ada");
        relativeDTO.setTypeRelation("Matka");

        try {
            relativeDTO = relativeController
                    .updateRelative(relativeDTO)
                    .getBody();

            Assert.assertTrue(relativeDTO.getId() > 0);

            relativeDTO.setName("Justyna");

            relativeDTO = relativeController
                    .updateRelative(relativeDTO)
                    .getBody();

        } catch (Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(relativeDTO.getName().equals("Justyna"));
    }


    @Test
    public void checkRelatives() {

        RelativeDTO relativeDTO = new RelativeDTO();
        relativeDTO.setGender('K');
        relativeDTO.setLastname("Kowalczyk");
        relativeDTO.setName("Ada");
        relativeDTO.setTypeRelation("Matka");

        RelativeDTO relativeDTO2 = new RelativeDTO();
        relativeDTO2.setGender('M');
        relativeDTO2.setLastname("Kowalczyk");
        relativeDTO2.setName("Michal");
        relativeDTO2.setTypeRelation("Ojciec");


        try {
            relativeDTO = relativeController
                    .updateRelative(relativeDTO)
                    .getBody();

            relativeDTO2 = relativeController
                    .updateRelative(relativeDTO2)
                    .getBody();

            relativeController.addRelativeFosterling(
                    new RelativeRequestBody(
                            relativeDTO.getId(),
                            fosterlingDTO.getId()
                    ));

            relativeController.addRelativeFosterling(
                    new RelativeRequestBody(
                            relativeDTO2.getId(),
                            fosterlingDTO.getId()
                    ));

            Assert.assertTrue(relativeController
                    .getAllFosterling(fosterlingDTO.getId())
                    .size() == 2);

            fosterlingController.deleteFosterling(fosterlingDTO.getId());

            Assert.assertTrue(relativeRepository.findAll().size() == 2);
            Assert.assertTrue(fosterlingRepository.findAll().size() == 0);


        } catch (Exception e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void searchByLastname() {
        RelativeDTO relativeDTO = new RelativeDTO();
        relativeDTO.setGender('K');
        relativeDTO.setLastname("Kowalczyk");
        relativeDTO.setName("Ada");
        relativeDTO.setTypeRelation("Matka");

        try {
            relativeDTO = relativeController
                    .createRelative(relativeDTO)
                    .getBody();


            Assert.assertTrue(relativeController
                    .searchRelativeByLastname("ko")
                    .size() == 1);

        } catch (Exception e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void deleteRelative() {
        RelativeDTO relativeDTO = new RelativeDTO();
        relativeDTO.setGender('K');
        relativeDTO.setLastname("Kowalczyk");
        relativeDTO.setName("Ada");
        relativeDTO.setTypeRelation("Matka");

        try {
            relativeDTO = relativeController
                    .createRelative(relativeDTO)
                    .getBody();

            relativeController
                    .addRelativeFosterling(
                            new RelativeRequestBody(
                                    relativeDTO.getId(),
                                    fosterlingDTO.getId()
                            ));

            relativeController.deleteRelative(relativeDTO.getId());

            Assert.assertTrue(
                    fosterlingRepository
                            .findOneById(fosterlingDTO.getId())
                            .getRelatives()
                            .size() == 0
            );

        } catch (Exception e) {
            Assert.assertTrue(false);
        }

    }
}
