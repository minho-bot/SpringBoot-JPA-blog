package com.minho.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    private static final String Tag = "HttpControllerTest";

    @GetMapping("/http/get")
    public String getTest(Member m){
        System.out.println(Tag + "getter : " + m.getId());
        m.setId(5555);
        System.out.println(Tag + "getter : " + m.getId());
        return "get 요청 : "+", "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    @PostMapping("http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청 : "+", "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    @PutMapping("http/put")
    public String putTest(){
        return "put 요청";
    }

    @DeleteMapping("http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
