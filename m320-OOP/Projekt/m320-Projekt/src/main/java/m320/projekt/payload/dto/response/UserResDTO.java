package m320.projekt.payload.dto.response;

import java.util.List;

public record UserResDTO(Integer id, String username, String email, List<Integer> itemIds, Integer roleId) {
}
