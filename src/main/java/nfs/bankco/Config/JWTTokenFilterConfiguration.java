package nfs.bankco.Config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public class JWTTokenFilterConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	private JWTTokenProvider provider;
	
	public JWTTokenFilterConfiguration(JWTTokenProvider p) {
		this.provider = p;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		JWTTokenFilter filter = new JWTTokenFilter(provider);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
