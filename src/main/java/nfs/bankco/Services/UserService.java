package nfs.bankco.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nfs.bankco.Repo.BankerRepository;

@Service
public class UserService  implements UserDetailsService{

    @Autowired
    private BankerRepository bankerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return bankerRepository.findUserWithEmail(username);
//                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

}
