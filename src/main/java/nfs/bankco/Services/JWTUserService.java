package nfs.bankco.Services;


import nfs.bankco.Entity.Banker;
import nfs.bankco.Entity.Role;
import nfs.bankco.Repo.BankerRepository;
import nfs.bankco.Utils.form.PasswordUtility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nfs.bankco.Config.JWTTokenProvider;

import java.util.ArrayList;
import java.util.List;

@Service
public class JWTUserService implements UserDetailsService {
	
	@Autowired
	  private BankerRepository bankerRepository;

	  @Autowired
	  private PasswordEncoder passwordEncoder;

	  @Autowired
	  private JWTTokenProvider jwtTokenProvider;

	  @Autowired
	  private AuthenticationManager authenticationManager;

	  public String signin(String email, String password) {
	    try {
	      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		  Banker banker = null;
		  banker = bankerRepository.findUserWithEmail(email);
		  if (banker != null) {
			  boolean isPasswordValid = PasswordUtility.validatePassword(password, banker.getPassword());
			  System.out.println(isPasswordValid);
			  if (isPasswordValid) {
				  String token = jwtTokenProvider.createToken(email, banker.getRole(), banker.getId());

				  return token;
			  }
		  }

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "";
	  }

	  public String signup(Banker banker) {
		  banker.setPassword(passwordEncoder.encode(banker.getPassword()));
		  List<Role> roles = new ArrayList<>();
		  roles.add(Role.ROLE_USER);
		  banker.setRole(roles);
	      bankerRepository.save(banker);
	      return jwtTokenProvider.createToken(banker.getUsername(), banker.getRole(), banker.getId());
	  }

	public List<Banker> getAll(){
		return bankerRepository.getAll();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		  System.out.println(email);
		  Banker banker = bankerRepository.findUserWithEmail(email);
//				.orElseThrow(() -> new UsernameNotFoundException("bad credentials"));
		return new org.springframework.security.core.userdetails.User(
				banker.getEmail(), banker.getPassword(), banker.getRole());
	}

}
