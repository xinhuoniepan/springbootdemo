package com.example.springbootdemo.web;

import com.example.springbootdemo.bean.DocumentRecord;
import com.example.springbootdemo.service.DocumentRecordService;
import com.example.springbootdemo.util.CommonRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documentRecord")
public class DocumentRecordController {
    @Autowired
    private DocumentRecordService documentRecordService;

    @RequestMapping("/selectAll")
    public List<DocumentRecord> getDocumentRecordAll(){
        List<DocumentRecord> list = documentRecordService.selectAll();
        return list;
    }


}
