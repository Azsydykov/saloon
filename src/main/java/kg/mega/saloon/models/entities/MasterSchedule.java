package kg.mega.saloon.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_master_schedule")
public class MasterSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ключ будет генерироваться
    Long id;
    @ManyToOne
    @JoinColumn(name = "master_id")
    Master master;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    Schedule schedule;
}
