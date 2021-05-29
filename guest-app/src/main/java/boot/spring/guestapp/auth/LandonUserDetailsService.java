package boot.spring.guestapp.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandonUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;


    public LandonUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        if(null==user) throw new UsernameNotFoundException(" cannot find username: " + username);
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        return new LandonUserPrinciple(user, authGroups);
    }
}
