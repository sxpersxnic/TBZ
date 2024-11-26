package m320.projekt.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import m320.projekt.model.User;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemReqDTO {
    @NotBlank(message = "Id must not be empty")
    private Integer id;
    private String title;
    private Integer authorId;
}
