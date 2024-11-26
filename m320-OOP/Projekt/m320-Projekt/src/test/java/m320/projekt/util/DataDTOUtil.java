package m320.projekt.util;

import m320.projekt.payload.dto.request.*;
import m320.projekt.payload.dto.response.*;

import java.util.ArrayList;
import java.util.List;

public class DataDTOUtil {
    public UserReqDTO getUserReqDTO(Integer index) {
        return getUserReqDTOs().get(index);
    }

    public UserResDTO getUserResDTO(Integer index) {
        return getUserResDTOs().get(index);
    }

    public ItemReqDTO getItemReqDTO(Integer index) {
        return getItemReqDTOs().get(index);
    }

    public ItemResDTO getItemResDTO(Integer index) {
        return getItemResDTOs().get(index);
    }

    public RoleReqDTO getRoleReqDTO(Integer index) {
        return getRoleReqDTOs().get(index);
    }

    public RoleResDTO getRoleResDTO(Integer index) {
        return getRoleResDTOs().get(index);
    }

    public SignUpReqDTO getSignUpReqDTO(Integer index) {
        return getSignUpReqDTOs().get(index);
    }

    public SignUpResDTO getSignUpResDTO(Integer index) {
        return getSignUpResDTOs().get(index);
    }

    public SignInReqDTO getSignInReqDTO(Integer index) {
        return getSignInReqDTOs().get(index);
    }

    public SignInResDTO getSignInResDTO(Integer index) {
        return getSignInResDTOs().get(index);
    }

    public List<UserReqDTO> getUserReqDTOs() {
        List<UserReqDTO> userReqDTOs = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            UserReqDTO dto = new UserReqDTO();
            dto.setId(i);
            dto.setUsername("test_user" + i);
            dto.setEmail("test_user" + i + "@localhost.com");
            dto.setRoleId(i);
            dto.setItemIds(List.of(i, i + 4));
            userReqDTOs.add(dto);
        }
        return userReqDTOs;
    }

    public List<UserResDTO> getUserResDTOs() {
        List<UserResDTO> userResDTOs = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            Integer roleId = i;
            if (i == 4) {
                roleId = 1;
            }
            UserResDTO dto = new UserResDTO(i, "test_user" + i, "", List.of(i, i + 4), roleId);
            userResDTOs.add(dto);
        }
        return userResDTOs;
    }

    public List<ItemReqDTO> getItemReqDTOs() {
        List<ItemReqDTO> itemReqDTOs = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            ItemReqDTO dto = new ItemReqDTO();
            dto.setId(i);
            dto.setTitle("Test Item " + i);
            if (i <= 4) {
                dto.setAuthorId(i);
            } else {
                dto.setAuthorId(i - 4);
            }
            itemReqDTOs.add(dto);
        }
        return itemReqDTOs;
    }

    public List<ItemResDTO> getItemResDTOs() {
        List<ItemResDTO> itemResDTOs = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            int authorId;
            if (i <= 4) {
                authorId = i;
            } else {
                authorId = i - 4;
            }
            ItemResDTO dto = new ItemResDTO(i, "Test Item" + i, authorId);
            itemResDTOs.add(dto);
        }
        return itemResDTOs;
    }

    public List<RoleReqDTO> getRoleReqDTOs() {
        List<RoleReqDTO> roleReqDTOs = new ArrayList<>();
        Integer roleId = 1;
        for (String name : List.of("user", "moderator", "admin")) {
            RoleReqDTO dto = new RoleReqDTO();
            dto.setId(roleId);
            dto.setName(name);
            roleReqDTOs.add(dto);
            roleId++;
        }
        return roleReqDTOs;
    }

    public List<RoleResDTO> getRoleResDTOs() {
        List<RoleResDTO> roleResDTOs = new ArrayList<>();
        Integer roleId = 1;
        for (String name : List.of("user", "moderator", "admin")) {
            if (name.equals("user")) {
                RoleResDTO dto = new RoleResDTO(roleId, name, List.of());
            }
            dto.setId(roleId);
            dto.setName(name);
            roleResDTOs.add(dto);
            roleId++;
        }
        return roleResDTOs;
    }

    public List<SignUpReqDTO> getSignUpReqDTOs() {
        List<SignUpReqDTO> signUpReqDTOs = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            SignUpReqDTO dto = new SignUpReqDTO();
            dto.setUsername("test_user" + i);
            dto.setEmail("test_user" + i + "@localhost.com");
            dto.setPassword("P@ssw0rd" + i);
            signUpReqDTOs.add(dto);
        }
        return signUpReqDTOs;
    }

    public List<SignUpResDTO> getSignUpResDTOs() {
        List<SignUpResDTO> signUpResDTOs = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            SignUpResDTO dto = new SignUpResDTO(i, "test_user" + i, "test_user" + i + "@localhost.com");
            signUpResDTOs.add(dto);
        }
        return signUpResDTOs;
    }

    public List<SignInReqDTO> getSignInReqDTOs() {
        List<SignInReqDTO> signInReqDTOs = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            SignInReqDTO dto = new SignInReqDTO();
            dto.setUsername("test_user" + i);
            dto.setPassword("P@ssw0rd" + i);
            signInReqDTOs.add(dto);
        }
        return signInReqDTOs;
    }

    public List<SignInResDTO> getSignInResDTOs() {
        List<SignInResDTO> signInResDTOs = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            int roleId = i;
            if (i == 4) {
                roleId = 1;
            }
            SignInResDTO dto = new SignInResDTO(i, "test_user" + i, "test_user" + i + "@localhost.com", List.of(i, i + 4), roleId, "token");
            signInResDTOs.add(dto);
        }
        return signInResDTOs;
    }
}
