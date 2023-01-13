package kg.mega.saloon.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.mega.saloon.models.dto.ClientDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveOrderRequest {

    ClientDto clientDto;
    Long masterId;
   // @JsonFormat(pattern = "HH:mm")  если оставить паттерн то можно задать только время, но тогда год будет записан 1960
    Date appointment_date;
}
