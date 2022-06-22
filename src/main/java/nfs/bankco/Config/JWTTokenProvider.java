package nfs.bankco.Config;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import nfs.bankco.Entity.Role;
import nfs.bankco.Services.JWTUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenProvider {

//	@Value("${security.jwt.token.secret-key}")
	@Value("secret")
	private String secretKey;
	
	private long tokenValidity = 360000000;
	
	@Autowired
	private JWTUserService userService;
	
	@PostConstruct
	public void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String username, List<Role> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream()
				.map(r -> new SimpleGrantedAuthority(r.getAuthority()))
						.collect(Collectors.toList()));
		
		Date now = new Date();
		Date expired = new Date(now.getTime() + tokenValidity);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expired)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userService.loadUserByUsername(getUsermane(token));
		return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
	}
	
	public String getUsermane(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest request) {
		String tk = request.getHeader("Authorization");
		if(tk != null && tk.startsWith("Bearer")){
			return tk.substring(7);
		}
		return null;
	}

	public boolean validatToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
