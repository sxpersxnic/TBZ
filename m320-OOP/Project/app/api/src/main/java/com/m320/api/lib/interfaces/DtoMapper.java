package com.m320.api.lib.interfaces;

public interface DtoMapper<T, RES, REQ> {
    RES toDTO(T src);
    T fromDTO(REQ dto);
}
