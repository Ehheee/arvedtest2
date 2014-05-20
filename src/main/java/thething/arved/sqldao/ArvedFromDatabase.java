package thething.arved.sqldao;



import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import thething.arved.dataobjects.AbstractArve;
import thething.arved.utils.AbstractArvedExtractor;
import thething.arved.utils.AbstractArvedFilter;
import thething.arved.utils.AbstractArvedFilter.ArvedType;
import thething.arved.utils.AbstractArvedMapper;


public class ArvedFromDatabase {

	final static String Insert_Arve = "insert into arved (objekt, arveNumber, kuuPaev, summaIlmaKM, summaKM, tasutud, pdfLocation, muugiMees, klient, tarnija, type) VALUES (:objekt, :arveNumber, :kuuPaev, :summaIlmaKM, :summaKM, :tasutud, :pdfLocation, :muugiMees, :klient, :tarnija, :type) ;";
	final static String Update_Arve = "update arved set objekt = :objekt, arveNumber = :arveNumber, kuuPaev = :kuuPaev, summaIlmaKM = :summaIlmaKM, summaKM = :summaKM, tasutud = :tasutud, pdfLocation = :pdfLocation, muugiMees = :muugiMees, klient = :klient, tarnija = :tarnija, type = :type where id = :id ;";
	final static String Delete_Arve = "delete from arved where id = ? ;";
	final static String Select_Objektid = "select distinct(objekt) from arved ;";
	

	
	protected Log logger = LogFactory.getLog(getClass());
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private AbstractArvedMapper abstractArvedMapper = new AbstractArvedMapper();
	private AbstractArvedExtractor extractor = new AbstractArvedExtractor();
	
	public Map<ArvedType, List<AbstractArve>> getArved(AbstractArvedFilter filter){
		Map<ArvedType, List<AbstractArve>> arved = null;
		String query = filter.getQuery();
		if(filter.getObjekt() != null && !"".equals(filter.getObjekt())){
			
		}
		arved = this.namedParameterJdbcTemplate.query(query, filter.getParamSource(), extractor);
		logger.info("Returned arved: " + arved.size());
		return arved;
	}
	
	public AbstractArve getArve(Long id){
		AbstractArvedFilter filter = new AbstractArvedFilter();
		filter.setId(id);
		logger.info(filter.getQuery());
		logger.info(filter);
		
		List<AbstractArve> arved = this.namedParameterJdbcTemplate.query(filter.getQuery(), filter.getParamSource(), abstractArvedMapper);
		logger.info(arved.size());
		return arved.get(0);
		
		
	}
	
	public void updateArve(AbstractArve arve){
		BeanPropertySqlParameterSource pm = new BeanPropertySqlParameterSource(arve);
		pm.registerSqlType("type", Types.VARCHAR);
		this.namedParameterJdbcTemplate.update(Update_Arve, pm);
	}
	
	public Long insertArve(AbstractArve arve){
		BeanPropertySqlParameterSource pm = new BeanPropertySqlParameterSource(arve);
		pm.registerSqlType("type", Types.VARCHAR);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.namedParameterJdbcTemplate.update(Insert_Arve, pm, keyHolder);
		return (Long) keyHolder.getKey();
		
	}
	
	public int deleteArve(Long id){
		int i = this.jdbcTemplate.update(Delete_Arve, id);
		if(i != 1){
			logger.warn("Delete called, but nothing deleted");
		}
		return i;
	}
	
	public List<String> getObjektid(){
		List<String> objektid = new ArrayList<String>();
		SqlRowSet rs = this.jdbcTemplate.queryForRowSet(Select_Objektid);
		while(rs.next()){
			objektid.add(rs.getString("objekt"));
		}
		return objektid;
	}
	
	private BasicDataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
}
