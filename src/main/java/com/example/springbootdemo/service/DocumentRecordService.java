package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.DocumentRecord;

import java.util.List;

public interface DocumentRecordService {
    int deleteByPrimaryKey(Integer id);

    int insert(DocumentRecord record);

    int insertSelective(DocumentRecord record);

    DocumentRecord selectByPrimaryKey(Integer id);

    List<DocumentRecord> selectAll();

    int updateByPrimaryKeySelective(DocumentRecord record);

    int updateByPrimaryKey(DocumentRecord record);
}
