package com.jamhuang.blog.service.impl;

import com.jamhuang.blog.NotFoundException;
import com.jamhuang.blog.dao.TagRepository;
import com.jamhuang.blog.entity.Tag;
import com.jamhuang.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        if (tag.getId()==null){
            tag.setCreateTime(new Date());
            tag.setUpdateTime(new Date());
        } else {
            tag.setUpdateTime(new Date());
        }
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTagById(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.getOneByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {

        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (String id : idarray) {
                if (isNumber(id)){
                    list.add(Long.parseLong(id));
                } else {
                    Tag t = new Tag();
                    t.setName(id);
                    Tag t2 = this.saveTag(t);
                    list.add(t2.getId());
                }
            }
        }
        return list;
    }

    private boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+(\\\\.[0-9]+)?");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()){
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该分类");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
