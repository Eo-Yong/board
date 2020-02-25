package com.example.board.service.impl;

import com.example.board.vo.BoardVO;
import com.example.board.vo.UserVO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {
    int loginCheck(UserVO userVO, HttpSession session) throws Exception;

    void logout(HttpSession session);

    UserVO viewMember(UserVO userVO) throws Exception;

    List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception;

    BoardVO selectBoardDetail(BoardVO boardVO) throws Exception;

    Boolean addCnt(int num) throws Exception;

    void insertBoard(BoardVO boardVO) throws Exception;

    void updateBoard(BoardVO boardVO) throws Exception;

    int deleteBoard(int num) throws Exception;

    List<BoardVO> selectBoardTypeList() throws Exception;

    void updateUseYnN(BoardVO boardVO) throws Exception;

    void updateUseYnY(BoardVO boardVO) throws Exception;

    List<BoardVO> selectBoardType() throws Exception;
}
