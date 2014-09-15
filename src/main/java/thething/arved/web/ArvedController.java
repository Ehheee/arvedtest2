package thething.arved.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import thething.arved.dataobjects.AbstractArve;
import thething.arved.utils.AbstractArvedFilter;
import thething.arved.utils.AbstractArvedFilter.ArvedType;
import thething.arved.utils.AbstractArvedFilter.Period;

@Controller
@RequestMapping("/")
public class ArvedController extends BaseController {

	/**
	 * The general controller method for returning invoices by request parameters.
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getArved(HttpServletRequest request, HttpSession session, Model model){
		AbstractArvedFilter filter = this.processRequest(request, session);
		if(filter.getPeriod() == null){
			filter.setPeriod(Period.CURRENTMONTH);
		}
		this.filterToModel(filter, model);
	
		
		return "main";
	}
	/**
	 * Url for returning the form of a new invoice. Used only when JavaScript is not available.
	 * @param model
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/arve/{s:[a-zA-Z]+}", method = RequestMethod.GET)
	public String newArve(Model model, @PathVariable("s") String s){
		model.addAttribute("type", ArvedType.fromString(s));
		model.addAttribute("arve", new AbstractArve());
		return "main";
	}
	
	/**
	 * Returns a specific invoice if only numbers used in url after /arve/
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/arve/{id:[0-9]+}", method = RequestMethod.GET)
	public String getArve(@PathVariable("id") Long id, Model model){
		AbstractArve arve = arvedFromDatabase.getArve(id);
		model.addAttribute("type", arve.getType());
		model.addAttribute("arve", arve);
		
		return "main";
	}
	
	/**
	 * Just a controller method used to test solutions in jsp pages.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model){
		Map<String, Integer> testMap = new TreeMap<String, Integer>();
		testMap.put("first", 5);
		testMap.put("second", 3);
		testMap.put("third", 1);
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		roles.add("ROLE_USER2");
		roles.add("ROLE_ADMIN");
		model.addAttribute("includedTypes", ArvedType.values());
		model.addAttribute("jspContent", "test.jsp");
		model.addAttribute("testMap",testMap);
		return "test";
	}
	
	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
	public String getArvedByType(@PathVariable("type") String type, HttpServletRequest request, HttpSession session, Model model){
		AbstractArvedFilter filter = this.processRequest(request, session);
		ArvedType arvedType = ArvedType.fromString(type);
		filter.setType(arvedType);
		this.filterToModel(filter, model);
		return "main";
	}
	
	
	@RequestMapping(value = "/ob", method = RequestMethod.GET)
	public String getObjektid(Model model){
		model.addAttribute("objektid", arvedFromDatabase.getObjektid());
		
		return "main";
	}
	/**
	 * Returns invoices by requested project.
	 * @param objekt
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ob/{objekt}", method = RequestMethod.GET)
	public String getArvedByObjekt(@PathVariable("objekt") String objekt, HttpServletRequest request, HttpSession session, Model model) throws Exception{
		logger.info("Requested objekt: " + objekt);
		/*
		if(true){
			throw new Exception();
		}
		*/
		AbstractArvedFilter filter = this.processRequest(request, session);
		filter.setObjekt(objekt);
		this.filterToModel(filter, model);
	
		return "main";
	}
	
	/**
	 * Returns only one type of invoices for requested project.
	 * @param objekt
	 * @param type
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ob/{objekt}/{type}", method = RequestMethod.GET)
	public String getArvedByObjektAndType(@PathVariable("objekt") String objekt, @PathVariable("type") String type, HttpServletRequest request, HttpSession session, Model model){
		
		AbstractArvedFilter filter = this.processRequest(request, session);
		ArvedType arvedType = ArvedType.fromString(type);
		filter.setType(arvedType);
		filter.setObjekt(objekt);
		this.filterToModel(filter, model);
		return "main";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteArve(@PathVariable("id") Long id){
		arvedFromDatabase.deleteArve(id);
		
		return String.valueOf(id);
	}
	
	/**
	 * Post controller for adding new invoices. Different view is returned when javascript is used. 
	 * for javascript only the new row is returned. Without javascript user is redirected back to the invoice that was just added.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String saveArve(MultipartHttpServletRequest request, Model model){
		AbstractArve arve = this.insertArve(request);
		if(arve != null){
			model.addAttribute("type", arve.getType());
			if(request.getParameter("js") != null){
				model.addAttribute("js", true);
				model.addAttribute("arve", arve);
				return "arveRow";
			}else{
				return "redirect:/arve/" + arve.getId();
			}
		}
		return null;
	}
	
	
	
}
