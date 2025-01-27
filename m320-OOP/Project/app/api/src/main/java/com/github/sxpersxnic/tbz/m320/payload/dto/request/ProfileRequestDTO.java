package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import lombok.Data;

/// Request Data transfer object of {@link Profile}.
///
/// **Contains:**
///
/// - `private String username;` - Username of profile, so not the email must be displayed.
///
/// - `private String profilePicture;` - Path to profile picture in Supabase S3 Bucket.
/// @author sxpersxnic
@Data
public class ProfileRequestDTO {
    private String username;
    private String profilePicture;
}
