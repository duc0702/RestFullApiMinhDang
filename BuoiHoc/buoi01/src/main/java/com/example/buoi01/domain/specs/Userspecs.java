
 package com.example.buoi01.domain.specs;

import org.springframework.data.jpa.domain.Specification;

import com.example.buoi01.domain.User;

public class Userspecs {
public static Specification<User> nameLike(String name){
    return (root,query,builder)->builder.like(root.get("name"),"%"+name+"%");
}
    
}