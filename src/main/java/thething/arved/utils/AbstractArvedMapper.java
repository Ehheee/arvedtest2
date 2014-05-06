package thething.arved.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import thething.arved.dataobjects.AbstractArve;
import thething.arved.utils.AbstractArvedFilter.ArvedType;

public class AbstractArvedMapper implements RowMapper<AbstractArve> {

	
	protected Log logger = LogFactory.getLog(getClass());
	
	public AbstractArve mapRow(ResultSet rs, int arg1) throws SQLException {
		AbstractArve arve = new AbstractArve();
		try{
			ArvedType type = ArvedType.fromString(rs.getString("type"));
			
			arve.setArveNumber(rs.getString("arveNumber"));
			arve.setId(rs.getLong("id"));
			
			arve.setKuuPaev(rs.getDate("kuuPaev"));
			arve.setObjekt(rs.getString("objekt"));
			arve.setPdfLocation(rs.getString("pdfLocation"));
			arve.setTasutud(rs.getBoolean("tasutud"));
			arve.setSummaIlmaKM(rs.getBigDecimal("summaIlmaKM"));
			arve.setSummaKM(rs.getBigDecimal("summaKM"));
			arve.setType(type);
			switch(type){
			
			case MUUGI:
				arve.setKlient(rs.getString("klient"));
				arve.setMuugiMees(rs.getString("muugiMees"));
				break;
				
			case OSTU:
				arve.setTarnija(rs.getString("tarnija"));
				break;
			}
			
			
			logger.info("mapped AbstractArve{" + arve + "}");
			return arve;
		}
		catch(SQLException e){
			logger.error("Error mapping row: " + e);
			throw e;
		}
		catch(Exception e){
			logger.error("Error mapping row: " + e);
			return null;
		}
		
	}

}
