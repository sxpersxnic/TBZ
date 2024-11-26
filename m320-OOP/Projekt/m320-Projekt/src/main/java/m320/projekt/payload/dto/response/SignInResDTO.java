package m320.projekt.payload.dto.response;

import java.util.List;

public record SignInResDTO(Integer id, String username, String email, List<Integer> itemIds, Integer roleId, String token) {}
