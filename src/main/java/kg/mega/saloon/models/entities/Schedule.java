package kg.mega.saloon.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.saloon.enums.WorkDayEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ключ будет генерироваться
    Long id;
  //  @DateTimeFormat(pattern = "hh:mm")
    @Temporal(value = TemporalType.TIME)
    Date startTime;
  //  @DateTimeFormat(pattern = "hh:mm")
    @Temporal(value = TemporalType.TIME)
    Date endTime;
    @Enumerated(EnumType.STRING)
    WorkDayEnum workDay;


}
