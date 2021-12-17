package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.service.UserServiceImpl;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;

    public AdminController(UserServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String allUsers(Model model) {
        List<User> users = new ArrayList<>();
        userService.allUsers().forEach(users::add);
        model.addAttribute("usersList", users);
        return "admin";
    }

    @GetMapping("/add")
    public String addPage() {
        return "addUser";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("tempRoles") String[] tempRoles) {

        user.setRoles(rolesList(tempRoles));
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable("id") long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        String[] rolesList = {"ADMIN", "USER", "STRANGER"};
        model.addAttribute("rolesList", rolesList);
        return "adminEditUser";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "tempRoles", required = false) String[] tempRoles) {
        if (tempRoles != null) {
            user.setRoles(rolesList(tempRoles));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }

    List<Role> rolesList(String[] roles){
        List<Role> roleList = new ArrayList<>();
        for (String st : roles) {
            if (st.equals("ADMIN")) {
                roleList.add(new Role(1L, "ADMIN"));
            }
            if (st.equals("USER")) {
                roleList.add(new Role(2L, "USER"));
            }
            if (st.equals("STRANGER")) {
                roleList.add(new Role(3L, "STRANGER"));
            }
        }
        return roleList;
    }
}
