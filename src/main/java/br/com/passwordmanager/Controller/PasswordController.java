package br.com.passwordmanager.Controller;

import br.com.passwordmanager.Entity.Password;
import br.com.passwordmanager.Service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/{idPassword}")
    public ResponseEntity<?> findById(
            @PathVariable Long idPassword
    ){
        try {
            return ResponseEntity.ok().body(this.passwordService.findById(idPassword));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findDescription/{description}")
    public ResponseEntity<?> findByDescription(
            @PathVariable String description
    ){
        try{
            return ResponseEntity.ok().body(this.passwordService.findByDescription(description).get());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(
            Pageable pageable
    ){
        try {
            return ResponseEntity.ok().body(this.passwordService.findAll(pageable));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @DeleteMapping("/{idPassword}")
    public ResponseEntity<?> delete(@PathVariable Long idPassword) {
        try {
            this.passwordService.delete(idPassword);
            return ResponseEntity.ok().body("Senha deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
