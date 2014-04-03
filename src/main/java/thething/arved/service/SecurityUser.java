package thething.arved.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import thething.arved.dataobjects.ArvedUser;


public class SecurityUser extends org.springframework.security.core.userdetails.User {
	
	private ArvedUser user;
	private String salt;
	
	public ArvedUser getUser(){
		return user;
	}
	
	public void setUser(ArvedUser user){
		this.user = user;
	}

	public SecurityUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String salt) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.setSalt(salt);
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return salt;
	}

}
