package kg.mega.saloon.models.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.saloon.enums.WorkDayEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDto {
    @JsonIgnore
    Long id;
    @JsonFormat(pattern = "HH:mm")
    Date startTime;
    @JsonFormat(pattern = "HH:mm")
    Date endTime;
    WorkDayEnum workDay;

}
