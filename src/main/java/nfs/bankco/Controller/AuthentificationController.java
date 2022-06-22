package nfs.bankco.Controller;

import nfs.bankco.Entity.Banker;
import nfs.bankco.Services.JWTUserService;
import nfs.bankco.Services.UserService;
import nfs.bankco.Utils.form.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AuthentificationController {
    static public class SigninForm {
        private String email;
        private String pass;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
    }
    @Autowired
    private JWTUserService userService;

    @RequestMapping("/")
    public ResponseEntity<List<Banker>> getAll(){
        return ResponseEntity.ok().body(userService.getAll());
    }

    @PostMapping("/signin")
    @ResponseBody
    public String login(@RequestBody SigninForm signinForm) {
        return userService.signin(signinForm.getEmail(), signinForm.getPass());
    }
}
