package m320.projekt.payload.mapper;

import m320.projekt.lib.annotations.Mapper;
import m320.projekt.lib.validation.UserValidator;
import m320.projekt.model.Item;
import m320.projekt.payload.dto.request.SignInReqDTO;
import m320.projekt.payload.dto.response.SignInResDTO;
import m320.projekt.model.User;

import java.util.List;

@Mapper
public class SignInMapper {

    private final UserValidator userValidator;

    public SignInMapper(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    public SignInResDTO toDTO(User src, String accessToken) {

        List<Integer> itemIds = src.getItems().stream().map(Item::getId).toList();
        return new SignInResDTO(src.getId(), src.getUsername(), src.getEmail(), itemIds, src.getRole().getId(), accessToken);
    }

    public User fromDTO(SignInReqDTO dto) {
        User user = new User();

        user.setPassword(dto.getPassword());
        userValidator.setPrincipal(dto.getPrincipal(), user);

        return user;
    }
}
