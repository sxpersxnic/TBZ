package m320.projekt.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RoleReqDTO {

    @NotNull(message = "Id can not be null!")
    private Integer id;

    @NotBlank(message = "Name can not be empty!")
    private String name;

    private List<Integer> userIds;
}
