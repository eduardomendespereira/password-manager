package br.com.passwordmanager.Controller;

import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{idUser")
    public ResponseEntity<User> findById(
            @PathVariable Long idUser
    ){
        return ResponseEntity.ok().body(this.userService.findById(idUser).get());
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAll(
            Pageable pageable
    ){
        return ResponseEntity.ok().body(this.userService.findAll(pageable));
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> update(
            @RequestBody User user,
            @PathVariable Long idUser
    ){
        try{
            this.userService.update(idUser, user);
            return ResponseEntity.ok().body("Usuario atualizado com sucesso!");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody User user
    ){
        try{
            this.userService.save(user);
            return ResponseEntity.ok().body("Usuario cadastrado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/disable/")
    public void disable(@PathVariable Long idUser) {
        userService.disable(idUser);
    }

}
