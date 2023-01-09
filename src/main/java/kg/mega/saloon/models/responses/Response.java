package kg.mega.saloon.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response {
    String message;

    public Response(String message) {
        this.message = message;
    }
}
