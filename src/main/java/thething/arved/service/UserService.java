package thething.arved.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import thething.arved.dao.ArvedUserDao;
import thething.arved.dataobjects.ArvedUser;

public class UserService implements UserDetailsService {

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		ArvedUser user = arvedUserDao.selectByUserName(userName);
		if(user != null){
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority(user.getPrivilege()));
			SecurityUser securityUser = new SecurityUser(
					user.getUserName(), user.getPassword(), user.isEnabled(), true, true, true, roles, user.getSalt()
			);
			securityUser.setUser(user);
			return securityUser;
		}else{
			throw new UsernameNotFoundException("No user with username '" + userName + "' found!");
		}
	}
	
	private ArvedUserDao arvedUserDao;
	public ArvedUserDao getArvedUserDao() {
		return arvedUserDao;
	}
	public void setArvedUserDao(ArvedUserDao arvedUserDao) {
		this.arvedUserDao = arvedUserDao;
	}

}
