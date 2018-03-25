package com.footbook.repository;


import com.footbook.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("select b.content from Blog b where b.user_id= ?1")
    List<String> findBlogContentByUser_id(Long user_id);
}
