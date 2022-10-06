package br.com.passwordmanager.Repository;

import br.com.passwordmanager.Entity.Password;
import br.com.passwordmanager.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user where user.username = :username")
    Optional<User> findByUsername(String username);
}
