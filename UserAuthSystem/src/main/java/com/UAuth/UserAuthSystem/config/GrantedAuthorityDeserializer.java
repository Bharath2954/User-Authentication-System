package com.UAuth.UserAuthSystem.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

public class GrantedAuthorityDeserializer extends StdDeserializer<GrantedAuthority> {

    public GrantedAuthorityDeserializer() {
        super(GrantedAuthority.class);
    }

    @Override
    public GrantedAuthority deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return new SimpleGrantedAuthority(node.asText());
    }
}

