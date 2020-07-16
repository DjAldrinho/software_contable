package co.com.devsoft.contable.utils;

import co.com.devsoft.contable.models.Product;
import co.com.devsoft.contable.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ProductConverter implements Converter<String, Product> {

    @Autowired
    private ProductService productService;

    public Product convert(String id){
        Optional<Product> product = productService.findById(Long.parseLong(id));
        return product.orElse(null);
    }
}
