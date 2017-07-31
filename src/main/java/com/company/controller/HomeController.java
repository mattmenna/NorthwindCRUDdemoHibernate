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
        ArrayList<CustomersEntity> customerList = getAllCustomers();
        return new ModelAndView("welcome2", "cList", customerList);
    }

    //this method was extracted to be used again
    //this is a regular method not controller method
    private ArrayList<CustomersEntity> getAllCustomers() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory();
        Session selectCustomers = sessionFact.openSession();
        selectCustomers.beginTransaction();
        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);
        return (ArrayList<CustomersEntity>) c.list();
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
    @RequestMapping("/delete")
    public ModelAndView deleteCustomer(@RequestParam("id") String id){
        // temp object will store info for object we want to delete
        CustomersEntity temp = new CustomersEntity();
        temp.setCustomerId(id);

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory();
        Session customers = sessionFact.openSession();
        customers.beginTransaction();
        customers.delete(temp);
        customers.getTransaction().commit(); // method that actually deletes the row

        ArrayList<CustomersEntity> customerList = getAllCustomers();

        return new ModelAndView("welcome2","cList",customerList);
    }

    @RequestMapping("/getNewCust")
    public String newCustomer(){
        return "addcustomerform"; //inclass example used addcustomerform for jsp page

    }
    @RequestMapping("/addNewCustomer")
    public String addNewCustomer(@RequestParam("custID") String custID,
                                 @RequestParam("compName") String compName,
                                 @RequestParam("contName") String contName,
                                 @RequestParam("contTitle") String contTitle){
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory();
        Session session = sessionFact.openSession();
        Transaction tx = session.beginTransaction();
        CustomersEntity newCustomer = new CustomersEntity();

        newCustomer.setCustomerId(custID);
        newCustomer.setCompanyName(compName);
        newCustomer.setContactName(contName);
        newCustomer.setContactTitle(contTitle);
        session.save(newCustomer);
        tx.commit();
        session.close();

        return "addcustomersuccess";

    }

}