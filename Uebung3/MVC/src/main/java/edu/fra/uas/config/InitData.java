package edu.fra.uas.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.model.User;
import edu.fra.uas.service.UserService;
import jakarta.annotation.PostConstruct;

//Die Klasse ist mit @Component annotiert, was bedeutet, dass sie von Spring als Bean verwaltet wird.
@Component
public class InitData {

    //Logger wird verwendet, um Informationen und Fehlermeldungen zu protokollieren.
    private final Logger log = org.slf4j.LoggerFactory.getLogger(InitData.class);
    
    //Die UserService wird injiziert, um Benutzer zu erstellen.
    @Autowired
    UserService userService;

    //Die Methode init() wird mit @PostConstruct annotiert, was bedeutet, dass sie nach der Instanziierung der Klasse aufgerufen wird.
    @PostConstruct
    public void init() {
        log.debug("### Initialize Data ###");

        //Wenn es bereits Benutzer gibt, wird die Methode beendet.
        log.debug("create user admin");
        User user = new User();
        user.setRole("ADMIN");
        user.setFirstName("Administrator");
        user.setLastName("Administrator");
        user.setEmail("admin@example.com");
        user.setPassword("extremeSecurePassword1234");
        userService.createUser(user);

        log.debug("create user alice");
        user = new User();
        user.setRole("USER");
        user.setFirstName("Alice");
        user.setLastName("Adams");
        user.setEmail("alice@example.com");
        user.setPassword("alice1234");
        userService.createUser(user);

        log.debug("create user bob");
        user = new User();
        user.setRole("USER");
        user.setFirstName("Bob");
        user.setLastName("Builder");
        user.setEmail("bob@example.com");
        user.setPassword("bob1234");
        userService.createUser(user);

        log.debug("### Data initialized ###");
    }

}
