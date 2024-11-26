package m320.projekt.payload.dto.response;

import java.util.List;

public record RoleResDTO(Integer id, String name, List<Integer> userIds) {}
