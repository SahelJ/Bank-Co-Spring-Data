package nfs.bankco.Controller;

import nfs.bankco.Entity.Banker;
import nfs.bankco.Models.SigninForm;
import nfs.bankco.Services.JWTUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


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
        UserDetails banker =  userService.loadUserByUsername(signinForm.getEmail());
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
