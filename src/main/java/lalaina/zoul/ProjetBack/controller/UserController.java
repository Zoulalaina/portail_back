package lalaina.zoul.ProjetBack.controller;

import lalaina.zoul.ProjetBack.dto.AuthentificationDto;
import lalaina.zoul.ProjetBack.model.Role;
import lalaina.zoul.ProjetBack.model.TypeRole;
import lalaina.zoul.ProjetBack.model.User;
import lalaina.zoul.ProjetBack.repository.UserRepository;
import lalaina.zoul.ProjetBack.service.JwtService;
import lalaina.zoul.ProjetBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;





    @PostMapping("inscription")
    public void create(@RequestBody User user){
        this.userService.inscription(user);
    }
    @PostMapping("connexion")
    public Map<String, String> connexion(@RequestBody AuthentificationDto authentificationDto){
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDto.username(), authentificationDto.password())
        );
        if(authenticate.isAuthenticated()) {
            return this.jwtService.generate(authentificationDto.username());
        }

        return null;
    }
    @PostMapping("deconnexion")
    public void deconnexion(){
        this.jwtService.deconnexion();
    }
}
