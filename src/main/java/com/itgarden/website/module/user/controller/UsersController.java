/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.website.module.user.controller;

import com.itgarden.website.module.user.model.Status;
import com.itgarden.website.module.user.model.Users;
import com.itgarden.website.module.user.ripository.RoleRepository;
import com.itgarden.website.module.user.ripository.UsersRepository;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("alluser", usersRepository.findAll());
        return "user/allusers";
    }

    @RequestMapping("/userbystatus")
    public String userByStatus(Model model, @RequestParam(value = "status", required = false) Status status) {

        model.addAttribute("status", Status.values());

        model.addAttribute("alluser", usersRepository.findByStatus(status));

        return "user/allusers_by_status";

    }

    @RequestMapping("/view/{uid}")
    public String view(Model model, @PathVariable Long uid) {
        model.addAttribute("users", usersRepository.findById(uid));
        return "user/view";
    }

    @RequestMapping("/registrations")
    public String registrations(Model model, Users users) {

        model.addAttribute("role", roleRepository.findAll());
        model.addAttribute("status", Status.values());
        return "user/registrations";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, Users users) {
        model.addAttribute("users", usersRepository.getOne(id));

        model.addAttribute("role", roleRepository.findAll());
        model.addAttribute("status", Status.values());
        return "user/registrations";
    }

//    @RequestMapping("/Esave")
//    //@Transactional
//    public String editSave(Model model, @Valid Users users, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("department", departmentRepository.findAll());
//            model.addAttribute("subDepartment", subDepartmentRepository.findAll());
//            model.addAttribute("role", roleRepository.findAll());
//            model.addAttribute("status", Status.values());
//            return "pims/users/registrations_edit";
//        }
//
//        // users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
//        try {
//            
//          
//            users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
//  
//            usersRepository.updateUsers(governmentId, name, email, mobile, department, subDepartment, role, Status.Others, remarks);save(users);
//            return "redirect:/users/index";
//            
//        } catch (Exception e) {
//            model.addAttribute("department", departmentRepository.findAll());
//            model.addAttribute("subDepartment", subDepartmentRepository.findAll());
//            model.addAttribute("role", roleRepository.findAll());
//            model.addAttribute("status", Status.values());
//            redirectAttributes.addFlashAttribute("message", e);
//            model.addAttribute("message", e);
//            return "pims/users/registrations_edit";
//        }
//    }
//    
//    
//    
    @RequestMapping("/save")
    //@Transactional
    public String save(Model model, @RequestParam(value = "password2", required = false) String password2, @Valid Users users, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("role", roleRepository.findAll());
            model.addAttribute("status", Status.values());
            return "user/registrations";
        }

        // users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        try {

            if (users.getPassword().isEmpty() && password2 != null && users.getId() != null) {

                users.setPassword(password2);
            } else {

                users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
            }

            usersRepository.save(users);
            return "redirect:/users/index";

        } catch (Exception e) {

            model.addAttribute("role", roleRepository.findAll());
            model.addAttribute("status", Status.values());
            redirectAttributes.addFlashAttribute("message", e);
            model.addAttribute("message", e);
            return "user/registrations";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, Users users) {
        usersRepository.deleteById(id);
        return "redirect:/users/index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("attribute", "value");

        model.addAttribute("logout", " You are successfully logout");
        return "user/login";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/users/login";
    }

    @RequestMapping("/detailsinfo/{id}")
    public String details(Model model, @PathVariable Long id) {
        model.addAttribute("employee", usersRepository.getOne(id));
        return "pims/details/details";
    }

    @RequestMapping("/uregistrations")
    public String uregistrations(Model model, Users users) {

        model.addAttribute("role", roleRepository.findAll());
        return "user/uregistrations";
    }

    @RequestMapping("/usave")
    public String usave(Model model, @Valid Users users, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("role", roleRepository.findAll());
            return "user/uregistrations";
        }
        users.setStatus(Status.Pending);
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
        redirectAttributes.addAttribute("success", " Congratulations you have successfully registered. please contact with system adminstrator.");
        return "redirect:users/uregistrations";
    }

    @RequestMapping("/front-user-save")
    public String frontUserSave(Model model, @Valid Users users, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("role", roleRepository.findAll());
            return "frontview/member-registration";
        }
        users.setStatus(Status.Pending);
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
        redirectAttributes.addFlashAttribute(" Congratulations you have successfully registered. please contact with system adminstrator.");
        return "redirect:/front-view/member-registration";
    }

}
