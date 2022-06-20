package nfs.bankco.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JWTTokenFilterConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	private JWTUtils provider;
	
	public JWTTokenFilterConfiguration(JWTUtils p) {
		this.provider = p;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		JWTTokenFilter filter = new JWTTokenFilter(provider);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
