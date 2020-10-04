package com.jamhuang.blog.dao;

import com.jamhuang.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findRecommendTop(Pageable pageable);

    @Query("select b from Blog b where b.title like lower(?1) or b.content like lower(?1)")
    Page<Blog> findBySearchContent(String searchContent, Pageable pageable);
}
