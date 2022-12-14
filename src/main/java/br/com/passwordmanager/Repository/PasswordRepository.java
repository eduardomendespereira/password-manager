package br.com.passwordmanager.Repository;

import br.com.passwordmanager.Entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    @Query("SELECT pa FROM Password pa where pa.description = :description")
    Optional<Password> findByDescription(String description);

    @Modifying
    @Query("UPDATE Password password SET password.inactive = true WHERE password.id = :idPassword")
    void disable(@Param("idPassword") Long idPassword);
}
