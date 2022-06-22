package nfs.bankco.Controller;

import nfs.bankco.Entity.Banker;
import nfs.bankco.Models.SigninForm;
import nfs.bankco.Services.JWTUserService;
import nfs.bankco.Services.UserService;
import nfs.bankco.Utils.form.PasswordUtility;
import org.aspectj.weaver.patterns.IToken;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AuthentificationController {

    @Autowired
    private JWTUserService userService;
//
//    @RequestMapping("/")
//    public ResponseEntity<List<Banker>> getAll(){
//        return ResponseEntity.ok().body(userService.getAll());
//    }

    @PostMapping("/signin")
    @ResponseBody
    public String login(@RequestBody SigninForm signinForm, HttpServletResponse response) {
        JSONObject responseObj = new JSONObject();
        responseObj.put("success", false);
        String token = userService.signin(signinForm.getEmail(), signinForm.getPass());
        System.out.println(token);
        if (token != "") {
            responseObj.put("success", true);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
        }
        return responseObj.toString();
    }
}
