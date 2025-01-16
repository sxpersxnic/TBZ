package com.m320.api.lib.utils;

import com.m320.api.model.Profile;
import com.m320.api.model.User;
import lombok.Data;

@Data
public class Account {

    private User user;
    private Profile profile;
}
