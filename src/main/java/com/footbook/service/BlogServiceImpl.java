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
    public List<String> findBlogContentByUserId(Long userId){
        return blogRepository.findBlogContentByUserId(userId);
    }

    @Override
    public List<Blog> findByUserId(Long userId){
        return blogRepository.findByUserId(userId);
    }

    @Override
    public Blog findById(Long id){
        return blogRepository.findById(id);
    }
}
