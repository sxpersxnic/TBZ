package com.github.sxpersxnic.tbz.m320.payload.dto.request;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import jakarta.validation.constraints.NotBlank;
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

    /// Username of {@link Profile}, so not the email must be displayed.
    @NotBlank(message = "Username cannot be blank!")
    private String username;

    /// Optional path to profile picture in Supabase S3 Bucket.
    /// @Default default.jpg
    /// @see Profile
    private String profilePicture;
}
