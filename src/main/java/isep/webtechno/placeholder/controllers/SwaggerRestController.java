package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.User_bak;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class SwaggerRestController {

    List<User_bak> users = new ArrayList<User_bak>();
    {
        users.add(new User_bak("Test","Un","user1@test.com","1111","ADMIN"));
        users.add(new User_bak("Test","Deux","user2@test.com","2222","SUPERVISOR"));
        users.add(new User_bak("Test","Trois","user3@test.com","3333","USER"));
        users.add(new User_bak("Test","Quatre","user4@test.com","4444","USER"));
    }
    @RequestMapping(value="/getUsers",method = RequestMethod.GET,produces = "applicaiton/json")
    public List<User_bak> getUsers(){
        return users;
    }
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, produces = "application/json")
    public User_bak getUserById(@PathVariable(value = "id") int id) {
        return users.stream().filter(x -> x.getId()==(id)).collect(Collectors.toList()).get(0);
    }
    @RequestMapping(value = "/getUser/role/{role}", method = RequestMethod.GET, produces = "application/json")
    public List<User_bak> getUserByRole(@PathVariable(value = "role") String role) {
        return users.stream().filter(x -> x.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

}
