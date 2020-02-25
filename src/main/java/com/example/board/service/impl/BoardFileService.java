package com.example.board.service.impl;

import java.util.List;

public interface BoardFileService {

    // 파일 목록
    List<String> getBoardFiles(Integer num) throws Exception;

    // 파일 삭제
    void deleteFile(String fileName, Integer num) throws Exception;
}
