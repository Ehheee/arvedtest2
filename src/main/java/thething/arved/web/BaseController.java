package thething.arved.web;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import thething.arved.dao.ArvedUserDao;
import thething.arved.dataobjects.AbstractArve;
import thething.arved.dataobjects.ArvedUser;
import thething.arved.service.SecurityUser;
import thething.arved.sqldao.ArvedFromDatabase;
import thething.arved.utils.AbstractArvedFilter;
import thething.arved.utils.AbstractArvedFilter.ArvedType;
import thething.arved.utils.AbstractArvedFilter.Period;
 
public class BaseController {

	protected Log logger = LogFactory.getLog(getClass());
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(BigDecimal.class,  new PropertyEditorSupport(){
			public void setAsText(String text){
				BigDecimal summa = null;
				StringTokenizer st = new StringTokenizer(text, ",.");
				if(st.countTokens() != 0){
					BigDecimal base = new BigDecimal(st.nextToken());
					if(st.hasMoreTokens()){
						BigDecimal comma = new BigDecimal("0." + st.nextToken());
						summa = base.add(comma);
					}
					setValue(summa);
				}else{
					setValue(new BigDecimal(0));
				}
			}
		});
		binder.registerCustomEditor(Boolean.class, new PropertyEditorSupport(){
			
			public void setAsText(String text){
				if("on".equals("text")){
					setValue(true);
				}else{
					setValue(Boolean.valueOf(text));
				}
				
			}
		});
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, "kuuPaev", editor);
		}
	
	
	protected AbstractArvedFilter processRequest(HttpServletRequest request, HttpSession session){
		session.setAttribute("periods", Period.getAllPeriods());
		AbstractArvedFilter filter = null;
		Object f = session.getAttribute("arvedFilter");
		if(f != null && f instanceof AbstractArvedFilter){
			filter = (AbstractArvedFilter) f;
		}else{
			filter = new AbstractArvedFilter();
		}
		filter = filterFromRequest(request, filter);
		this.setDefaults(filter);
		session.setAttribute("arvedFilter", filter);
		return filter;
		
	}
	
	protected AbstractArvedFilter filterFromRequest(HttpServletRequest request, AbstractArvedFilter filter){
		
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if(startDate != null && !"".equals(startDate)){
				filter.setStartDate(dateFormat.parse(startDate));
			}else{
				filter.setStartDate(null);
			}
			if(endDate != null && !"".equals(endDate)){
				filter.setEndDate(dateFormat.parse(endDate));
			}else{
				filter.setEndDate(null);
			}
		} catch (ParseException e) {
			logger.warn(e.getStackTrace());
			
		}
		if(request.getParameter("id") != null){
		
			filter.setId(Long.valueOf(request.getParameter("id")));
			return filter;
		}
		
		this.setOrder(filter, request);
		filter.setPeriod(Period.fromString(request.getParameter("period")));
		logger.info("Period: " + filter.getPeriod());
		
		if(request.getParameter("page") != null){
			filter.setPage(Integer.valueOf(request.getParameter("page")));
		}else{
			filter.setPage(0);
		}
		if(request.getParameter("tasutud") != null){
			filter.setTasutud(Boolean.valueOf(request.getParameter("tasutud")));
		}
		
		/*
		 * Currently both type and objekt are set to null as filter is taken from session and may contain types and objekts set previously
		 * which are not set to null by more generic controllers. Type and objekt specific controllers will set those separately
		 */
		filter.setTypes(null);										
		filter.setObjekt(null);										
																	
		
		return filter;
	}
	
	public void setOrder(AbstractArvedFilter filter, HttpServletRequest request){
		String orderBy = request.getParameter("orderBy");
		logger.info("setOrder: " + orderBy + "  " + filter.getOrderHow());
		if(orderBy != null){
			filter.setOrderBy(orderBy);
			if("ASC".equals(filter.getOrderHow())){
				filter.setOrderHow("DESC");
			}else if( "DESC".equals(filter.getOrderHow())){
				filter.setOrderHow("ASC");
			}else{
				filter.setOrderHow("DESC");
			}
			
		}
		
	}
	
	public void setDefaults(AbstractArvedFilter filter){
		SecurityUser securityUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArvedUser user = securityUser.getUser();
		
		
		if(filter.getPage() == null){
			filter.setPage(0);
		}if(filter.getPageSize() == null){
			Integer pageSize = (user != null && user.getPageSize() != null) ? user.getPageSize() : 30;
			filter.setPageSize(30);
		}
		if(filter.getOrderBy() == null){
			String orderBy = "kuuPaev";
			String orderHow = "DESC";
			//TODO Get default order from user
			filter.setOrderBy(orderBy);
			filter.setOrderHow(orderHow);
		}
		
	}
	
	
	/**
	 * Requests DAO for arved by filter and attaches them to given model.
	 * If filter has set some types but type is not returned from database
	 * because filter didn't match anything then we still have to add possibility
	 * to insert new entry from jsp. In order for jsp to not throw error on checking 
	 * list size then an empty list is added.
	 * 
	 * 
	 * @param filter - created probably by controller or this.processRequest()
	 * @param model
	 */
	protected void filterToModel(AbstractArvedFilter filter, Model model, ArvedType t){
		Map<ArvedType, List<AbstractArve>> arved = arvedFromDatabase.getArved(filter);
		model.addAttribute("arved", arved);
		List<ArvedType> types = filter.getTypes();
		model.addAttribute("includedTypes", types);
		for(ArvedType type: types){
			if(arved.get(type) == null){
				arved.put(type, Collections.<AbstractArve>emptyList());
			}
		}
		model.addAttribute("filter", filter);
	}
	
	protected void filterToModel(AbstractArvedFilter filter, Model model){
		filterToModel(filter, model, null);
	}
	
	protected AbstractArve insertArve(MultipartHttpServletRequest request){
		this.printRequestparams(request.getParameterMap());
		AbstractArve arve = null;
		Long id = null;
		try {
			String i = request.getParameter("id");
			if(i != null && !"".equals(i)){
				logger.info("i: " + i);
				id = Long.valueOf(i);
			}
			logger.info("id: " + id);
			String objekt = request.getParameter("objekt");
			String arveNumber = request.getParameter("arveNumber");
			Date kuuPaev = dateFormat.parse(request.getParameter("kuuPaev"));
			BigDecimal summaIlmaKM = new BigDecimal(request.getParameter("summaIlmaKM"));
			BigDecimal summaKM = new BigDecimal(request.getParameter("summaKM"));
			boolean tasutud = this.checkBoxToBoolean(request.getParameter("tasutud"));
			logger.info("tasutud:" + tasutud);
			String muugiMees = request.getParameter("muugiMees");
			String klient = request.getParameter("klient");
			String tarnija = request.getParameter("tarnija");
			ArvedType arvedType = ArvedType.fromString(request.getParameter("arvedType"));
			String pdfLocation = this.saveFile(request, arvedType);
			if(id != null){
				arve = arvedFromDatabase.getArve(id);
				if(arve == null){
					logger.warn("Trying to update id that doesn't exist");
					arve = new AbstractArve();
				}
			
				
				
			}else{
				arve = new AbstractArve();
			}
			arve.setArveNumber(arveNumber);
			arve.setKuuPaev(kuuPaev);
			arve.setObjekt(objekt);
			if(pdfLocation != null){
				arve.setPdfLocation(pdfLocation);
			}
			arve.setSummaIlmaKM(summaIlmaKM);
			arve.setSummaKM(summaKM);
			arve.setTasutud(tasutud);
			arve.setType(arvedType);
			switch(arvedType){
			case MUUGI:
				arve.setMuugiMees(muugiMees);
				arve.setKlient(klient);
				break;
			case OSTU:
				arve.setTarnija(tarnija);
				break;
			}
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		
		
		logger.info("made it here");
		if(id != null){
			logger.info("triggeredUpdate");
			arvedFromDatabase.updateArve(arve);
		}else{
			id = arvedFromDatabase.insertArve(arve);
			arve.setId(id);
		}
			
			
		
		
		
		return arve;
	}
	/*
	
	protected void setDefaultDates(HttpSession session){
		Calendar calendar =  Calendar.getInstance();
		if(session.getAttribute("endDate") == null){
			logger.info("set default endDate: " + calendar.getTime());
			session.setAttribute("endDate", calendar.getTime());
		}
		if(session.getAttribute("startDate") == null){
			logger.debug("startDate before roll: " + calendar);
			calendar.roll(Calendar.YEAR, -1);
			logger.debug("startDate after roll: " + calendar);
			session.setAttribute(" set default startDate", calendar.getTime());
		}
		
	}
	*/
		
	
	
	
	
	protected String saveFile(MultipartHttpServletRequest request, ArvedType arvedType) {
		String pdfLocation = null;
		try{
			MultipartFile uploadFile = request.getFile("pdf");
			if(uploadFile == null){
				return null;
			}
			String fileName = uploadFile.getOriginalFilename();
			if(fileName == null || "".equals(fileName)){
				return null;
			}
			String contextPath = servletContext.getRealPath("");
			String saveTo = contextPath + File.separator + "resources" + File.separator + "pdf" + File.separator + arvedType.getIdentifier() + File.separator + fileName;
			
			pdfLocation = "/resources/pdf/" + arvedType.getIdentifier() + "/" +  fileName;
			File file = new File(saveTo);
			if(!file.exists()){
				file.mkdirs();
				uploadFile.transferTo(file);
				
			}
			
		}catch(Exception e){
			logger.error("saving file filed: ",  e);
			return pdfLocation;
		}
		
		return pdfLocation;
	}
	
	private void printRequestparams(Map<String, String[]> params){
		for(Entry<String, String[]> e: params.entrySet()){
			String key = e.getKey();
			String[] value = e.getValue();
			logger.info("key: " + key);
			logger.info("value: " + value[0]);
			
		}
	}
	
	private Boolean checkBoxToBoolean(String text){
		if("on".equals(text)){
			return true;
		}else{
			return Boolean.valueOf(text);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	protected ArvedFromDatabase arvedFromDatabase;
	public ArvedFromDatabase getArvedFromDatabase() {
		return arvedFromDatabase;
	}

	public void setArvedFromDatabase(ArvedFromDatabase arvedFromDatabase) {
		this.arvedFromDatabase = arvedFromDatabase;
	}

	@Autowired
	protected ServletContext servletContext;
	public ServletContext getServletContext() {
		return servletContext;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	
	
	@Autowired
	protected ArvedUserDao arvedUserDao;
	public ArvedUserDao getArvedUserDao(){
		return arvedUserDao;
	}
	
	public void setArvedUserDao(ArvedUserDao arvedUserDao){
		this.arvedUserDao = arvedUserDao;
	}
}
