package klab.sugangstar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    private GameCharacter gameCharacter;

    private String login_id;
    private String login_password;
    private String mail;

}
