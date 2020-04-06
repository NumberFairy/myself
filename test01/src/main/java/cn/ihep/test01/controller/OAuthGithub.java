package cn.ihep.test01.controller;

import cn.ihep.test01.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OAuthGithub {

    @Autowired
    public AccountService accountService;

    @GetMapping("/eating/callback")
    @ResponseBody
    public void getAccessTokenByCode(String code, HttpServletRequest request) throws Exception {
        accountService.getTokenFromGithub(code);
    }
}
