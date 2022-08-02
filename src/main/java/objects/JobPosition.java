package objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobPosition {
    private String fName;
    private String lName;
    private String email;
    private String phone;
}
