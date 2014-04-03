package thething.arved.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import thething.arved.dao.ArvedUserDao;
import thething.arved.dataobjects.ArvedUser;

public class BootOrganizer {

	public void secureDatabase(){
		List<ArvedUser> users = arvedUserDao.getAll();
		for(int i = 0; i < users.size(); i++){
			ArvedUser user = users.get(i);
			double d  = Math.random()*1000000;
			String salt = String.valueOf(d);
			user.setSalt(salt);
			
			String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), salt);
			
			user.setPassword(encodedPassword);
			arvedUserDao.updateArvedUser(user);
			
		}
	}
	
	@Autowired
	private ArvedUserDao arvedUserDao;
	public ArvedUserDao getArvedUserDao() {
		return arvedUserDao;
	}
	public void setArvedUserDao(ArvedUserDao arvedUserDao) {
		this.arvedUserDao = arvedUserDao;
	}
	
	private PasswordEncoder passwordEncoder;
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	
	
	
	private SaltSource saltSource;
	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}
	public SaltSource getSaltSource() {
		return saltSource;
	}
	
	private UserDetailsService userService;
	public void setUserService(UserDetailsService userService) {
		this.userService = userService;
	}
	public UserDetailsService getUserService() {
		return userService;
	}
	
}
