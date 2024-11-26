package m320.projekt.lib.constants;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Security {
    public static final String SECRET = "707f8bb419f3453d3bf78c1ebb180cd8ec99ad053e6e2d2b47a471c037da8b1a2f799107158be5cbc191ed986869cafb171195993ceb15a687f7df4502f1403ffc992d2da590dcb7dd00e7bbd93f19997205b3b7886dc07fa2fbe5be580d799418ba4bbc29b517c31679bd77334218ee8c31b334b88588f5cfb28aa2bf698ed8ca5870f2fb51de3b81093950fef7d22f31936b26fddcad6321a7a340a75c8f443295860bee15b0d8789aa46ce9e6361deddff5ec73c87500a57b607a334fc11b4db81f86cfdcb9fc121ba84dd3d5cb03008bdde22568a8b34177b53d5cd6a107ff30f16e667d18d1de48c699683f5a2bdfb121f6331b5df829f4f63d48291dd0";
    public static final String ALGORITHM = "HmacSHA256";
    public static final SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    public static final long EXPIRATION_TIME = 864_000_000L; // 10 days

    private static final String API = "/api";
    private static final String API_VERSION = "/v1";
    public static final String AUTH_URLS = API + API_VERSION + "/auth/**";
    public static final String AUTH_CONTROLLER_URL = API + API_VERSION + "/auth";
    public static final String SIGN_UP_PATH = "/signup";
    public static final String SIGN_IN_PATH = "/signin";
    public static final String SIGN_OUT_PATH = "/signout";

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String ROOT_PATH = "/";
}