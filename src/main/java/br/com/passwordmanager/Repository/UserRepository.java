package br.com.passwordmanager.Repository;

import br.com.passwordmanager.Entity.Password;
import br.com.passwordmanager.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user where user.username = :username")
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User user SET user.inactive = true WHERE user.id = :idUser")
    void disable(@Param("idUser") Long idUser);
}
