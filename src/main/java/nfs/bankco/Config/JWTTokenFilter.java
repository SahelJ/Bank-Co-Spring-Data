package nfs.bankco.Config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTTokenFilter extends OncePerRequestFilter {
	
	private JWTTokenProvider provider;
	
	public JWTTokenFilter(JWTTokenProvider p) {
		this.provider = p;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter) throws ServletException, IOException{
		String token = provider.resolveToken(req);
		String cookieToken = null;
		Cookie[] cookies = req.getCookies();
		if ( cookies != null && cookies.length > 0) {
			for (Cookie cookie: cookies) {
				System.out.println("Cookie : " + cookie.getName());
				if (cookie.getName().equals("token")) {
					cookieToken = cookie.getValue();
				}
			}
		}
		try {
				if(token != null && provider.validatToken(token)) {
					System.out.println("Token : " + token);
					Authentication auth = provider.getAuthentication(token);
					System.out.println("Auth : " + auth.getCredentials());
					SecurityContextHolder.getContext().setAuthentication(auth);
				} else if (cookieToken != null && provider.validatToken(cookieToken) ) {
					System.out.println("Cookie Token : " + cookieToken);
					Authentication auth = provider.getAuthentication(cookieToken);
					System.out.println("Auth : " + auth.getCredentials());
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
		} catch (Exception e) {
				SecurityContextHolder.clearContext();
				res.sendError(403, "bad token");
			}
		filter.doFilter(req, res);
	}

}
