package com.api.controller;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.api.model.SentBoxModel;
import com.api.model.UserModel;
import com.api.service.BinService;
import com.api.service.InBoxService;
import com.api.service.SendMessage;
import com.api.service.SentBoxService;
import com.api.service.UserService;


@Controller
@SessionAttributes("usermail")
public class MailCastingController {
	
	@Autowired
	BinService binService;
	@Autowired
	InBoxService inboxService;
	@Autowired
	SentBoxService sentboxService;
	@Autowired
	UserService userService;
	@Autowired
	SendMessage messageService;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	private Logger logger=Logger.getLogger(getClass().getName());
	
//Header Mapping	
	

	
	@GetMapping(value="/index")
	public ModelAndView showIndex(@RequestParam(value = "error", required = false) String error,Model m)  {
		logger.info("\nInside index");
		if(error!=null) {
			String errorMsg="Please check your Email and Password";
			logger.info("\nThere is an error while logging in \nError : "+errorMsg);
			m.addAttribute("serverMessage", errorMsg);
		}
		
		return new ModelAndView("index");
	}
	
	
	@GetMapping(value="/home")
	public ModelAndView showHome(Model m) {
		logger.info("\nLogin Succesful");
		logger.info("\nInside Home");
		String usermail=(String) m.getAttribute("usermail");
		
		if(usermail==null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
			if ((auth!=null)
	        		&&(auth instanceof UsernamePasswordAuthenticationToken)
	        		&&auth.isAuthenticated())
	        {
	         
	          UserDetails userDetail = (UserDetails) auth.getPrincipal();	
	          usermail=userDetail.getUsername();
	          
	          m.addAttribute("usermail", usermail);
	          
	        
	     
	          logger.info("\nSession Attribute UserName :" + m.getAttribute("usermail"));
	        
	          return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));
		
	       }
	        
	       else
	    	   return new ModelAndView("index","serverMessage","Please Login to continue");
	       
	    }
		else
			return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));
	}
	
	@GetMapping(value="/bin")
	public ModelAndView showBin(@ModelAttribute("usermail") String usermail) {
		return new ModelAndView("bin","mails",binService.getBinMailsByMailId(usermail));
	}
	
	@GetMapping(value="/sent")
	public ModelAndView showSentBox(@ModelAttribute("usermail") String usermail) {
		return new ModelAndView("sent","mails",sentboxService.getAllMailsByEmail(usermail));
	}
	
	@GetMapping(value="/myProfile")
	public ModelAndView showProfile(@ModelAttribute("usermail") String usermail,Model m)  {
		m.addAttribute("success","");
		return new ModelAndView("myProfile","user",userService.getUserByEmail(usermail));
	}
	
	@GetMapping(value="/compose")
	public ModelAndView showCompose(@ModelAttribute("usermail") String usermail) {
		return new ModelAndView("compose","mail",new SentBoxModel(usermail));
	}

	@GetMapping(value="/register-page")
	public ModelAndView showRegisterPage(){
		return new ModelAndView("Register","user",new UserModel());
	}
	
	@GetMapping(value="/contactus")
	public ModelAndView showContactUs(@ModelAttribute("usermail") String usermail){
		SentBoxModel mail=new SentBoxModel(usermail);
		mail.setReciever("helpdesk@mailcasting.com");
		return new ModelAndView("contactus","mail",mail);
	}
	
	
//send mail	
	@PostMapping(value="/composeEmail")
	private ModelAndView composeEmail(@ModelAttribute("usermail")String usermail,@ModelAttribute("mail")SentBoxModel mail,Model m)  {
		logger.info("\nInside Compose Email");
			if(messageService.sendMsg(mail))
			return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));
			else {
				
				m.addAttribute("serverMesssage","Receiver Not Found");
				return new ModelAndView("compose","mail",mail);
			}
		
		}
	
	
//Show Message
	@GetMapping(value="/getInBoxMail")
	public ModelAndView getInboxMail(@RequestParam("id") String id,Model m) {
		
		m.addAttribute("mail", inboxService.getMailById(Integer.parseInt(id)));
		return new ModelAndView("GetInBoxMail");
	}
	@GetMapping(value="/getSentBoxMail")
	public ModelAndView getSentboxMail(@RequestParam("id") String id,Model m) {
		m.addAttribute("mail", sentboxService.getMailById(Integer.parseInt(id)));
		return new ModelAndView("GetSentMail");
	}
	@GetMapping(value="/getBinMail")
	public ModelAndView geBinboxMail(@RequestParam("id") String id,Model m) {
		m.addAttribute("mail", binService.getMailById(Integer.parseInt(id)));
		return new ModelAndView("GetBinMail");
	}
	
	
//delete mails
	@GetMapping(value="/deleteSentboxMail")
	public ModelAndView deleteSentboxMail(@ModelAttribute("usermail")String usermail,@RequestParam("id")String id) {
		binService.addSentBoxMailtoBin(Integer.parseInt(id));
		return new ModelAndView("sent","mails",sentboxService.getAllMailsByEmail(usermail));
	}
	@GetMapping(value="/deleteInboxMail")
	public ModelAndView deleteinboxMail(@ModelAttribute("usermail")String usermail,@RequestParam("id")String id) {
		binService.addInboxMailtoBin(Integer.parseInt(id));
		return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));

	}
	@GetMapping(value="/deleteBinboxMail")
	public ModelAndView deleteBinboxMail(@ModelAttribute("usermail")String usermail,@RequestParam("id")String id) {
		binService.deleteByBinId(Integer.parseInt(id));
		return new ModelAndView("bin","mails",binService.getBinMailsByMailId(usermail));
	}

	
//Retrieve from bin
	@GetMapping(value="/retriveMail")
	private ModelAndView retriveFromBin(@ModelAttribute("usermail")String usermail,@RequestParam("id")String id) {
			
			String type=binService.retriveFromBin(Integer.parseInt(id));
			
			if(type.equalsIgnoreCase("inbox")) 
				return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));
			
			if(type.equalsIgnoreCase("sentbox")) 
				return new ModelAndView("sent","mails",sentboxService.getAllMailsByEmail(usermail));
				
		
			return new ModelAndView("index","user",new UserModel());
		
	}
	
		

//register User	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	private ModelAndView registerUser(@Valid @ModelAttribute("user")UserModel user,BindingResult br)  {
			
			if(br.hasErrors())
				return new ModelAndView("Register");
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			if(userService.register(user))
			{
			
			String register= "You are Successfully registered";
			
			return new ModelAndView("index","serverMessage",register);
			}
			else
			{
				String register= "User Email Already Taken";
				
				return new ModelAndView("Register","serverMessage",register);
			}
		
	}
		
//password Change Request	
	@PostMapping(value="/validate")
	private ModelAndView validatePassword(Model m,@RequestParam("id")String id,@ModelAttribute("usermail") String usermail,@RequestParam("password")String password) {

		if(userService.validatePassword(Integer.parseInt(id),password)) 
			m.addAttribute("success", "success");
		else 
			m.addAttribute("success", "Invalid");

		return new ModelAndView("myProfile","user",userService.getUserByEmail(usermail));
		
		
	}
	@PostMapping(value="/newPasswordRequest")
	private ModelAndView changePassword(Model m,@RequestParam("id")String id,@ModelAttribute("usermail") String usermail,@RequestParam("password")String password)  {
		
		
		if(userService.changePassword(Integer.parseInt(id),password)) {
			m.addAttribute("newPassword", "Password SuccesFully Changed");
			m.addAttribute("success","");	
		}
		return new ModelAndView("myProfile","user",userService.getUserByEmail(usermail));
		
	
	}

		
}

