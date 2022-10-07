package br.com.passwordmanager.Controller;

import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{idUser}")
    public ResponseEntity<?> findById(
            @PathVariable Long idUser
    ){
        try {
            return ResponseEntity.ok().body(this.userService.findById(idUser).get());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findUsername/{username}")
    public ResponseEntity<?> findByUsername(
            @PathVariable String username
    ){
        try {
            return ResponseEntity.ok().body(this.userService.findByUsername(username).get());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> delete(@PathVariable Long idUser) {
        try {
            this.userService.delete(idUser);
            return ResponseEntity.ok().body("Usuario deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok().body(this.userService.login(user));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
