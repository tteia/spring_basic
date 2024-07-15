package com.beyond.basic.controller;

import com.beyond.basic.domain.PostResDto;
import com.beyond.basic.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/post/list")
    @ResponseBody
    public List<PostResDto> postList(){
        return postService.postList();
    }

    @GetMapping("/post/find/title")
    @ResponseBody
    public List<PostResDto> titleList(@RequestBody String title){
        return postService.titlePostList(title);
    }
}
