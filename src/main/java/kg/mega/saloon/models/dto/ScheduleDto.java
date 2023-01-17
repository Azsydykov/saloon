package kg.mega.saloon.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.saloon.enums.WorkDayEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ScheduleDto {
    @JsonIgnore
    Long id;
    //  @JsonFormat(pattern = "HH:mm", timezone="-06:00:00")
    @Temporal(value = TemporalType.TIME)
    LocalTime startTime;
    // @JsonFormat(pattern = "HH:mm", timezone="-06:00:00")
    @Temporal(value = TemporalType.TIME)
    LocalTime endTime;
    WorkDayEnum workDay;

    public ScheduleDto(LocalTime startTime, LocalTime endTime, WorkDayEnum workDay) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.workDay = workDay;
    }

}
