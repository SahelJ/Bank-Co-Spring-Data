package nfs.bankco.Config;


import nfs.bankco.Services.security.AppAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigJWT extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTUtils provider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/users/signin").permitAll()    // public, obligatoire à définir en premier
                .antMatchers("/users/signup").permitAll()    // public, obligatoire à définir en premier
                .antMatchers("/auth/**").authenticated()    // si dans l'url j'ai /auth/ je dois être authentifié pour y accéder
                .anyRequest().permitAll();                                // si dans l'url je n'ai aps auth, c'est public

        http.apply(new JWTTokenFilterConfiguration(provider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //return super.authenticationManagerBean();
        return new AppAuthProvider();
    }

}