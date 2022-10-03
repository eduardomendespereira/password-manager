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
    @Length(min = 3, max = 255, message = "A descricao deverá ter no máximo {max} caracteres")
    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Getter
    @Setter
    @Length(min = 3, max = 255, message = "A url deverá ter no máximo {max} caracteres")
    @Column(name = "url", nullable = false, length = 20, unique = true)
    private String url;

    @Getter
    @Setter
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    public User(String description, String url, String password) {
        this.description = description;
        this.url = url;
        this.password = password;
    }

    public User(Long id, LocalDateTime registered, boolean inactive, String description, String url, String password) {
        super(id, registered, inactive);
        this.description = description;
        this.url = url;
        this.password = password;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(description, user.description) && Objects.equals(url, user.url) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, url, password);
    }
}