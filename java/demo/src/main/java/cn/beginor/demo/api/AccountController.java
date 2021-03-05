package cn.beginor.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @GetMapping("/info")
    public String getAccountInfo() {
        return "hello, world!";
    }

}
