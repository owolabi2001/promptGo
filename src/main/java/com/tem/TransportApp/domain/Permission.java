package com.tem.TransportApp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    TRANSPORTPROVIDER_READ("management:read"),
    TRANSPORTPROVIDER_UPDATE("management:update"),
    TRANSPORTPROVIDER_CREATE("management:create"),
    TRANSPORTPROVIDER_DELETE("management:delete")



    ;

    @Getter
    private final String permission;
}
