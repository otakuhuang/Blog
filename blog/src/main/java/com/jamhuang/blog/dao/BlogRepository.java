package com.jamhuang.blog.dao;

import com.jamhuang.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findRecommendTop(Pageable pageable);

    @Query("select b from Blog b where b.title like lower(?1) or b.content like lower(?1)")
    Page<Blog> findBySearchContent(String searchContent, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views + 1 where b.id = ?1")
    Integer updateViews(Long id);

    @Query("select function('date_format',b.createTime,'%Y') as year from Blog b group by function('date_format',b.createTime,'%Y') order by year DESC")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.createTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    @Query("select count(b) from Blog b where b.type.id = ?1")
    Long countBlogByTypeId(Long typeId);
}
