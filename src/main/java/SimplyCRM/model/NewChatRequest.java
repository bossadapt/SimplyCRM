package SimplyCRM.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewChatRequest {
    private String name;
    private List<Long> participants;
}
