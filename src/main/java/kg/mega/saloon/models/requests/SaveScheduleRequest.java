package kg.mega.saloon.models.requests;

import kg.mega.saloon.enums.WorkDayEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveScheduleRequest {

    LocalTime startTime;
    LocalTime endTime;
    WorkDayEnum workDay;
}
