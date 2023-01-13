package kg.mega.saloon.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.saloon.enums.WorkDayEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveScheduleRequest {

    Date startTime;
    Date endTime;
    WorkDayEnum workDay;
}
