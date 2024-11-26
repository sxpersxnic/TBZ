package m320.projekt.payload.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import m320.projekt.lib.annotations.PasswordMatching;

import static m320.projekt.lib.constants.Regex.EMAIL_REGEX;
import static m320.projekt.lib.constants.Regex.USERNAME_REGEX;
import jakarta.validation.constraints.Pattern;
import m320.projekt.lib.annotations.StrongPassword;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "username")
@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirmation don't match!"
)
public class SignUpReqDTO {

    @NotBlank(message = "Username is required!")
    @Pattern(regexp = USERNAME_REGEX, message = "Invalid username!")
    @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters!")
    private String username;

    @Email
    @Pattern(regexp = EMAIL_REGEX, message = "Invalid email!")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @StrongPassword
    private String password;
}
