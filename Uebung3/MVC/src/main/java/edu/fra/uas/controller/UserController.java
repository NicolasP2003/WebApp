package edu.fra.uas.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fra.uas.model.User;
import edu.fra.uas.service.UserService;

@Controller
public class UserController {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // http://127.0.0.1/
    @RequestMapping
	public String get() {
        log.debug("get() is called");
		return "index.html";
	}

    // http://127.0.0.1/list
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list() is called");
        Iterable<User> userIter = userService.getAllUsers();
        List<User> users = new ArrayList<>();
        for (User user : userIter) {
            users.add(user);
        }
        model.addAttribute("users", users);
        return "list.html";
    }

    // http://127.0.0.1/find?id=1
    @RequestMapping(value = {"/find"}, method = RequestMethod.GET)
    // @RequestParam("id") Long userId: The value of the id parameter is mapped to the userId parameter of the find() method.
    public String find(@RequestParam("id") Long userId, Model model) {
        // The log.debug() method is used to log a debug message.
        log.debug("find() is called");
        // The getUserById() method is called to retrieve the user with the specified id.
        User user = userService.getUserById(userId);
        // The user object is added to the model.
        model.addAttribute("user", user);
        // The name of the view file is returned.
        return "find.html";
    }

    // http://127.0.0.1/add?firstName=Celine&lastName=Clever&email=celine.clever%40example.com&password=123456
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    // The add() method is called when the /add URL is accessed using the GET method.
    public String add(@RequestParam("firstName") String firstName, 
    // The value of the firstName parameter is mapped to the firstName parameter of the add() method.
                      @RequestParam("lastName") String lastName, 
    // The value of the lastName parameter is mapped to the lastName parameter of the add() method.                  
                      @RequestParam("email") String email, 
    // The value of the email parameter is mapped to the email parameter of the add() method.                  
                      @RequestParam("password") String password, 
    // The value of the password parameter is mapped to the password parameter of the add() method.                  
                      Model model) throws MissingServletRequestParameterException {
    // The add() method throws a MissingServletRequestParameterException if any of the required parameters are missing.
    // The log.debug() method is used to log a debug message.
        log.debug("add() is called");
        // A new User object is created and initialized with the values of the parameters.
        User user = new User();
        // The role of the user is set to "USER".
        user.setRole("USER");
        // The first name of the user is set to the value of the firstName parameter.
        user.setFirstName(firstName);
        // The last name of the user is set to the value of the lastName parameter.
        user.setLastName(lastName);
        // The email of the user is set to the value of the email parameter.
        user.setEmail(email);
        // The password of the user is set to the value of the password parameter.
        user.setPassword(password);
        // The createUser() method is called to create the user.
        userService.createUser(user);
        // The user object is added to the model.
        model.addAttribute("user", user);
        // The name of the view file is returned.
        return "add.html";
    }

    // http://127.0.0.1/update
    @RequestMapping(value = {"/update"}, method = RequestMethod.GET)
    public String update() {
        log.debug("update() is called");
        return "update.html";
    }

    // http://127.0.0.1/updated?id=2&firstName=Alice&lastName=Abraham&email=alice%40example.com&password=123A456
    @RequestMapping(value = {"/updated"}, method = { RequestMethod.GET, RequestMethod.POST })
    public String update(@RequestParam("id") Long userId, 
                         @RequestParam("firstName") String firstName, 
                         @RequestParam("lastName") String lastName, 
                         @RequestParam("email") String email, 
                         @RequestParam("password") String password, 
                         Model model) {
        log.debug("updated() is called");
        User user = userService.getUserById(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userService.updateUser(user);
        model.addAttribute("user", user);
        return "updated.html";
    }

    // http://127.0.0.1/delete/3
    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, Model model) {
        log.debug("delete() is called");
        User user = userService.getUserById(id);
        userService.deleteUser(id);
        model.addAttribute("user", user);
        return "deleted.html";
    }

}
