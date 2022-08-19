package com.ldm.blog.service;

import com.ldm.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

   //新建的方法
    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag();

    Tag updateTag(Long id, Tag type);

    void deleteTag(Long id);

    Tag getTagByName(String name);
}
