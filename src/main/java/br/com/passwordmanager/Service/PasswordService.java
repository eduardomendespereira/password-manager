package br.com.passwordmanager.Service;

import br.com.passwordmanager.Entity.Password;
import br.com.passwordmanager.Repository.PasswordRepository;
import br.com.passwordmanager.Utils.CipherPw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public Password findById(Long id){
        Password password = this.passwordRepository.findById(id).get();
        CipherPw cipherPw = new CipherPw();
        String decryptedPassword = cipherPw.decrypt(password.getPassword());
        password.setPassword(decryptedPassword);
        return password;
    }

    public Optional<Password> findByDescription(String description){
        return this.passwordRepository.findByDescription(description);
    }

    public Page<Password> findAll(Pageable pageable){
        Page<Password> passwords = this.passwordRepository.findAll(pageable);
        CipherPw cipherPw = new CipherPw();
        for (Password password : passwords) {
            String decryptedPassword = cipherPw.decrypt(password.getPassword());
            password.setPassword(decryptedPassword);
        }
        return passwords;
    }

    public Password save(Password password){
        CipherPw cipherPw = new CipherPw();
        String encryptedPassword = cipherPw.encrypt(password.getPassword());
        password.setPassword(encryptedPassword);
        return this.passwordRepository.save(password);
    }

    public void update(Long id, Password password){
        if(id == password.getId()){
            this.passwordRepository.save(password);
        }else{
            throw new RuntimeException();
        }
    }

    public void delete(Long id){
        Optional<Password> password = this.passwordRepository.findById(id);
        if (password.isPresent()) {
            this.passwordRepository.delete(password.get());
            return;
        }
        throw new RuntimeException("Senha nao encontrada");
    }
}

