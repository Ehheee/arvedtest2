package thething.arved.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thething.arved.dataobjects.ArvedUser;
import thething.arved.service.SecurityUser;

@Controller
@RequestMapping(value="/user")
public class ArvedUserController extends BaseController {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getUser(Model model, HttpServletRequest request){
		SecurityUser securityUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArvedUser user = securityUser.getUser();
		model.addAttribute("user", user);
		return "main";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postUser(@ModelAttribute("user") ArvedUser newUser){
		SecurityUser securityUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArvedUser oldUser = securityUser.getUser();
		if(oldUser.getId() != newUser.getId()){
			logger.error("unexpected POST made to /user");
			return "main";
		}
		oldUser = arvedUserDao.selectById(newUser.getId());
		oldUser.setFullName(newUser.getFullName());
		oldUser.setPageSize(newUser.getPageSize());
		oldUser.setUserName(newUser.getUserName());
		arvedUserDao.updateArvedUser(oldUser);
		
		return "main";
	}
	
	@RequestMapping(value="/parool", method=RequestMethod.POST)
	public String changeParool(@RequestParam("userId") long userId,
								@RequestParam("vanaParool") String vanaParool,
								@RequestParam("uusParool1") String uusParool1,
								@RequestParam("uusParool2") String uusParool2,
								RedirectAttributes redirectAttributes){
		
		boolean passwordChanged = this.changeParoolLogic(userId, vanaParool, uusParool1, uusParool2);
		redirectAttributes.addAttribute("passwordChanged", passwordChanged);

		return "redirect:/profiil";
	}
	
	
	private boolean changeParoolLogic(long userId, String vanaParool, String uusParool1, String uusParool2){
		if(uusParool1.length() < 5){
			return false;
		}
		ArvedUser arvedUser = arvedUserDao.selectById(userId);
		vanaParool = passwordEncoder.encodePassword(vanaParool, arvedUser.getSalt());
		if(arvedUser.getPassword().equals(vanaParool) && uusParool1.equals(uusParool2)){
			double d  = Math.random()*1000000;
			String salt = String.valueOf(d);
			String newPassword = passwordEncoder.encodePassword(uusParool1, salt);
			arvedUser.setSalt(salt);
			arvedUser.setPassword(newPassword);
			arvedUserDao.updateArvedUser(arvedUser);
			return true;
		}else{
			return false;
		}
		
	}
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
}
