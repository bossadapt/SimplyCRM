package SimplyCRM.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserIdentity implements Serializable{
    private Long id;
    private String name;
}
