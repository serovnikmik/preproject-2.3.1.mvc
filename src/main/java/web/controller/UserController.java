package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

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

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUser(id));
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
        userService.delete(id);
        return "redirect:/user/list";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "user/edit";
        }
        userService.update(user);
        return "redirect:/user/" + id;
    }
}
