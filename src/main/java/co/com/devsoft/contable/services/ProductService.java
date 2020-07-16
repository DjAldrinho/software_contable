package co.com.devsoft.contable.services;

import co.com.devsoft.contable.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService{

    @Autowired
    private ProductRepository productRepository;

    /** Buscar producto por ID */
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    /** Insertar producto */
    public Product save(Product entity){
        entity = productRepository.save(entity);
        return entity;
    }

    public List<Product> getProductList(){
        return (List<Product>) productRepository.findAll();
    }
}
