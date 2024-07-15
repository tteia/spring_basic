package com.beyond.basic.service;

import com.beyond.basic.domain.Post;
import com.beyond.basic.domain.PostResDto;
import com.beyond.basic.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    public List<PostResDto> postList(){
        List<Post> postList = postRepository.findAll();
        List<PostResDto> postResDtoList = new ArrayList<>();
        for (Post post : postList) {
            postResDtoList.add(post.fromEntity());
            System.out.println("저자의 이메일은 : " + post.getMember().getEmail());
        }

        return postResDtoList;
    }

    public List<PostResDto> titlePostList(String title) {
        List<Post> postList = postRepository.findByTitle(title);
        List<PostResDto> titleList = new ArrayList<>();
        for (Post post : postList) {
//            PostResDto dto = new PostResDto();
//            dto.setTitle(post.getTitle());
//            dto.setId(post.getId());
//            titleList.add(dto);
            titleList.add(post.fromEntity());
        }
        return titleList;
    }
}
