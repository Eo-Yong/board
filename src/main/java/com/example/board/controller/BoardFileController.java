package com.example.board.controller;

import com.example.board.service.impl.BoardFileService;
import com.example.board.util.UploadFileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/file")
public class BoardFileController {

    @Autowired
    private final BoardFileService boardFileService;

    public BoardFileController(BoardFileService boardFileService) {
        this.boardFileService = boardFileService;
    }

    // 게시글 파일 업로드
    @RequestMapping(value="/upload", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadFile(MultipartFile file, HttpServletRequest request) {
        ResponseEntity<String> entity = null;
        try {
            String saveFilePath = UploadFileUtils.uploadFile(file, request);
            entity = new ResponseEntity<>(saveFilePath, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 게시글 파일 출력
    @RequestMapping(value="/display", method=RequestMethod.GET)
    public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request) throws Exception {
        HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName);
        String rootPath = UploadFileUtils.getRootPath(fileName, request);

        ResponseEntity<byte[]> entity = null;

        // 파일데이터, HttpHeader 전송
        try (InputStream inputStream = new FileInputStream(rootPath + fileName)) {
            entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 게시글 첨부 파일 목록
    @RequestMapping(value="/list/{num}", method=RequestMethod.GET)
    public ResponseEntity<List<String>> getFiles(@PathVariable("num") Integer num) {
        ResponseEntity<List<String>> entity = null;
        try {
            List<String> fileList = boardFileService.getBoardFiles(num);
            entity = new ResponseEntity<>(fileList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 게시글 파일 삭제 : 게시글 작성
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFiles(String fileName, HttpServletRequest request) {
        ResponseEntity<String> entity = null;

        try {
            UploadFileUtils.deleteFile(fileName, request);
            entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 게시글 파일 삭제 : 게시글 수정
    @RequestMapping(value = "/delete/{num}", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(@PathVariable("num") Integer articleNo,
                                             String fileName,
                                             HttpServletRequest request) {
        ResponseEntity<String> entity = null;

        try {
            UploadFileUtils.deleteFile(fileName, request);
            boardFileService.deleteFile(fileName, articleNo);
            entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 게시글 파일 전체 삭제
    @RequestMapping(value="/deleteAll", method=RequestMethod.POST)
    public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files, HttpServletRequest request) {

        if(files == null || files.length == 0) {
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }

        ResponseEntity<String> entity = null;

        try {
            for (String fileName : files) {
                UploadFileUtils.deleteFile(fileName, request);
                entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
