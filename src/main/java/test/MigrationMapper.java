package test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

import thething.arved.dataobjects.AbstractArve;
import thething.arved.utils.AbstractArvedFilter.ArvedType;
/**
 * Mapper to migrate data from old version. 
 * @author Kaur
 *
 */
public class MigrationMapper implements RowMapper<AbstractArve> {

	private Log logger = LogFactory.getLog(getClass());
	public AbstractArve mapRow(ResultSet rs, int arg1) throws SQLException {
		AbstractArve arve = null;
		Long id = rs.getLong("id");

		String objekt = rs.getString("objekt");
		String arveNumber = null;
		
		try{
			arveNumber = rs.getString("muugiarve_arvenumber");
		}catch(Exception e){
			
		}
		if(arveNumber != null){
			String klient = rs.getString("muugiarve_klient");
			Date kuuPaev = rs.getDate("muugiarve_kuupaev");
			String muugiMees = rs.getString("muugiarve_muugimees");
			String pdfLocation = rs.getString("muugiarve_pdflocation");
			BigDecimal summaIlmaKM = rs.getBigDecimal("muugiarve_summa_ilma_km");
			BigDecimal summaKM = rs.getBigDecimal("muugiarve_summa_KM");
			Boolean tasutud = rs.getBoolean("muugiarve_tasutud");
			arve = new AbstractArve( id, objekt, arveNumber, kuuPaev, summaIlmaKM, summaKM, tasutud, pdfLocation, muugiMees, klient, null, ArvedType.MUUGI);
			
		}else{
			
			arveNumber = rs.getString("ostuarve_arvenumber");
			if(arveNumber != null){
				Date kuuPaev = rs.getDate("ostuarve_kuupaev");
				String pdfLocation = rs.getString("muugiarve_pdflocation");
				BigDecimal summaIlmaKM = rs.getBigDecimal("ostuarve_summa_ilma_km");
				BigDecimal summaKM = rs.getBigDecimal("ostuarve_summa_KM");
				String tarnija = rs.getString("ostuarve_tarnija");
				Boolean tasutud = rs.getBoolean("ostuarve_tasutud");
				arve = new AbstractArve( id, objekt, arveNumber, kuuPaev, summaIlmaKM, summaKM, tasutud, pdfLocation, null, null, tarnija, ArvedType.OSTU);
				
			}
		}
		if(objekt == null){
			logger.info(id + " -- " + objekt + " -- " + arveNumber + " -- " + arve.getType());
		}
		
		
		
		return arve;
	}

}
