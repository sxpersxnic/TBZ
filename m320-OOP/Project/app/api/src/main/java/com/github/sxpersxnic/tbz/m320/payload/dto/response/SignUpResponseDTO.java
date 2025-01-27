package com.github.sxpersxnic.tbz.m320.payload.dto.response;

import com.github.sxpersxnic.tbz.m320.lib.abstracts.ResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * @author sxpersxnic
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SignUpResponseDTO extends ResponseDTO {
    private UUID profileId;
    private String email;
}
