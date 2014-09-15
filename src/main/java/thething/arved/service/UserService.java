package thething.arved.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import thething.arved.dao.ArvedUserDao;
import thething.arved.dataobjects.ArvedUser;
/**
 * As currently there is no need for an interface for creating or modifying users, this class only contains one method needed by spring security.
 * @author Kaur
 *
 */
public class UserService implements UserDetailsService {

	private Log logger = LogFactory.getLog(getClass());
	
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		logger.info(userName);
		ArvedUser user = arvedUserDao.selectByUserName(userName);
		logger.info(user);
		if(user != null){
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority(user.getPrivilege()));
			SecurityUser securityUser = new SecurityUser(
					user.getUserName(), user.getPassword(), user.isEnabled(), true, true, true, roles, user.getSalt()
			);
			securityUser.setUser(user);
			logger.info(securityUser.getUsername());
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
