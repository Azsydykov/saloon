package kg.mega.saloon.models.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDto {
    Long id;
    String name;
    String surname;
    String phoneNumber;
    String email;
    @JsonIgnore
    boolean active;
}
