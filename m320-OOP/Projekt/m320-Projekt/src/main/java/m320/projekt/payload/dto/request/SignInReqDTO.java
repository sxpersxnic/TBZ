package m320.projekt.payload.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "principal")
public class SignInReqDTO {
    @NotBlank(message = "Email/Username must not be empty")
    private String principal;

    @NotBlank(message = "Password must not be empty")
    private String password;
}
