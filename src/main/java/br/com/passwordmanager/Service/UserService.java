package br.com.passwordmanager.Service;

import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findById(Long id){
        return this.userRepository.findById(id);
    }

    public Page<User> findAll(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public void save(User user){
        this.userRepository.save(user);
    }

    public void update(Long id, User user){
        if(id == user.getId()){
            this.userRepository.save(user);
        }else{
            throw new RuntimeException();
        }
    }

    public void delete(User user){
        this.userRepository.delete(user);
    }
}
