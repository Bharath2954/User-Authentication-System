package com.UAuth.UserAuthSystem.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityModule extends SimpleModule {

    public GrantedAuthorityModule() {
        addSerializer(GrantedAuthority.class, new GrantedAuthoritySerializer());
        addDeserializer(GrantedAuthority.class, new GrantedAuthorityDeserializer());
    }
}

