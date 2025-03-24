package com.example.buoi01.domain.specs;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec;
import org.springframework.data.jpa.domain.Specification;

import com.example.buoi01.domain.Product;
import com.example.buoi01.domain.User;

import lombok.Data;
@Data
public class ProductSpecs {
  public static Specification<Product> priceBetween (int max,int min){
      return (root,query,builder)->builder.between(root.get("price"),max,min);
   
}
public static Specification<Product> nameLike(String name){
    return (root,query,builder)->builder.like(root.get("name"),"%"+name+"%");
}
}
