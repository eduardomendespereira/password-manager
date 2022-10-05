package br.com.passwordmanager.ServiceTest;

import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@RequiredArgsConstructor
@SpringBootTest
public class UserServiceTest {

    private final UserService userService;
    private User userFactory(){
        User user = new User("username", "password", "url1");
        return user;
    }

    @Test
    private void insert(){
        User user = userFactory();
        this.userService.save(user);
        var getUser = this.userService.findById(user.getId());
        Assertions.assertEquals(getUser, user);
    }
}
