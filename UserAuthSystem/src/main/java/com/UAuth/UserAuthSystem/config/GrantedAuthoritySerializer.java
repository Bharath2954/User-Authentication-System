package com.UAuth.UserAuthSystem.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;

public class GrantedAuthoritySerializer extends StdSerializer<GrantedAuthority> {

    public GrantedAuthoritySerializer() {
        super(GrantedAuthority.class);
    }

    @Override
    public void serialize(GrantedAuthority value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getAuthority());
    }
}

