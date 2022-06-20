package nfs.bankco.Controller;

import nfs.bankco.Config.JWTUtils;
import nfs.bankco.Entity.Role;
import nfs.bankco.Repo.BankerRepository;
import nfs.bankco.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthentificationController {
    private JWTUtils jwtUtils;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/signin")
    public String signin(@RequestParam("username") String username, @RequestParam("pass") String password)
        return userService.signin(username, password);
    }

//    @RequestMapping(value = "/auth/logout")
}
