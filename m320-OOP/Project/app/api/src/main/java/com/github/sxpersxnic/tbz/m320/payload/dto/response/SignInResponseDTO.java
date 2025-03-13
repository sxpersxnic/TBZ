package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;


/// Response DTO for sign in
/// @author sxpersxnic
@Data
@EqualsAndHashCode(callSuper = true)
public class SignInResponseDTO extends ResponseDTO {
    private UUID profileId;
    private String accessToken;
}
