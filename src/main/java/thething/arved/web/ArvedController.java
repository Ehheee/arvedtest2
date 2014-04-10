package thething.arved.web;

import java.util.List;
import java.util.Map;

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

@Controller
@RequestMapping("/")
public class ArvedController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String getArved(HttpServletRequest request, HttpSession session, Model model){
		AbstractArvedFilter filter = this.processRequest(request, session);
		this.setDefaults(filter);
		this.filterToModel(filter, model);
	
		
		return "main";
	}
	
	@RequestMapping(value = "/arve/{s:[a-zA-Z]+}", method = RequestMethod.GET)
	public String newArve(Model model, @PathVariable("s") String s){
		model.addAttribute("type", ArvedType.fromString(s));
		model.addAttribute("arve", new AbstractArve());
		return "main";
	}
	
	
	@RequestMapping(value = "/arve/{id:[0-9]+}", method = RequestMethod.GET)
	public String getArve(@PathVariable("id") Long id, Model model){
		AbstractArve arve = arvedFromDatabase.getArve(id);
		model.addAttribute("type", arve.getType());
		model.addAttribute("arve", arve);
		
		return "main";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model){
		model.addAttribute("includedTypes", ArvedType.values());
		model.addAttribute("jspContent", "test.jsp");
		return "main";
	}
	
	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
	public String getArvedByType(@PathVariable("type") String type, HttpServletRequest request, HttpSession session, Model model){
		logger.info("hit2???");
		AbstractArvedFilter filter = this.processRequest(request, session);
		this.setDefaults(filter);
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
	
	@RequestMapping(value = "/ob/{objekt}", method = RequestMethod.GET)
	public String getArvedByObjekt(@PathVariable("objekt") String objekt, HttpServletRequest request, HttpSession session, Model model){
		logger.info("Requested objekt: " + objekt);
		AbstractArvedFilter filter = this.processRequest(request, session);
		this.setDefaults(filter);
		filter.setObjekt(objekt);
		this.filterToModel(filter, model);
		return "main";
	}
	
	@RequestMapping(value = "/ob/{objekt}/{type}", method = RequestMethod.GET)
	public String getArvedByObjektAndType(@PathVariable("objekt") String objekt, @PathVariable("type") String type, HttpServletRequest request, HttpSession session, Model model){
		
		AbstractArvedFilter filter = this.processRequest(request, session);
		this.setDefaults(filter);
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
