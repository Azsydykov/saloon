package kg.mega.saloon.models.entities;

import kg.mega.saloon.enums.WorkTypeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_master")
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ключ будет генерироваться (используют обычно для маленьких приложений)
    Long id;
    String name;
    String surname;
    boolean active;

    @ManyToOne
    @JoinColumn(name="saloon_id")
    Saloon saloon;

//    @ManyToOne
//    @JoinColumn(name="schedule_id")
//    Schedule schedule;

    @Enumerated(EnumType.STRING)
    WorkTypeEnum workType;

    @PrePersist
    protected void onCreate() {
        active = true;
    }
}
