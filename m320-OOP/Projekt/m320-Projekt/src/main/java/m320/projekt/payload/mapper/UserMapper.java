package m320.projekt.payload.mapper;

import m320.projekt.lib.annotations.Mapper;
import m320.projekt.lib.interfaces.CrudMapper;
import m320.projekt.model.Item;
import m320.projekt.model.User;
import m320.projekt.payload.dto.request.UserReqDTO;
import m320.projekt.payload.dto.response.UserResDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper
public class UserMapper implements CrudMapper<User, UserReqDTO, UserResDTO> {

    @Override
    public UserResDTO toDTO(User user) {
        List<Integer> itemIds = user.getItems().stream().map(Item::getId).toList();
        return new UserResDTO(user.getId(), user.getUsername(), user.getEmail(), itemIds, user.getRole().getId());
    }

    @Override
    public User fromDTO(UserReqDTO dto) {
        User user = new User();
        Set<Item> items = new HashSet<>();

        for (Integer itemId : dto.getItemIds()) {
            Item item = new Item();
            item.setId(itemId);
            items.add(item);
        }

        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setItems(items);
        user.setRole(dto.getRole());

        return user;
    }

}
