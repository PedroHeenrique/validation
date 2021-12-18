package validationpojos.springvalidation.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import validationpojos.springvalidation.model.UserModel;
import validationpojos.springvalidation.repository.UserRepository;

import java.util.Optional;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByLogin(username);
        if(user.isPresent()){
            return user.get();

        }

        throw new UsernameNotFoundException("Dados Invalidos!");
    }
}
