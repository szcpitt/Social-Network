package com.footbook.service;

import com.footbook.model.Blog;
import com.footbook.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public void save(Blog blog){
        blogRepository.save(blog);
    }

    @Override
    public List<String> findBlogContentByUser_id(Long user_id){
        return blogRepository.findBlogContentByUser_id(user_id);
    }

}
