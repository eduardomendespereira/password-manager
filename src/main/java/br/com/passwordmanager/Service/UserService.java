package br.com.passwordmanager.Service;

import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findById(Long id){
        return this.userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    public Page<User> findAll(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public User save(User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public User update(Long id, User user){
        Optional<User> oldUser = this.userRepository.findById(user.getId());
        if (oldUser.isPresent() && this.checkPassword(user.getPassword(), oldUser.get().getPassword())) {
            return this.save(user);
        }
        throw new RuntimeException("Usuario nao encontrado");
    }

    public void delete(Long id) {
        var user = this.userRepository.findById(id);
        this.userRepository.delete(user.get());
    }

    public User login(User user){
        var userDatabase = this.userRepository.findByUsername(user.getUsername());
        if(userDatabase.isPresent() && this.checkPassword(user.getPassword(), userDatabase.get().getPassword())){
            return userDatabase.get();
        }
        throw new RuntimeException("Usuario nao encontrado");
    }

    private boolean checkPassword(String password1, String password2){
        return this.passwordEncoder.matches(password1, password2);
    }
}
