package com.footbook.service;


import com.footbook.model.Blog;

import java.util.List;

public interface BlogService {
    void save(Blog blog);

    List<String> findBlogContentByUser_id(Long user_id);
}
