package m320.projekt.lib.enums;

import lombok.Getter;

@Getter
public enum Method {
    PATCH("PATCH"),
    DELETE("DELETE"),
    POST("POST"),
    GET("GET"),
    CONTROLLER("CONTROLLER");

    private final String method;

    Method(String method) {
        this.method = method;
    }

}
