package br.com.passwordmanager.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="passwords", schema="public")
public class Password extends AbstractEntity {

    @Getter
    @Setter
    @Column(name="description")
    private String description;

    @Getter
    @Setter
    @Column(name="url")
    private String url;

    @Getter
    @Setter
    @Column(name="password", nullable=false)
    private String password;

    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="user_id", nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User user;

    public Password(String description, String url, String password, User user) {
        this.description = description;
        this.url = url;
        this.password = password;
        this.user = user;
    }

    public Password(Long id, LocalDateTime registered, boolean inactive, String description, String url, String password, User user) {
        super(id, registered, inactive);
        this.description = description;
        this.url = url;
        this.password = password;
        this.user = user;
    }

    public Password() {
    }
}