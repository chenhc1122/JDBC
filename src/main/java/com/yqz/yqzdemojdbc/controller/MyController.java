package com.yqz.yqzdemojdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@RestController
public class MyController {
    @Autowired
    JdbcTemplate jdbcTemplate;
     @PostMapping ("/userlist")
    public List<Map<String,Object>> allUser(@RequestParam("id") Integer id){
         System.out.println("mysql");
               String sql = "select * from user where id ="+id;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }


     @GetMapping("/adduser")
    public String addUser(){
         String sql = "insert into user (id,username,password) value (4, 'wangzy','wzy123') " ;
         jdbcTemplate.update(sql);
         return "增加成功";
    }


//    Restful风格
    @PostMapping("/userupdata/{username}/{password}/{id}")
    public String updateUser(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("id") int id){
         String sql= "update user set username=?,password=? where id = "+id ;
        System.out.println(sql);
         Object[] objects = new Object[2];
         objects[0] = username;
         objects[1] = password;
         jdbcTemplate.update(sql,objects);
         return "增加成功";
    }
}
