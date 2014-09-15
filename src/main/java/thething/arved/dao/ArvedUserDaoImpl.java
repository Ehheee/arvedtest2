package thething.arved.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import thething.arved.dataobjects.ArvedUser;
import thething.arved.utils.ArvedUserMapper;

@Component(value = "arvedUserDao")
public class ArvedUserDaoImpl implements ArvedUserDao {
	
	private Log logger = LogFactory.getLog(getClass());

    private final String Select_ArvedUser_By_id = 		"select * from arvedUsers where id = ? ;";
    private final String Select_ArvedUser_By_userName = "select * from arvedUsers where userName = ? ;";
    private final String Select_ArvedUsers = 			"select * from arvedUsers ;";
    private final String Insert_ArvedUser = 			"insert into arvedUsers (userName, fullName, password, salt, enabled, privilege, pageSize, defaultSort) values (:userName, :fullName, :password, :salt, :enabled, :privilege, :pageSize, :defaultSort) ;";
    private final String Update_ArvedUser = 			"update arvedUsers set userName = :userName, fullName = :fullName, password = :password, salt = :salt, enabled = :enabled, privilege = :privilege, pageSize = :pageSize, defaultSort = :defaultSort where id = :id ;";
    private final String Delete_ArvedUser = 			"delete from arvedUsers where id = ? ;";
	
    private ArvedUserMapper arvedUserMapper = new ArvedUserMapper();
	
    
    public List<ArvedUser> getAll(){
    	if(this.jdbcTemplate == null){
    		logger.error("jdbcTemplate is null");
    	}
    	return this.jdbcTemplate.query(Select_ArvedUsers, arvedUserMapper);
    }
    
    
    public ArvedUser selectById(Long id){
    	return this.jdbcTemplate.query(Select_ArvedUser_By_id, arvedUserMapper, id).get(0);
    }
    public ArvedUser selectByUserName(String userName){
    	logger.info(userName);
    	logger.info(Select_ArvedUser_By_userName);
    	logger.info(this.jdbcTemplate.query(Select_ArvedUser_By_userName, arvedUserMapper, userName).get(0));
    	return this.jdbcTemplate.query(Select_ArvedUser_By_userName, arvedUserMapper, userName).get(0);
    }
    
    public Long insertArvedUser(ArvedUser user){
    	KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource pm = new BeanPropertySqlParameterSource(user);
		this.namedParameterJdbcTemplate.update(Insert_ArvedUser, pm, keyHolder);
		return (Long)keyHolder.getKey();
    }
    
    public Long updateArvedUser(ArvedUser user){
    	SqlParameterSource pm = new BeanPropertySqlParameterSource(user);
    	this.namedParameterJdbcTemplate.update(Update_ArvedUser, pm);
    	return user.getId();
    }
    
    public void deleteUser(Long id){
    	this.jdbcTemplate.update(Delete_ArvedUser, id);
    }
	
	
	private BasicDataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	

}
