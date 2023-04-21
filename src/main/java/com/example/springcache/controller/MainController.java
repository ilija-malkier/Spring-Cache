package com.example.springcache.controller;

import com.example.springcache.model.Property;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class MainController {

    private Property property=new Property("test",10.0);

    @GetMapping("/{id}")
    @Cacheable(value = "property",key = "#id")
    public Property getProperty(@PathVariable("id") String id){
        System.out.println("Fetching");

        return property;
    }

    @GetMapping("/all")
    @CachePut(value = "propertyList")
    public  List<Property> getList(){
        System.out.println("List");
        if(property==null) return List.of();
        return List.of(property);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "property",key = "#id")
//    @CacheEvict(value = "propertyList",key = "'2'")
    public  Property deleteProperty(@PathVariable("id") String id){
        System.out.println("Deleter");

        property=null;
        return property;
    }

    @PostMapping
    public Property createProperty(){
        System.out.println("Creating");
        property=new Property("test",10.0);
        return  property;
    }

    @PutMapping("/{id}")
    @CachePut(value = "property",key = "#id")
    public Property updateProperty(@PathVariable("id") String id){
        System.out.println("Update");

        property.setName("updated");
        property.setPrice(100.0);
        return property;
    }
}
