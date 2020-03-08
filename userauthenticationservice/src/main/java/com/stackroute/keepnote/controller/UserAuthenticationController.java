package com.stackroute.keepnote.controller;


import com.stackroute.keepnote.exception.UserAlreadyExistsException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.UserAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * As in this assignment, we are working on creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with the @Controller annotation
 * has handler methods which return a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
public class UserAuthenticationController {

    /*
	 * Autowiring should be implemented for the UserAuthenticationService. (Use Constructor-based
	 * autowiring) Please note that we should not create an object using the new
	 * keyword
	 */
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    LoginResponse login = null;

    Boolean authenticated = true;

    public UserAuthenticationController(UserAuthenticationService authicationService) {
        this.userAuthenticationService = userAuthenticationService;
	}

/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations:
	 * 1. 201(CREATED) - If the user created successfully. 
	 * 2. 409(CONFLICT) - If the userId conflicts with any existing user
	 * 
	 * This handler method should map to the URL "/api/v1/auth/register" using HTTP POST method
	 */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/v1/auth/register", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody final User user) {

        boolean savedUser = true;

        try
        {
            userAuthenticationService.saveUser(user);
        }
        catch (UserAlreadyExistsException e)
        {
            savedUser = false;
        }

        if (savedUser)
        {
            return new ResponseEntity<User>(HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
    }

	/* Define a handler method which will authenticate a user by reading the Serialized user
	 * object from request body containing the username and password. The username and password should be validated 
	 * before proceeding ahead with JWT token generation. The user credentials will be validated against the database entries. 
	 * The error should be return if validation is not successful. If credentials are validated successfully, then JWT
	 * token will be generated. The token should be returned back to the caller along with the API response.
	 * This handler method should return any one of the status messages basis on different
	 * situations:
	 * 1. 200(OK) - If login is successful
	 * 2. 401(UNAUTHORIZED) - If login is not successful
	 * 
	 * This handler method should map to the URL "/api/v1/auth/login" using HTTP POST method
	*/
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/v1/auth/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> loginUser(@RequestBody final User user) {

        boolean loginStatus = true;
        String token = "";

        try {
            token = getToken(user.getUserName(), user.getUserPassword());
            login = new LoginResponse(token);
        }
        catch (UserAlreadyExistsException e)
        {
            loginStatus = false;
        }
        catch (Exception exception)
        {
            loginStatus = false;
        }

        if (loginStatus)
        {
            return new ResponseEntity<LoginResponse>(login, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<LoginResponse>(login, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/v1/isAuthenticated", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Boolean>> isAuthenticated(HttpServletRequest request, HttpServletResponse response) {

        if(!request.getMethod().equals("OPTIONS"))
        {
        	System.out.println("this is not OPTIONS ");
    	System.out.println("request reading"+request.getHeader("Authorization"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("isAuthenticated", request.getHeader("Authorization"));

    boolean loginStatus = true;
        String token = "";
        Map map = new HashMap<String, String>();
      //  map.put("isAuthenticated", request.getHeader("Authorization"));
        map.put("isAuthenticated", Boolean.TRUE);


          /*  try {
            token = getToken(user.getUserId(), user.getUserPassword());
            login = new LoginResponse(token);
        }
        catch (UserAlreadyExistsException e)
        {
            loginStatus = false;
        }
        catch (Exception exception)
        {
            loginStatus = false;
        }

        if (loginStatus)
        {
            return new ResponseEntity<LoginResponse>(login, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<LoginResponse>(login, HttpStatus.CONFLICT);
        }*/
        return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);
        }else{
        	System.out.println("this is OPTIONS ");
        	response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
            response.addHeader("Access-Control-Max-Age", "3600");
            return new ResponseEntity(HttpStatus.OK);

           // return new ResponseEntity<LoginResponse>(login, HttpStatus.CONFLICT);
        }
    }

   /* @RequestMapping(value= "/api/v1/isAuthenticated", method=RequestMethod.OPTIONS)
    public void corsHeaders(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.addHeader("Access-Control-Max-Age", "3600");
    }*/
    
    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }



// Generate JWT token
	public String getToken(final String username, final String password) throws Exception
    {
        User user = null;
        if (username != null && password != null)
        {
            try
            {
                user = userAuthenticationService.findByUserNameAndPassword(username, password);
            }
            catch (UserNotFoundException userNotFoundException)
            {
                throw new UserNotFoundException("Invalid User Login");
            }
        }
        else
        {
            throw new UserNotFoundException("Invalid User Login");
        }

        return  (Jwts.builder().setSubject(username)
                .claim(user.getUserRole(), user.getUserName()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());

    }


}




