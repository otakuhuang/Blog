package com.jamhuang.blog.service.impl;

import com.jamhuang.blog.NotFoundException;
import com.jamhuang.blog.dao.BlogRepository;
import com.jamhuang.blog.entity.Blog;
import com.jamhuang.blog.entity.Type;
import com.jamhuang.blog.service.BlogService;
import com.jamhuang.blog.utils.MarkdownUtils;
import com.jamhuang.blog.utils.MyBeanUtils;
import com.jamhuang.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getOne(id);
        if (Objects.equals(blog, null)) {
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {

        return blogRepository.findAll((Specification<Blog>) (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
            }
            if (blog.getTypeId()!=null){
                predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
            }
            if (blog.isRecommend()){
                predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll((Specification<Blog>) (root, cq, cb) -> {
            Join join = root.join("tags");
            return cb.equal(join.get("id"), tagId);
        }, pageable);
    }

    @Override
    public Page<Blog> listSearchBlog(String searchContent, Pageable pageable) {
        return blogRepository.findBySearchContent(searchContent, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {

        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findRecommendTop(pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    @Override
    public Long countBlogByTypeId(Long typeId) {
        return blogRepository.countBlogByTypeId(typeId);
    }

    @Override
    public Long countBlogByTagId(Long tagId) {
        return blogRepository.count(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"), tagId);
            }
        });
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
