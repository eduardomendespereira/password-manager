package br.com.passwordmanager.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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
}