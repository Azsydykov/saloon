package kg.mega.saloon.models.requests;

import kg.mega.saloon.enums.WorkTypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveMasterRequest {
    String name;
    String surname;
    Long saloonId;
    WorkTypeEnum workType;
}
