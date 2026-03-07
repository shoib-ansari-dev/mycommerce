package com.example.productservice.controller;

import com.example.productservice.api.ProductsApi;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController implements ProductsApi {

    @Override
    public String getProducts(){
        return "I am returning Product";
    }

    @Override
    public String getProductById(@PathVariable("id") String id){
        return "I am returning id +" + id;
    }

    @Override
    public String saveProduct(@RequestBody String str){
        return  " I am saving save the product";
    }

    @Override
    public String updateProductPut(@RequestBody String str){
        return "I am updating whole object";
    }

    @Override
    public String updateProductPatch(@RequestBody String str){
        return "I am updating via patch not whole object";
    }
}
