package test;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import thething.arved.dataobjects.AbstractArve;
import thething.arved.sqldao.ArvedFromDatabase;
import thething.arved.utils.AbstractArvedFilter;
import thething.arved.utils.AbstractArvedFilter.ArvedType;
import thething.arved.utils.AbstractArvedFilter.Period;

public class Main {

	
	public static void main(String[] args){
		AbstractArvedFilter filter = new AbstractArvedFilter();
		filter.setObjekt("hooldus");
		filter.setPeriod(Period.CURRENTMONTH);
		filter.setOrderBy("kuuPaev");
		filter.setPage(2);
		filter.setPageSize(30);
		filter.getQuery();
		print(filter.getSumQuery());
		BasicDataSource ds2 = new BasicDataSource();
		ds2.setDriverClassName("com.mysql.jdbc.Driver");
		ds2.setUsername("arvedtest2");
		ds2.setPassword("f4k390fkf9");
		ds2.setUrl("jdbc:mysql://localhost:3306/arvedtest2?useUnicode=true&amp;characterEncoding=utf8");
		ds2.setMaxActive(10);
		ds2.setMaxIdle(5);
		ds2.setInitialSize(5);
		ds2.setValidationQuery("SELECT 1");
		ArvedFromDatabase afd = new ArvedFromDatabase();
		afd.setDataSource(ds2);
		print(afd.getTotalSummaIlmaKM(filter).get(ArvedType.OSTU));
		
	}
	
	/*Convert old data to new database
	 * 
	 * 
	private static String selectMA = "select ma.*, o.objekt_nimi as objekt from muugiarved ma left join objektid_muugiarved oma on ma.id=oma.muugiarve_id left join objektid o on o.id = oma.objekt_id " ;
	private static String selectOA = "select oa.*, o.objekt_nimi as objekt from ostuarved oa left join objektid_ostuarved ooa on oa.id=ooa.ostuarve_id left join objektid o on o.id = ooa.objekt_id " ;
	public static void main(String[] args) {
		
		RowMapper<AbstractArve> rowMapper = new MigrationMapper();
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("arved_user");
		ds.setPassword("migrationtest");
		ds.setUrl("jdbc:mysql://localhost:3306/arved_test?useUnicode=true&amp;characterEncoding=utf8");
		ds.setMaxActive(10);
		ds.setMaxIdle(5);
		ds.setInitialSize(5);
		ds.setValidationQuery("SELECT 1");
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(ds);
		List<AbstractArve> mArved = template.query(selectMA, new MapSqlParameterSource(), rowMapper);

		List<AbstractArve> oArved = template.query(selectOA, new MapSqlParameterSource(), rowMapper);
		ds = null;
		BasicDataSource ds2 = new BasicDataSource();
		ds2.setDriverClassName("com.mysql.jdbc.Driver");
		ds2.setUsername("arvedtest2");
		ds2.setPassword("f4k390fkf9");
		ds2.setUrl("jdbc:mysql://localhost:3306/arvedtest2?useUnicode=true&amp;characterEncoding=utf8");
		ds2.setMaxActive(10);
		ds2.setMaxIdle(5);
		ds2.setInitialSize(5);
		ds2.setValidationQuery("SELECT 1");
		ArvedFromDatabase afd = new ArvedFromDatabase();
		afd.setDataSource(ds2);
		
		for(AbstractArve a: mArved){
			afd.insertArve(a);
		}
		for(AbstractArve a: oArved){
			afd.insertArve(a);
		}
	}
	*/
	
	
	
	public static void print(Object o){
		System.out.println(o);
	}

}
