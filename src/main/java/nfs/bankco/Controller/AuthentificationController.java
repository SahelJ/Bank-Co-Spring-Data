package nfs.bankco.Controller;

import nfs.bankco.Entity.Banker;
import nfs.bankco.Models.SigninForm;
import nfs.bankco.Services.JWTUserService;
import nfs.bankco.Utils.form.PasswordUtility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@RestController
@RequestMapping("/")
public class AuthentificationController {

    @Autowired
    private JWTUserService jwtUserService;

    PasswordUtility passwordUtility;


    @PostMapping("/signin")
    @ResponseBody
    public String login(@RequestBody SigninForm signinForm, HttpServletResponse response) {
        JSONObject responseObj = new JSONObject();
        String token="";
        responseObj.put("success", false);
        UserDetails banker =  jwtUserService.loadUserByUsername(signinForm.getEmail());
        try {
            passwordUtility.validatePassword(signinForm.getPass(), banker.getPassword());
            token = jwtUserService.signin(signinForm.getEmail(), signinForm.getPass());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        System.out.println(token);
        if (token != "") {
            responseObj.put("success", true);
            responseObj.put("role",banker.getAuthorities());
            Cookie cookie = new Cookie("token", token);
            response.setHeader("Authorization","Bearer" + token);
            response.addHeader("Authorization","Bearer" + token);
            response.addCookie(cookie);
        }
        return responseObj.toString();
    }

}
