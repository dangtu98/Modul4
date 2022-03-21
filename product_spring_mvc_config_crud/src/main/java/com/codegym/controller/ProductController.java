package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProductController {

    @GetMapping("/products")
    public String showProductList(ModelMap modelMap){
        modelMap.addAttribute("products", ProductService.products);
        return "show";
    }

    @GetMapping("/createProduct")
    public ModelAndView createProductList(){
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }

    @PostMapping("createProduct")
    public void save(@ModelAttribute Product product , HttpServletRequest request , HttpServletResponse response) throws IOException {
        ProductService.save(product);
        response.sendRedirect("products");
    }

    @GetMapping("/edits/{id}")
    public ModelAndView editProductList(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product",ProductService.getProduct(id));
        return modelAndView;
    }

    @PostMapping("edits/{id}")
    public ModelAndView editProduct(@ModelAttribute Product product , @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        ProductService.edit(ProductService.findIndexById(id),product);
        return modelAndView;
    }
}
