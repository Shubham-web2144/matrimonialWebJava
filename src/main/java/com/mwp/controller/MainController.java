package com.mwp.controller;

import com.mwp.model.Contact;
import com.mwp.model.User;
import com.mwp.service.ContactService;
import com.mwp.service.UserService;
import com.util.UploadImag;
import com.util.UserData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    private UserData ud = new UserData();

    @RequestMapping("/index")
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping("/about")
    public ModelAndView getAbout() {
        return new ModelAndView("about");
    }

    @RequestMapping("/contact")
    public ModelAndView getContacct(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return new ModelAndView("contact");
    }

    @PostMapping("/handleContact")
    public String handleContactPage(Model model, @ModelAttribute("contact") Contact contact) {
        System.out.println("Contact email " + contact.getEmail());
        String sendMessage = contactService.sendMessage(contact);
        if(sendMessage.equals("success")) {
            System.out.println("Sent Message");
        }else {
            System.out.println("Errors...");
        }
        model.addAttribute("msg", true);
        return "redirect:/contact";
    }

    @RequestMapping("/explore")
    public ModelAndView getExplore(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("user");

        if(userId == null) {
            return new ModelAndView("error");
        }

        List<User> allUsersExceptOneUSer = userService.getAllUsers();
        List<User> userList = new ArrayList<>();

        for(User u : allUsersExceptOneUSer) {
            if(u.getUserId().equals(userId)) {
                continue;
            }
            userList.add(u);
        }
        
        model.addAttribute("users", userList);
        return new ModelAndView("explore");
    }

    @RequestMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("login");
    }

    @PostMapping("/handleLogin")
    public String handleLogInProcess(@RequestParam("email") String email, @RequestParam("password") String password,
            Model model, HttpSession session) {
        User u = userService.getUserByEmail(email);
        System.out.println("Email is " + email);
        System.out.println(u.getEmail() + " = " + u.getPassword());
        if (u != null && u.getPassword().equals(password)) {

            session.setAttribute("user", u.getUserId());
            return "redirect:/index";

        }
        return "redirect:/login";
    }

    @RequestMapping("/userDetails/{userId}")
    public ModelAndView getUserDetails(@PathVariable("userId") String userId, Model model) {
        System.out.println("Current User ID is => " + userId );
        User u = userService.getUserById(userId);
        model.addAttribute("user", u);
        return new ModelAndView("userDetails");
    }

    @RequestMapping("/userProfile")
    public ModelAndView getUserProfile(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("user");
        System.out.println("User Profile Page Id " + userId);
        User userById = userService.getUserById(userId);
        model.addAttribute("user", userById);
        return new ModelAndView("userProfile");
    }

    @RequestMapping("/")
    public ModelAndView getCreateAccountPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        ModelAndView mv = new ModelAndView("createAccount");
        return mv;
    }

    @PostMapping("/up")
    public String uploadImg(@ModelAttribute("user") User user, @RequestParam("image") MultipartFile img,
            Model model, HttpSession session) throws IOException {
        String fileName = StringUtils.cleanPath(img.getOriginalFilename());

        UploadImag.uploadImageDir("static/uploadImages", fileName, img);
        user.setImg(fileName);
        User createUser = userService.createUser(user);
        System.out.println(createUser.toString());
        model.addAttribute("user", createUser);

        session.setAttribute("user", createUser.getUserId());
        ud.setUserId(createUser.getUserId());
        System.out.println("user session " + session.getAttribute("user"));
        return "redirect:/index";
    }

    @GetMapping("/logOut")
    public String logOutUser(Model model, HttpSession session) {
        session.removeAttribute("user");
        System.out.println("Log Out Time Session id " + session.getAttribute("user"));
        return "redirect:/login";
    }


}
