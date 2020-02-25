package com.example.board.service.impl;

import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.FileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardServiceImpl")
public class BoardFileServiceImpl implements BoardFileService {

    private static Logger logger = LoggerFactory.getLogger(BoardFileServiceImpl.class);

    @Autowired
    private final FileMapper mapper;

    @Autowired
    public BoardFileServiceImpl(FileMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<String> getBoardFiles(Integer num) throws Exception {
        return mapper.getBoardFiles(num);
    }

    @Override
    public void deleteFile(String fileName, Integer num) throws Exception {
        mapper.deleteFile(fileName);
        mapper.updateFileCnt(num);
    }
}
