package m320.projekt.payload.dto.request;

import lombok.*;
import m320.projekt.model.Item;
import m320.projekt.model.Role;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserReqDTO {
    private Integer id;
    private String username;
    private String email;
    private List<Integer> itemIds;
    private Integer roleId;
}
