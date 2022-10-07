package br.com.passwordmanager.Service;

import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
        return this.userRepository.save(user);
    }

    public void update(Long id, User user){
        if(id == user.getId()){
            this.userRepository.save(user);
        }else{
            throw new RuntimeException();
        }
    }

    public void delete(Long id) {
        var user = this.userRepository.findById(id);
//        if (user.isPresent()) {
            this.userRepository.delete(user.get());
//        }else {
//            throw new RuntimeException("Usuario nao encontrado");
//        }
    }
}
