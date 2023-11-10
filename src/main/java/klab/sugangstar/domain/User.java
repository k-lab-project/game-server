package klab.sugangstar.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    private Character character;

    private String login_id;
    private String login_password;
    private String mail;

}
