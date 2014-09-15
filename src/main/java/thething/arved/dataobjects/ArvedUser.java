package thething.arved.dataobjects;

import java.io.Serializable;

/**
 * 'Business' class representing the users of this applications. Uses password encryption from previous version(SHA).
 * Currently Bcrypt should be used for encrypting passwords, but this is not really open to world application and only has a few users
 * and not too much activity so probably not a target for hackers.
 * @author Kaur
 *
 */
public class ArvedUser implements Serializable{

	private long id;
	private String userName;
    private String fullName;
    private String password;
    private String salt;
    private boolean enabled;
    private String privilege;
    private Integer pageSize;
    private String defaultSort;
    /*
    	create table arvedtest2.arvedUsers (
    	id SERIAL,
    	userName VARCHAR(40),
    	fullName VARCHAR(70),
    	password VARCHAR(50),
    	salt VARCHAR(50),
    	enabled BOOLEAN,
    	privilege VARCHAR(50),
    	pageSize INT,
    	defaultSort VARCHAR(50)
    	
    	);
    	
    	
    	insert into arvedtest2.arvedUsers 
    	(userName, fullName, password, salt, enabled, privilege, pageSize, defaultSort) values 
    	('admin', 'Kaur', 'bdf5433cac30e637b04b5e88107b26cd8222f3d2', '614497.5535472096', true, 'ROLE_USER', 30, 'kuuPaev' );
      
     */
    
    
    public ArvedUser(){
    	
    }
    
	public ArvedUser(long id, String userName, String fullName,
			String password, String salt, boolean enabled, String privilege,String defaultSort) {
		super();
		this.id = id;
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.salt = salt;
		this.enabled = enabled;
		this.privilege = privilege;
		this.defaultSort = defaultSort;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	

	public Integer getPageSize() {
		return pageSize;
	}

	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getDefaultSort() {
		return defaultSort;
	}

	public void setDefaultSort(String defaultSort) {
		this.defaultSort = defaultSort;
	}
	
	
    
    
}
