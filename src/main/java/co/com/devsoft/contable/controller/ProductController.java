package co.com.devsoft.contable.controller;

import co.com.devsoft.contable.models.Product;
import co.com.devsoft.contable.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @ResponseBody
    public String saveProducts(){
        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Producto 1", 'A'));
        productList.add(new Product("Producto 2", 'A'));
        productList.add(new Product("Producto 3", 'A'));
        productList.add(new Product("Producto 4", 'A'));
        try {
            for (Product product: productList) {
                productService.save(product);
            }
            return "Productos ingresados con exito!";
        } catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

}
