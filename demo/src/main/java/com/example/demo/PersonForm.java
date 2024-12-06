package com.example.demo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PersonForm {
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Min(18)
    private Integer age;

    public String getName(){
        return this.name;
    }

    public void setName(String  name){
        this.name = name;
    }

    public Integer getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}
