package br.com.passwordmanager.ServiceTest;

import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private User userFactory(){
        User user = new User("aaaaaaaa", "ccccccc");
        return user;
    }

    @Test
    public void insert(){
        User user = userFactory();
        this.userService.save(user);
        var getUser = this.userService.findById(user.getId());
        Assertions.assertEquals(getUser.get().getUsername(), user.getUsername());
    }
}
