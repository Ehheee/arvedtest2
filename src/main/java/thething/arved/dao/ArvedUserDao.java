package thething.arved.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import thething.arved.dataobjects.ArvedUser;


public interface ArvedUserDao {

	
	 public ArvedUser selectById(Long id);
	 public ArvedUser selectByUserName(String userName);
	 public List<ArvedUser> getAll();
	 public Long insertArvedUser(ArvedUser user);
	    
	 public Long updateArvedUser(ArvedUser user);
	 public void deleteUser(Long id);
	    
}
