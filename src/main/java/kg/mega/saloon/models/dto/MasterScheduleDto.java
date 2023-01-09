package kg.mega.saloon.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MasterScheduleDto {
    Long id;
    MasterDto master;
    ScheduleDto schedule;


    public MasterScheduleDto(MasterDto master, ScheduleDto schedule) {
        this.master = master;
        this.schedule = schedule;
    }
}
