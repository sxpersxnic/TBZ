package com.github.sxpersxnic.tbz.m320.lib.constants;

/**
 * @author sxpersxnic
 */
public class Controller {

    public final static String USERS = "/users";
    public final static String AUTH = "/auth";
    public final static String ROLES = "/roles";
    public final static String PROFILES = "/profiles";
    public final static String QUESTIONS = "/questions";
    public final static String ANSWERS = "/answers";
    public final static String OPTIONS = "/options";

    public final static String DB_INIT = "/db-init";
    public final static String SIGNIN = "/signin";
    public final static String SIGNUP = "/signup";

    /// Path for **GET** requests with params other than id or findAll.
    public final static String GET = "/get";
    public final static String ID_GET = "/{id}";
    public final static String OPTION_ID_GET = "/get/options";
    public final static String QUESTION_ID_GET = "/get/questions";
    public final static String NAME_GET = "/name/{name}";

    public final static String DELETE = "/{id}";
    public final static String PATCH = "/{id}";
    public final static String POST = "/create";
}
