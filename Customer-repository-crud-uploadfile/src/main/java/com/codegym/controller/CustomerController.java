package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICustomerService customerService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/customers/list")
    public ModelAndView showListCustomers() {
        ModelAndView modelAndView = new ModelAndView("customer/list");
        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }


    @GetMapping("/customers/create")
    private ModelAndView ShowCreateCustomer() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new CustomerForm());
        return modelAndView;
    }

    @PostMapping("/customers/create")
    private ModelAndView createCustomer(@ModelAttribute CustomerForm customerForm) {
        String fileName = customerForm.getImage().getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;

        try {
            FileCopyUtils.copy(customerForm.getImage().getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getAge(), fileName);
        customerService.save(customer);
        return new ModelAndView("redirect:/customers/list");
    }

    @GetMapping("/customers/delete/{id}")
    public ModelAndView showDeleteCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ModelAndView("error-404");
        }
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        modelAndView.addObject("customer", customerOptional.get());
        return modelAndView;
    }

    @PostMapping("/customers/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            File file = new File(uploadPath + customerOptional.get().getImage());
            if (file.exists()) {
                file.delete();
            }
            customerService.removeById(id);
            return new ModelAndView("redirect:/customers/list");
        }
        return new ModelAndView("error-404");
    }

    @GetMapping("/customers/edit/{id}")
    public ModelAndView showEditCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ModelAndView("error-404");
        }
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customerOptional.get());
        return modelAndView;
    }

    @PostMapping("/customers/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Long id, @ModelAttribute CustomerForm customerForm) {
        MultipartFile img = customerForm.getImage();
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            Customer oldCustomer = customerOptional.get();
            if (img.getSize() != 0) {
                String filename = customerForm.getImage().getOriginalFilename();
                Long currentTime = System.currentTimeMillis();
                filename = currentTime + filename;
                oldCustomer.setImage(filename);
                try {
                    FileCopyUtils.copy(img.getBytes(),new File(uploadPath + filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            oldCustomer.setName(customerForm.getName());
            oldCustomer.setAge(customerForm.getAge());
            oldCustomer.setProvince(customerForm.getProvince());
            customerService.save(oldCustomer);
            return new ModelAndView("redirect:/customers/list");
        }
        return new ModelAndView("error-404");
    }

    @GetMapping("customers/{id}")
    public ModelAndView showCustomerDetails(@PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if (!customerOptional.isPresent()){
            return  new ModelAndView("error-404");
        }
        ModelAndView modelAndView = new ModelAndView("customer/view");
        modelAndView.addObject("customer",customerOptional.get());
        return modelAndView;
    }


}


