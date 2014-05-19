package thething.arved.utils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.event.ListSelectionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class AbstractArvedFilter {
	
	public enum ArvedType{
		MUUGI("m", "Müügi"), OSTU("o", "Ostu");
		
		private String identifier;
		private String description;
		private static List<ArvedType> allTypes = Arrays.asList(ArvedType.values());
		ArvedType(String identifier, String description){
			this.identifier = identifier;
			this.description = description;
			
		}
		
		public String getIdentifier(){
			return identifier;
		}
		
		public String getDescription(){
			return description;
		}
		
		public static List<ArvedType> getAllTypes(){
			return allTypes;
		}
		
		public String toString(){
			return this.identifier;
		}
		
		public static ArvedType fromString(String text){
			for(ArvedType type: allTypes){
				if(type.identifier.equalsIgnoreCase(text)){
					return type;
				}
			}
			return null;
		}
	}
	
	public enum Period{
		DAY("day"), WEEK("week"), MONTH("month"), YEAR("year"), CURRENTMONTH("currentmonth"), LASTMONTH("lastmonth");
		
		private String identifier;
		private static List<Period> allPeriods = Arrays.asList(Period.values());
		
		Period(String identifier){
			this.identifier = identifier;
		}
		public static List<Period> getAllPeriods(){
			return allPeriods;
		}
		public static Period fromString(String text){
			for(Period p: allPeriods){
				
				if(p.identifier.equalsIgnoreCase(text)){
					return p;
				}
			}
			return null;
		}
	}

	protected Log logger = LogFactory.getLog(getClass());	
	private List<ArvedType> types;
	
	private Long id;
	private Date startDate;
	private Date endDate;
	private String objekt;
	private Boolean tasutud;
	private Period period;
	private String orderBy;
	private String orderHow;
	private Integer page;
	private Integer pageSize;
	
	protected MapSqlParameterSource paramSource;
	
	
	
	


	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getObjekt() {
		return objekt;
	}
	public void setObjekt(String objekt) {
		this.objekt = objekt;
	}
	public boolean isTasutud() {
		return tasutud;
	}
	public void setTasutud(boolean tasutud) {
		this.tasutud = tasutud;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	public List<ArvedType> getTypes() {
		return types;
	}
	public void setTypes(List<ArvedType> types) {
		this.types = types;
	}

	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderHow() {
		return orderHow;
	}
	public void setOrderHow(String orderHow) {
		this.orderHow = orderHow;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setType(ArvedType type){
		types = new ArrayList<ArvedType>();
		types.add(type);
		
	}
	
	public MapSqlParameterSource getParamSource() {
		return paramSource;
	}
	public void setParamSource(MapSqlParameterSource paramSource) {
		this.paramSource = paramSource;
	}
	private String createBaseQuery(){
		String query = null;
		if(this.types == null || this.types.size() == 0){
			this.types = ArvedType.getAllTypes();
			
		}
		query = this.selectArved;
		if(this.paramSource == null){
			paramSource = new MapSqlParameterSource();
			
			
		}
		paramSource.addValue("types", types);
		paramSource.registerSqlType("types", Types.VARCHAR);
		return query;
	}
	
	public String getQuery(){
		String query = null;
		query = createBaseQuery();
		
		if(id != null){
			query = query + a + this.byId;
			paramSource.addValue("id", id);
			this.logMap(paramSource.getValues());
			return query;
		}
		
		if(objekt != null){
			 query += a +  byObjekt;
			 paramSource.addValue("objekt", objekt);
		}
		if(startDate != null){
			query += a + byStartDate;
			paramSource.addValue("startDate", startDate);
		}
		if(startDate == null && period != null){
			Calendar calendar = Calendar.getInstance();
			switch(period){
			
				case MONTH:
					calendar.roll(Calendar.MONTH, -1);
					startDate = calendar.getTime();
					break;
					
				case DAY:
					calendar.roll(Calendar.DAY_OF_MONTH, -1);
					startDate = calendar.getTime();
					break;
					
				case WEEK:
					calendar.roll(Calendar.WEEK_OF_YEAR, -1);
					startDate = calendar.getTime();
					break;
					
				case YEAR:
					calendar.roll(Calendar.YEAR, -1);
					startDate = calendar.getTime();
					break;
					
				case CURRENTMONTH:
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.DAY_OF_MONTH, 1);
					startDate = calendar.getTime();
					calendar.roll(Calendar.MONTH, 1);
					endDate = calendar.getTime();
					break;
					
				case LASTMONTH:
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.DAY_OF_MONTH, 1);
					calendar.roll(Calendar.MONTH, -1);
					startDate = calendar.getTime();
					calendar.roll(Calendar.MONTH, 1);
					endDate = calendar.getTime();
				
			}

		}
		if(startDate != null ){
			query += a + byStartDate;
			paramSource.addValue("startDate", startDate);
		}
		if(endDate != null){
			query += a + byEndDate;
			paramSource.addValue("endDate", endDate);
		}
			
		if(tasutud != null){
			query += a + byTasutud;
			paramSource.addValue("tasutud", tasutud);
		}
		
		query = this.orderQuery(query);
		
		/**
		 * PageSize is increased so always +1 is displayed. -1 is deducted to check if next page link is necessary
		 */
		if(page != null && pageSize != null){
			
			query = query + " limit " + (page*pageSize) + "," + (pageSize+1) ;
	
		}
		if(page == null && pageSize != null){

			query = query + " limit 0," + (pageSize +1);

		}
		
		logger.info("query: " + query);
		this.logMap(paramSource.getValues());
	
		query = query + ";";
		return query;
	}
	

	
	
	/*
	protected String getDefultsQuery(String select){
		if(bindParams == null){
			bindParams = new HashMap<String, Object>();
		}
		if(objekt != null){
			 select += byObjekt;
		}
		if(startDate != null){
			select += byStartDate;
			bindParams.put("startDate", startDate);
		}
		if(endDate == null && period != null){
			Calendar calendar = Calendar.getInstance();
			switch(period){
			
				case MONTH:
					calendar.roll(Calendar.MONTH, 1);
				case DAY:
					calendar.roll(Calendar.DAY_OF_MONTH, 1);
				case YEAR:
					calendar.roll(Calendar.YEAR, 1);
				
			}
			endDate = calendar.getTime();
		
		}
		if(endDate != null){
			select += byEndDate;
			bindParams.put("endDate", endDate);
		}
			
		if(tasutud != null){
			select += byTasutud;
			bindParams.put("tasutud", tasutud);
		}
		logger.info("getDefaultsQuery: " + select);
		return select;
	}
	
	*/
	
	protected String orderQuery(String query){
		if(orderBy != null){
			query += " order by " + orderBy;
			if(orderHow != null && ("asc".equalsIgnoreCase(orderHow) || "desc".equalsIgnoreCase(orderHow))){
				query += " " + orderHow;
			}
		}
		return query;
	}
	
	public void logMap(Map<?,?> map){
		StringBuilder builder = new StringBuilder();
		builder.append("{ ");
		for(Entry<?, ?> e :map.entrySet()){
			builder.append("(K: " + e.getKey() + ", V: " + e.getValue() + " )");
		}
	
		logger.info(builder.toString());
	}
	


	private String selectArved = "select * from arved where type in (:types)";
	
	protected String a = " AND ";

	
	
	protected String byId = "id = :id;";
	
	protected String byObjekt = "objekt = :objekt";
	protected String byStartDate = "kuuPaev >= :startDate";
	protected String byEndDate = "kuuPaev <= :endDate";
	protected String byTasutud = "tasutud = :tasutud";
	
}
