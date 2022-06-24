package nfs.bankco.Controller;

import nfs.bankco.Entity.Banker;
import nfs.bankco.Models.SigninForm;
import nfs.bankco.Services.JWTUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class AuthentificationController {

    @Autowired
    private JWTUserService userService;
//
//    @RequestMapping("/")
//    public ResponseEntity<List<Banker>> getAll(){
//        return ResponseEntity.ok().body(userService.getAll());
//    }


    @PostMapping(value = "/signin", produces = "application/json")
    @ResponseBody
    public String login(@RequestBody SigninForm signinForm, HttpServletRequest request, HttpServletResponse response) {
        JSONObject responseObj = new JSONObject();
        responseObj.put("success", false);
        String token = userService.signin(signinForm.getEmail(), signinForm.getPass());
        if (token != "") {
            responseObj.put("success", true);
            responseObj.put("token", token);
            Cookie cookie = new Cookie("token", token);
            response.setHeader("Authorization","Bearer " + token);
            response.addCookie(cookie);
        } else {
            responseObj.put("error", "NoAcc");
        }
        return responseObj.toString();
    }

}
