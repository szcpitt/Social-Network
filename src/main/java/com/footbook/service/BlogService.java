package com.footbook.service;


import com.footbook.model.Blog;

import java.util.List;

public interface BlogService {
    void save(Blog blog);

    List<String> findBlogContentByUserId(Long userId);

    List<Blog> findByUserId(Long userId);

    Blog findById(Long id);
}
