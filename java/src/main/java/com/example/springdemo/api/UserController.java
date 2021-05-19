package com.example.springdemo.api;

import com.example.springdemo.data.AppUser;
import com.example.springdemo.data.AppUserRepository;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final AppUserRepository userRepo;

    public UserController(AppUserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    // GET api/users?userName=xxx
    @GetMapping("")
    public List<AppUser> search(@RequestParam(required = false) String userName) {
        var userStream = Streamable.of(userRepo.findAll());
        if (userName != null) {
            var upperUserName = userName.toUpperCase();
            userStream = userStream.filter(
                user -> user.getNormalizedUserName().contains(upperUserName)
            );
        }
        return userStream.toList();
    }
}
