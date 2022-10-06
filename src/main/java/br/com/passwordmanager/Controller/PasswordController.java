package br.com.passwordmanager.Controller;

import br.com.passwordmanager.Entity.Password;
import br.com.passwordmanager.Entity.User;
import br.com.passwordmanager.Service.PasswordService;
import br.com.passwordmanager.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/passwords")
public class PasswordController {
    private final PasswordService passwordService;

    @GetMapping("/{idPassword")
    public ResponseEntity<Password> findById(
            @PathVariable Long idPassword
    ){
        return ResponseEntity.ok().body(this.passwordService.findById(idPassword).get());
    }

    @GetMapping("/{description")
    public ResponseEntity<Password> findByDescription(
            @PathVariable String description
    ){
        return ResponseEntity.ok().body(this.passwordService.findByDescription(description).get());
    }

    @GetMapping
    public ResponseEntity<Page<Password>> findAll(
            Pageable pageable
    ){
        return ResponseEntity.ok().body(this.passwordService.findAll(pageable));
    }

    @PutMapping("/{idPassword}")
    public ResponseEntity<?> update(
            @RequestBody Password password,
            @PathVariable Long idPassword
    ){
        try{
            this.passwordService.update(idPassword, password);
            return ResponseEntity.ok().body("Senha atualizada com sucesso!");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody Password password
    ){
        try{
            this.passwordService.save(password);
            return ResponseEntity.ok().body("Senha cadastrada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/disable/")
    public void disable(@PathVariable Long idPassword) {
        passwordService.disable(idPassword);
    }
}
