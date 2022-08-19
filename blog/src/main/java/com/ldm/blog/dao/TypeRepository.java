package com.ldm.blog.dao;

import com.ldm.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);


    //首页分类的条数
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}

