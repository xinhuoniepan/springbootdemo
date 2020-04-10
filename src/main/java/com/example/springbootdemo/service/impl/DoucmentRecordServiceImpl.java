package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.DocumentRecord;
import com.example.springbootdemo.dao.DocumentRecordMapper;
import com.example.springbootdemo.service.DocumentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoucmentRecordServiceImpl implements DocumentRecordService {

    @Autowired
    private DocumentRecordMapper documentRecordMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(DocumentRecord record) {
        return 0;
    }

    @Override
    public int insertSelective(DocumentRecord record) {
        return 0;
    }

    @Override
    public DocumentRecord selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(DocumentRecord record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DocumentRecord record) {
        return 0;
    }

    @Override
    public List<DocumentRecord> selectAll() {
        return null;
    }
}
