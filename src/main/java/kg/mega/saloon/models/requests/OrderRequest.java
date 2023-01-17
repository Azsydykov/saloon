package kg.mega.saloon.models.requests;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    Long clientId;
    Long masterId;
    Date appointmentDate;
}
