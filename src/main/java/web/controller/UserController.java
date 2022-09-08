package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import web.models.User;
import web.services.UserService;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("list", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/addExamples")
    public String addExamples(){
        userService.saveExampleUsers();
        return "redirect:/user/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUser(id));
        System.out.println(userService.getUser(id));
        return "user/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "user/edit";
    }

    @GetMapping("/deleteAllUsers")
    public String deletAllUsers(){
        userService.deleteAllUsers();
        return "redirect:/user/list";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new User());
        return "user/create";
    }

    @PostMapping("/createUser")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "user/create";
        }

        userService.save(user);
        return "redirect:/user/list";
    }

    @DeleteMapping ("/{id}")
    public String delete(@PathVariable("id") int id){
        System.out.println("Trying to delete user: " + userService.getUser(id));
        userService.delete(id);
        return "redirect:/user/list";
    }

//    @PostMapping("/{id}", method = RequestMethod.DELETE)
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "user/edit";
        }

        System.out.println("Trying to update user: " + userService.getUser(id));
        userService.update(id, user);
        return "redirect:/user/" + id;
    }
}
