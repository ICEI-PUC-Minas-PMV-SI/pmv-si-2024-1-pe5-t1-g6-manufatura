package com.ecocleaning.webstore.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/produtos")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products/index";
    }

    @GetMapping("/produto/{id}")
    public String productId(Model model, @PathVariable String id ) {
        final var productOpt =  productRepository.findById(id);
        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            return "product/index";
        }
        return "product/not-found";
    }

    @GetMapping("/produto")
    public String product(Model model) {
        return "product/index";
    }

    @PutMapping(path="/produtos/{id}", consumes="application/x-www-form-urlencoded")
    public String update(Model model, ProductDto productDto) {
        try {
            final var product = productDto.toProduct();
            this.productRepository.save(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "product/error";
        }
        model.addAttribute("text", "Produto atualizado com sucesso!");
        return "product/success";
    }

    @PostMapping(path="/produtos", consumes="application/x-www-form-urlencoded")
    public String save(Model model, ProductDto productDto) {
        try {
            final var product = productDto.toProduct();
            this.productRepository.save(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "product/error";
        }
        model.addAttribute("text", "Produto salvo com sucesso!");
        return "product/success";
    }

    @DeleteMapping("/produtos/{id}")
    public String delete(@PathVariable String id) {
        System.out.println("Product with " + id + " deleted");
        productRepository.deleteById(id);

        return "/products/delete-success";
    }




}
