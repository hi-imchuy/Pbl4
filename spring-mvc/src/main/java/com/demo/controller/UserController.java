package com.demo.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.model.User;
import com.demo.service.UserService;

@SuppressWarnings("unused")
@Controller
@MultipartConfig(
		  fileSizeThreshold = 256 * 256 * 1, // 1 MB
		  maxFileSize = 256 * 256 * 10,      // 10 MB
		  maxRequestSize = 256 * 256 * 100   // 100 MB
		)

public class UserController {
	@Autowired
    private ServletContext servletContext;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request) {

		int userID = userService.checkLogin(username, password);

		if (userID != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("userID", userID);
			return new ModelAndView("redirect:" + "/home");
		} else {
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("error", "Invalid username or password");
			modelAndView.addObject("username", username);
			return modelAndView;
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@RequestParam("username") String username, 
								@RequestParam("password") String password,
								@RequestParam("email") String email,
								HttpServletRequest request) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setEmail(email);
		System.err.println(username + password + email);
		boolean isCreated = userService.createUser(newUser);
		System.err.println(isCreated);
		if (isCreated) {
			// Redirect to the login page after successful registration
			return new ModelAndView("redirect:/login");
		} 
		
		ModelAndView modelAndView = new ModelAndView("signup");
		modelAndView.addObject("error", "Signup failed. Please try again.");
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate the session
		}
		return new ModelAndView("redirect:/login");
	}
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView updateProfile(@RequestParam("firstname") String firstname, 
									  @RequestParam("lastname") String lastname, 
									  @RequestParam("location") String location,
									  @RequestParam("userID") int userID,
									  HttpServletRequest request) {
		userService.updateUser1(lastname, firstname, location, userID);
		return new ModelAndView("redirect: /spring-mvc/profile");
	}
	
	@RequestMapping(value = "/findFriend", method = RequestMethod.GET)
	public ModelAndView findFriend(@RequestParam("friendID") String friendID,
	                               HttpServletRequest request) {
	    User user1 = userService.getUser(friendID);

	    // Assuming "user" is the object you want to pass to the redirected URL
	    ModelAndView modelAndView = new ModelAndView("findFriend");
	    modelAndView.addObject("user", user1);

	    return modelAndView;
	}
	
	
	@RequestMapping(value = "/fileuploadservlet", method = RequestMethod.POST)
	public ModelAndView FileUploadServlet(@RequestParam("file") MultipartFile file,
											@RequestParam("user") String user,
	                                      HttpServletRequest request) throws IOException, ServletException {
	    try {
	        if (file != null && !file.isEmpty()) {
	            String uploadDirectory = servletContext.getRealPath("/WEB-INF/template/web/avt");
	            System.err.println(uploadDirectory);
	            File dest = new File(uploadDirectory + File.separator + user + ".jpg");
	            System.err.println(uploadDirectory + File.separator + user + ".jpg");
	            file.transferTo(dest);
	        } else {
	            // Handle the case when the file is empty or null
	            return new ModelAndView("logout");
	        }
	    } catch (IOException | IllegalStateException e) {
	        // Handle specific exceptions accordingly
		    return new ModelAndView("redirect: /spring-mvc/profile");
	    }
	    
	    return new ModelAndView("redirect: /spring-mvc/profile");
	}

}
