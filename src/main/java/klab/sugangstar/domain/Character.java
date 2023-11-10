package klab.sugangstar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="character")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Character {
    @Id
    @GeneratedValue
    @Column(name = "character_id")
    private Long id;

    @Embedded
    private Status status;

    @Embedded
    private Debuff debuff;

    private int week;
    private int stamina;
    private int condition;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "character",cascade = CascadeType.ALL)
    List<Subject> subjects;
}
