package com.example.buoi01.Controller.admin;

import com.example.buoi01.domain.Product;
import com.example.buoi01.domain.User;
import com.example.buoi01.service.ProductService;
import com.example.buoi01.service.utils.error.messageCustomExcetion;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
@AllArgsConstructor
public class ProductController {
    @Autowired
 private  final   ProductService productService;
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> productList= productService.getAllProduct();
        return ResponseEntity.ok().body(productList);
    }
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
     Product savePro =  productService.saveProduct(product);
        return ResponseEntity.created(null).body(savePro);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Xóa thành công");
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable long id) throws messageCustomExcetion{
        Product detailProduct= productService.getOneProduct(id);

        if (detailProduct==null){
            throw new messageCustomExcetion("Không tìm thấy product");
        }
        Product updatePro = productService.updateProduct(product,id);
        return ResponseEntity.ok().body(updatePro);
    }

}
