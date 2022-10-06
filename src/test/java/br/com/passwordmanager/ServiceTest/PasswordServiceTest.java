package br.com.passwordmanager.ServiceTest;

import br.com.passwordmanager.Entity.*;
import br.com.passwordmanager.Service.PasswordService;
import br.com.passwordmanager.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class PasswordServiceTest {
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserService userService;

    private User userFactory(){
        User user = new User("myUser", "123");
        if(this.userService.findByUsername(user.getUsername()) == null){
            return this.userService.findByUsername(user.getUsername()).get();
        }else {
            return this.userService.save(user);
        }
    }

    private Password passwordFactory(){
        User user = userFactory();
        Password password = new Password("new password", "url1", "password1", user);
        if(passwordService.findByDescription(password.getDescription()) == null){
            return this.passwordService.findByDescription(password.getDescription()).get();
        }else{
            return this.passwordService.save(password);
        }
    }

    @Test
    public void insert(){
        Password password = this.passwordFactory();
        var getPassword = this.passwordService.findByDescription(password.getDescription());
        Assertions.assertEquals(getPassword.get().getDescription(), password.getDescription());
    }
}
