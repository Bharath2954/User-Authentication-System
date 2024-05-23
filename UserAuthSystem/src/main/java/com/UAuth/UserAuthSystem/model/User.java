package com.UAuth.UserAuthSystem.model;

import java.util.Collection;
import java.util.List;

import com.UAuth.UserAuthSystem.config.GrantedAuthorityDeserializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("User")
public class User implements UserDetails{

    @Id
	@Field
	@Indexed(unique=true)
	private ObjectId id;
	
	@Setter
    @Field
	@Indexed(unique=true)
	private String userName;
	
	@Field
	@Setter
	private String password;

	@Field
	@Setter
	private String firstname;

	@Field
	@Setter
	private String lastname;

	@Field
	@Setter
	private String email;

	@Field
	private Role role;

	@JsonDeserialize(using = GrantedAuthorityDeserializer.class)
	private List<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
	}
}
