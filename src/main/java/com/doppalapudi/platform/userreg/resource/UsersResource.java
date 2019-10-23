package com.doppalapudi.platform.userreg.resource;

import com.doppalapudi.platform.userreg.model.Role;
import com.doppalapudi.platform.userreg.model.User;
import com.doppalapudi.platform.userreg.repository.RolesRepository;
import com.doppalapudi.platform.userreg.repository.UsersRepository;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UsersResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersResource.class);

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        return "views/list";
    }

    @GetMapping("/signin")
    public String signIn(Model model) {
        return "views/loginForm";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());        
        model.addAttribute("roles", getRoleNames());
        return "views/registerForm";
    }
    
    private Set<String> getRoleNames() {
    	return rolesRepository.findAll().stream()
        .map(role -> role.getRoleName()).collect(Collectors.toSet());    	
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
        	model.addAttribute("roles", getRoleNames());
            return "views/registerForm";
        }
        if(usersRepository.findByUsernameLike(user.getUsername()) != null) {
        	model.addAttribute("roles", getRoleNames());
            model.addAttribute("exist",true);
            return "views/registerForm";
        }
        
        LOGGER.debug("passwordEncoder : {}", passwordEncoder);
        
        LOGGER.error("user.getRoles() : {}", user.getRoles());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        /*Role userRole = new Role();
        userRole.setRoleName("USER");
        
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        
        user.setRoles(Stream.of(userRole, adminRole).collect(Collectors.toSet()));*/
        usersRepository.save(user);
        return "views/success";
    }
    
    @GetMapping("/editUser")
    public String editUser(HttpServletRequest request, Model model) {
    	String userName = request.getParameter("username");
    	Optional<User> user = usersRepository.findById(userName);
    	if(user.isPresent()) {
    		model.addAttribute("user", user.get());
    		model.addAttribute("roles", getRoleNames());
            return "views/updateUser";    		
    	}
    	return "forward:users";
    }
    
    @PostMapping("/updateUser")
    public String updateUser(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
        	LOGGER.debug("bindingResult : {}", ReflectionToStringBuilder.reflectionToString(bindingResult));
            return "views/updateUser";
        }
        
        LOGGER.debug("user : {}", ReflectionToStringBuilder.reflectionToString(user));
        User dbUser = usersRepository.findByUsernameLike(user.getUsername());
        LOGGER.debug("dbUser : {}", ReflectionToStringBuilder.reflectionToString(dbUser));

        if(dbUser != null) {
        	dbUser.setFirstName(user.getFirstName());
            dbUser.setLastName(user.getLastName());
            dbUser.setEmail(user.getEmail());
            dbUser.setRoles(user.getRoles());
            usersRepository.save(dbUser);
        }
        return "redirect:users";
    }
    
    @GetMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request) {
    	String userName = request.getParameter("username");
    	usersRepository.deleteById(userName);
    	return "forward:users";
    }
    
    
    
}
