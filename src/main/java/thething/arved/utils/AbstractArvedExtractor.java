package thething.arved.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import thething.arved.dataobjects.AbstractArve;
import thething.arved.utils.AbstractArvedFilter.ArvedType;


public class AbstractArvedExtractor implements ResultSetExtractor<Map<ArvedType, List<AbstractArve>>>{
	
	
	AbstractArvedMapper mapper;
	public AbstractArvedExtractor(){
		mapper = new AbstractArvedMapper();
	}

	public Map<ArvedType, List<AbstractArve>> extractData(ResultSet rs)
			throws SQLException, DataAccessException {
		
		Map<ArvedType, List<AbstractArve>> arved = new HashMap<ArvedType, List<AbstractArve>>();
		List<AbstractArve> muugiArved = null;
		List<AbstractArve> ostuArved = null;
			while(rs.next()){
				AbstractArve arve = mapper.mapRow(rs, rs.getRow());
				if(arve.getType().equals(ArvedType.MUUGI)){
					if(muugiArved == null){
						muugiArved = new ArrayList<AbstractArve>();
					}
					muugiArved.add(arve);
				}
				if(arve.getType().equals(ArvedType.OSTU)){
					if(ostuArved == null){
						ostuArved = new ArrayList<AbstractArve>();
					}
					ostuArved.add(arve);
				}
			}
			
		if(muugiArved != null){
			arved.put(ArvedType.MUUGI, muugiArved);
		}
		if(ostuArved != null){
			arved.put(ArvedType.OSTU, ostuArved);
		}
		return arved;
	}

}
