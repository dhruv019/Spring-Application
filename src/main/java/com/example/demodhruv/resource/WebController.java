package com.example.demodhruv.resource;

import com.example.demodhruv.model.User;
import com.example.demodhruv.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/dhruv")
public class WebController {

    @Autowired
    UserRepo ur;

    @GetMapping(value = "/") //when we call some page and pass value then we use PostMapping
    public String getHome(){
        return "index";
    }

    @PostMapping(value = "/welcome")
    public String welcome(@RequestParam("sub")String sub){ //retrieving data from page having name of text sub and storing it in string sub
        String file="";
        if(sub.equals("Login")){
            file="login";
        }
        else{
            file="register";
        }
        return file;
    }
    @PostMapping(value = "/registerme")
    public String registerme(@RequestParam("uname")String uname,@RequestParam("upass")String upass,@RequestParam("uage")String uage){
        User u1 = new User(uname, upass, Integer.parseInt(uage));
        ur.save(u1);
        return "register";
    }
    @PostMapping(value = "/loginme")
    public ModelAndView loginme(Model model, @RequestParam("uname")String uname, @RequestParam("upass")String upass){
        User u1 = ur.findByNameAndPass(uname,upass);
        String file="";
        ModelAndView mod = new ModelAndView();

        if(u1==null){
            model.addAttribute("errormsg","Invalid Username or Password");
            mod.addObject(model);
            mod.setViewName("login");
        }
        else{
            mod.setViewName("home");
        }
        return mod;
    }

    @PostMapping(value = "/gethome")
    public ModelAndView getHome(Model model, @RequestParam("sub")String sub){
        ModelAndView mod = new ModelAndView();
        if (sub.equals("Show")){
            List<User> list = ur.findAll();
            model.addAttribute("userlist",list);
            mod.addObject(model);
            mod.setViewName("show");
        }
        if (sub.equals("Delete")){
            mod.setViewName("delete");
        }
        if (sub.equals("Update")){
            mod.setViewName("update");
        }
        return mod;
    }

    @PostMapping(value = "/delete")
    public ModelAndView delete(Model model, @RequestParam("deleteid")String deleteid){
        ModelAndView mod = new ModelAndView();
        int deleteid2 = Integer.parseInt(deleteid);
        User u1 = ur.deleteById(deleteid2);
        if(u1==null){
            model.addAttribute("error","User Deleted");
            mod.addObject(model);
            mod.setViewName("delete");
        }
        else{
            model.addAttribute("error","Invalid User Id");
            mod.addObject(model);
            mod.setViewName("delete");
        }
        return mod;
    }

    @PostMapping(value = "/updpass")
    public String updatePassword(@RequestParam("tid")String tid, @RequestParam("tpa")String tpa){
        ur.updatePassword(Integer.parseInt(tid),tpa);
        return "home";
    }
}