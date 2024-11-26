package m320.projekt.payload.mapper;

import m320.projekt.lib.annotations.Mapper;
import m320.projekt.lib.interfaces.CrudMapper;
import m320.projekt.model.Role;
import m320.projekt.model.User;
import m320.projekt.payload.dto.request.RoleReqDTO;
import m320.projekt.payload.dto.response.RoleResDTO;

import java.util.List;

@Mapper
public class RoleMapper implements CrudMapper<Role, RoleReqDTO, RoleResDTO> {

    @Override
    public RoleResDTO toDTO(Role role) {

        List<Integer> userIds = role.getUsers().stream().map(User::getId).toList();

        return new RoleResDTO(role.getId(), role.getName(), userIds);

    }

    @Override
    public Role fromDTO(RoleReqDTO dto) {
        Role role = new Role();

        role.setId(dto.getId());
        role.setName(dto.getName());

        return role;
    }
}
