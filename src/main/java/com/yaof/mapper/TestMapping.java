package com.yaof.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.yaof.pojo.Student;

@Repository
@Mapper
public interface TestMapping {

    @Cacheable(value = "common", key = "'getTestString'")
    int getCount();

    @Cacheable(value = "default", key = "'getAllStudent'")
    List<Object> getAllStudent();

    @CacheEvict(value = "default", key = "'getAllStudent'")
    void insertStudent(Student student);
}
