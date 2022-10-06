package br.com.passwordmanager.Service;

import br.com.passwordmanager.Entity.Password;
import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public Optional<Password> findById(Long id){
        return this.passwordRepository.findById(id);
    }

    public Optional<Password> findByDescription(String description){
        return this.passwordRepository.findByDescription(description);
    }

    public Page<Password> findAll(Pageable pageable){
        return this.passwordRepository.findAll(pageable);
    }

    public Password save(Password password){
        return this.passwordRepository.save(password);
    }

    public void update(Long id, Password password){
        if(id == password.getId()){
            this.passwordRepository.save(password);
        }else{
            throw new RuntimeException();
        }
    }

    public void delete(Password password){
        this.passwordRepository.delete(password);
    }
}

