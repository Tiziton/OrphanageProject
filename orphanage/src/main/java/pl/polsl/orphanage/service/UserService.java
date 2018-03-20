package pl.polsl.orphanage.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.orphanage.domain.Authority;
import pl.polsl.orphanage.domain.User;
import pl.polsl.orphanage.repository.AuthorityRepository;
import pl.polsl.orphanage.repository.UserRepository;
import pl.polsl.orphanage.service.dto.UserDTO;
import pl.polsl.orphanage.service.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Interface for managing User.
 */
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       AuthorityRepository authorityRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * Create user
     *
     * @param userDTO the user entity
     * @param password the password of user
     * @return the new user entity
     */
    public UserDTO createUser(UserDTO userDTO, String password) {

        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);

        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(encryptedPassword);

        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                    .map(authorityRepository::findOneByName)
                    .collect(Collectors.toSet());
            newUser.setAuthorities(authorities);
        }

        return userMapper.userToUserDTO(userRepository.save(newUser));

    }

    /**
     * Change password of user
     *
     * @param username the user`s login
     * @param password the user`s password
     */
    public void changePassword(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            String encryptedPassword = passwordEncoder.encode(password);

            User user = userOptional.get();
            user.setPassword(encryptedPassword);

            userRepository.save(user);
        }
    }

    /**
     * Get user by username
     *
     * @param username the user`s login
     * @return the user entity
     */
    public UserDTO getUser(String username){
        return userMapper.userToUserDTO(userRepository.findByUsername(username).get());

    }

    /**
     * Get all of users
     *
     * @return the user
     */
    public List<UserDTO> getAllUsers(){
        return userMapper.usersToUserDTOs(userRepository.findAll());
    }

    /**
     * Delete the user by username
     *
     * @param username the user`s login
     */
    public void deleteUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            userRepository.delete(user.get());
        }
    }
}
