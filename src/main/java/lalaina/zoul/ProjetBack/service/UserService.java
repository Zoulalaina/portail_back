package lalaina.zoul.ProjetBack.service;

import lalaina.zoul.ProjetBack.model.Role;
import lalaina.zoul.ProjetBack.model.TypeRole;
import lalaina.zoul.ProjetBack.model.User;
import lalaina.zoul.ProjetBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
   private UserRepository userRepository;
    @Autowired
   private BCryptPasswordEncoder passwordEncoder;
   public void inscription(User user){
       if(!user.getEmail().contains("@")){
           throw new RuntimeException("email no valmid");
       }
       if(!user.getEmail().contains(".")){
           throw  new RuntimeException("mail no vslide");


       }

       Optional<User> UtilisateurOptional = this.userRepository.findByEmail(user.getEmail());
       if(UtilisateurOptional.isPresent()){
           throw new RuntimeException("Email exist");
       }
       String mdpCrypt = this.passwordEncoder.encode(user.getPass());
       user.setPass(mdpCrypt);
       Role roleUser = new Role();
       roleUser.setLibelle(TypeRole.UTILISATEUR);
       user.setRole(roleUser);
       this.userRepository.save(user);
   }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByEmail(username)
                .orElseThrow(() ->new UsernameNotFoundException("User not found"));
    }
}
