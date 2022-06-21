package nfs.bankco.Controller;

import nfs.bankco.Config.JWTUtils;
import nfs.bankco.Entity.Banker;
import nfs.bankco.Entity.Role;
import nfs.bankco.Repo.BankerRepository;
import nfs.bankco.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AuthentificationController {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/signin")
//    public Banker signin(@RequestParam("email") String email, @RequestParam("password") String password){
    public Object signin() {
        Object banker = null;
        try {
            banker = userService.signin("sd.bancko@legilmalas.fr", "password");
            System.out.println(banker);
        } catch (NoResultException e) {
            System.out.println(e);
            return e;
        }


        List<Role> roles = new ArrayList<>();
        String token = jwtUtils.createToken("sd.bancko@legilmalas.fr",roles);
        return token;
    }

//    @RequestMapping(value = "/auth/logout")
}
