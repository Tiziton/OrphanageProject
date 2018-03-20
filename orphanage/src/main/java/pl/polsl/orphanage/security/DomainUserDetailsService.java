package pl.polsl.orphanage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.polsl.orphanage.domain.User;
import pl.polsl.orphanage.repository.UserRepository;

import java.util.Locale;
import java.util.Optional;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> user = userRepository.findByUsername(lowercaseLogin);
        if( user.isPresent())  {
            return new DomainUserDetails(user.get());
        }

        throw new UsernameNotFoundException(login);
    }
}
