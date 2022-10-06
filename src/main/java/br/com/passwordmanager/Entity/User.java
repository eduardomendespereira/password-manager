package br.com.passwordmanager.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public")
public class User extends AbstractEntity{
    @Getter
    @Setter
    @Length(min = 3, max = 50, message = "A descricao deverá ter no máximo {max} caracteres")
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Getter
    @Setter
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, LocalDateTime registered, boolean inactive, String username, String password) {
        super(id, registered, inactive);
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}