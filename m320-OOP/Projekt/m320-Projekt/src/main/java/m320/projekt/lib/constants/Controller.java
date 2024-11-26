package m320.projekt.lib.constants;

import m320.projekt.lib.enums.Entity;
import m320.projekt.lib.enums.Method;

public class Controller {

    public static final String API = "/api/v1";

    public static final String USER = "/users";
    public static final String ROLE = "/roles";
    public static final String ITEM = "/items";
    public static final String SEED = "/seed";


    public static final String GET = "/{id}";
    public static final String POST = "/post";
    public static final String PATCH = "/patch/{id}";
    public static final String DELETE = "/delete/{id}";

    public static final String USER_PATH = API + USER;
    public static final String ROLE_PATH = API + ROLE;
    public static final String ITEM_PATH = API + ITEM;
    public static final String SEED_PATH = API + SEED;

    public static final String USER_GET_PATH = USER_PATH + GET;
    public static final String ROLE_GET_PATH = ROLE_PATH + GET;
    public static final String ITEM_GET_PATH = ITEM_PATH + GET;

    public static final String ITEM_POST_PATH = ITEM_PATH + POST;
    public static final String ROLE_POST_PATH = ROLE_PATH + POST;

    public static final String USER_PATCH_PATH = USER_PATH + PATCH;
    public static final String ROLE_PATCH_PATH = ROLE_PATH + PATCH;
    public static final String ITEM_PATCH_PATH = ITEM_PATH + PATCH;

    public static final String USER_DELETE_PATH = USER_PATH + DELETE;
    public static final String ROLE_DELETE_PATH = ROLE_PATH + DELETE;
    public static final String ITEM_DELETE_PATH = ITEM_PATH + DELETE;

    public static final String SEED_USERS_PATH = SEED_PATH + USER;
    public static final String SEED_ROLES_PATH = SEED_PATH + ROLE;
}