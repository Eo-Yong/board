package com.example.board.mapper;

import java.util.List;

public interface FileMapper {

    // 파일 추가
    void addFile(String fullName) throws Exception;

    // 파일 목록
    List<String> getBoardFiles(Integer num) throws Exception;

    // 파일 삭제
    void deleteFile(String fileName) throws Exception;

    // 파일 전체 삭제
    void deleteAllFiles(Integer num) throws Exception;

    // 파일 수정
    void replaceFile(Integer fileNo, Integer num) throws Exception;

    // 파일 개수 갱신
    void updateFileCnt(Integer num) throws Exception;
}
