package pl.polsl.orphanage.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.orphanage.repository.UserRepository;
import pl.polsl.orphanage.security.AuthoritiesConstants;
import pl.polsl.orphanage.service.UserService;
import pl.polsl.orphanage.service.dto.UserDTO;
import pl.polsl.orphanage.web.rest.errors.BadRequestAlertException;
import pl.polsl.orphanage.web.rest.errors.LoginAlreadyUsedException;
import pl.polsl.orphanage.web.rest.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;
import java.util.List;
import pl.polsl.orphanage.web.rest.requestbody.UserRequestBody;

/**
 * REST controller for managing User.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    private UserRepository userRepository;

    private static final String ENTITY_NAME = "user";

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /user  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     //* @param userDTO the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequestBody bodyRequest) throws URISyntaxException {

        if (userRepository.findByUsername(bodyRequest.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(bodyRequest.getLogin());
            bodyRequest.getRoles()
              .stream()
              .filter(role -> contains(AuthoritiesConstants.class, role))
              .forEach(elem -> userDTO.getAuthorities().add(elem));

            UserDTO newUser = userService.createUser(userDTO, bodyRequest.getPassword());
            return ResponseEntity.created(new URI("/api/users/" + newUser.getUsername()))
              .headers(HeaderUtil.createAlert("userManagement.created", newUser.getUsername()))
              .body(newUser);
        }
    }

    /**
     * GET /user/:login : get the "login" user.
     *
     * @param login the login of the user to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login" user, or with status 404 (Not Found)
     */
    @GetMapping("/user/{login}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        return new ResponseEntity<>(userService.getUser(login), HttpStatus.OK);
    }

    /**
     * GET /user : Get all users
     *
     * @return list of users
     */
    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    /**
     * DELETE /user/:username : delete entity by username
     *
     * @param username the user`s login
     */
    @DeleteMapping("/user/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, username)).build();
    }

    private <E extends Enum<E>> boolean contains(Class<E> _enumClass,
                                                       String value) {
        try {
            return EnumSet.allOf(_enumClass)
                    .contains(Enum.valueOf(_enumClass, value));
        } catch (Exception e) {
            return false;
        }
    }
}
