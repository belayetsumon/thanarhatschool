/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itgarden.website.controller;

import com.itgarden.website.module.user.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/front-view")
public class FrontController {

    @RequestMapping("/about-us")
    public String aboutUs(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/aboutUs";
    }

    @RequestMapping("/mission-vision")
    public String missionVvision(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/mission-vision";
    }

    @RequestMapping("/executive-committee")
    public String executiveCommittee(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/executive-committee";
    }

    @RequestMapping("/constitution")
    public String constitution(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/constitution";
    }

    @RequestMapping("/advisory-council")
    public String advisoryCouncil(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/advisory-council";
    }

    @RequestMapping("/all-member")
    public String allmember(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/all-member";
    }

    @RequestMapping("/member-search")
    public String membersearch(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/all-search";
    }

    @RequestMapping("/member-login")
    public String memberlogin(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/member-login";
    }

    @RequestMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/forgot-password";
    }

    @RequestMapping("/member-registration")
    public String memberRegistration(Model model,Users users) {
        model.addAttribute("attribute", "value");
        return "frontview/member-registration";
    }

    @RequestMapping("/batch-modaretor")
    public String batchmodaretor(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/batch-modaretor";
    }

    @RequestMapping("/news-events")
    public String newsEvents(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/newsEvents";
    }

    @RequestMapping("/news-details")
    public String newsdetails(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/newsdetails";
    }

    @RequestMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/gallery";
    }

    @RequestMapping("/donations")
    public String donations(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/donations";
    }

    @RequestMapping("/contactUs")
    public String contactUs(Model model) {
        model.addAttribute("attribute", "value");
        return "frontview/contactUs";
    }

}
