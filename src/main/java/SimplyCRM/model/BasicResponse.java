package SimplyCRM.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class BasicResponse {
    private boolean sucessful;
    //either the error message or ID
    private String text;
}
