package com.ldm.blog.web.admin;
import com.ldm.blog.po.User;
import com.ldm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

//登陆控制器

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    //跳转到登陆页面
    @GetMapping
    public String loginPage(){

        return "admin/login";
    }

    //登陆之后，把用户名和密码传过来处理，判断
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        //登陆成功
        if(user != null){
            //不要把密码传到前端去
            user.setPassword(null);
            session.setAttribute("user",user);
            //登陆成功，跳转到admin/index页面
            return "admin/index";
        }
        //登陆失败
        else{
            //把登陆失败的信息返回给前端
            attributes.addFlashAttribute("message", "用户名和密码错误");
            //返回登陆页面
            return "redirect:/admin";

        }
    }

    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session){
        //把登陆进去的user拿掉
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
