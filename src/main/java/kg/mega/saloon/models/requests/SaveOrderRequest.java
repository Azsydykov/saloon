package kg.mega.saloon.models.requests;

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
}
