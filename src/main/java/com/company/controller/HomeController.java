package com.company.controller;

import com.test.models.CustomersEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping("/")

    public ModelAndView helloWorld() // gets a webpage using the ModelandView method
    {
        return new // useful for adding one or two things to a page the model is the data
                ModelAndView("welcome", "message", "Hello World");

    }

    @RequestMapping("/test") // usefull for adding several attribriutes to a jsp page
    public String test(Model model) { // string methods are used to show a view
        model.addAttribute("String1", "Hello World1");
        model.addAttribute("String2", "Hello World2");
        model.addAttribute("String3", "Hello World3");
        return "testing";
    }

    @RequestMapping("/listCustomers") // case sensitive
    public ModelAndView listCustomer() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory();
        Session selectCustomers = sessionFact.openSession();
        selectCustomers.beginTransaction();
        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);
        ArrayList<CustomersEntity> customerList = (ArrayList<CustomersEntity>) c.list();
        return new ModelAndView("welcome2", "cList", customerList);
    }
    @RequestMapping("/searchByCity")
    public ModelAndView searchCity(@RequestParam("city") String cityName){
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory();
        Session selectCustomers = sessionFact.openSession();
        selectCustomers.beginTransaction();
        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);
        c.add(Restrictions.like("city", "%" + cityName + "%"));

        ArrayList<CustomersEntity> customerList = (ArrayList<CustomersEntity>) c.list();
        return new ModelAndView("welcome2","cList",customerList);
    }
}