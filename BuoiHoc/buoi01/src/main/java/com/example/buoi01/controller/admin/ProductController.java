package com.example.buoi01.controller.admin;

import com.example.buoi01.domain.Product;
import com.example.buoi01.domain.dto.res.FillterProductDTO;
import com.example.buoi01.service.ProductService;
import com.example.buoi01.service.utils.error.messageCustomExcetion;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
@AllArgsConstructor
public class ProductController {
    @Autowired
 private  final   ProductService productService;
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> productList= productService.getAllUser(Product.class) ;
        return ResponseEntity.ok().body(productList);
    }
    @GetMapping("{id}")
    public ResponseEntity<Product> getOneproduct(@PathVariable long id){
        Product product= productService.getUserById(id,Product.class).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
        return ResponseEntity.ok(product);
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
        Product detailProduct= productService.getUserById(id,Product.class).get();

        if (detailProduct==null){
            throw new messageCustomExcetion("Không tìm thấy product");
        }
        Product updatePro = productService.updateProduct(product,id);
        return ResponseEntity.ok().body(updatePro);
    }
    @GetMapping("/page/sort")
    public ResponseEntity<List<Product>> getAllProductPageAndSort(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String sortBy, @RequestParam("order") String sortType){
        List<Product> productList= productService.findAllByWithPageable(page,size,sortBy,sortType).getContent();
        return ResponseEntity.ok().body(productList);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchByBetweenPrice(@RequestParam("maxPrice") int maxPrice,@RequestParam("minPrice") int minPrice){
        if (maxPrice <0||minPrice<0||minPrice>maxPrice) {
            return ResponseEntity.badRequest().body("Giá nhập sai");
        }
        List<Product> productList= productService.searchByBetweenPrice(maxPrice,minPrice);
        return ResponseEntity.ok().body(productList);
    }
    @GetMapping("/searchByMany")
    public ResponseEntity<?> searchByMany(Pageable pageable ,FillterProductDTO fillterProductDTO){
         // Kiểm tra null hoặc rỗng trước khi parse
         if (fillterProductDTO.getPriceMax() == null || fillterProductDTO.getPriceMin() == null ||
         fillterProductDTO.getPriceMax().isEmpty() || fillterProductDTO.getPriceMin().isEmpty()) {
         return ResponseEntity.badRequest().body("Giá không được để trống");
     }

     // Chuyển đổi giá trị từ String sang int
     int maxPrice = Integer.parseInt(fillterProductDTO.getPriceMax());




     
     int minPrice = Integer.parseInt(fillterProductDTO.getPriceMin());

        if (maxPrice <0||minPrice<0||minPrice>maxPrice) {
            return ResponseEntity.badRequest().body("Giá nhập sai");
        }
        Page<Product> productList= productService.findAllByNameBetweenPriceAndPage(pageable,fillterProductDTO);
        return ResponseEntity.ok().body(productList.getContent());
    }
    

}
