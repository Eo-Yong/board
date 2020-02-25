package com.example.board.service.impl;

import com.example.board.mapper.BoardMapper;
import com.example.board.mapper.FileMapper;
import com.example.board.vo.BoardVO;
import com.example.board.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {

    private static Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public int loginCheck(UserVO userVO, HttpSession session) throws Exception {
        int result = mapper.loginCheck(userVO);
        if(result == 1) {
            UserVO vo2 = viewMember(userVO);
            // 세션 변수 등록
            session.setAttribute("userId", vo2.getUserId());
            session.setAttribute("userName", vo2.getUserName());
            session.setAttribute("userType", vo2.getUserType());
        }
        return result;
    }

    @Override
    public UserVO viewMember(UserVO userVO) throws Exception {
        return mapper.viewMember(userVO);
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @Override
    @Transactional
    public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception {
        return mapper.selectBoardList(boardVO);
    }

    @Override
    @Transactional
    public BoardVO selectBoardDetail(BoardVO boardVO) throws Exception {
        return mapper.selectBoardDetail(boardVO);
    }

    @Override
    public Boolean addCnt(int num) throws Exception {
        return mapper.addCnt(num);
    }

    @Transactional
    @Override
    public void insertBoard(BoardVO boardVO) throws Exception {
        // 게시글 입력처리
        mapper.insertBoard(boardVO);
        String[] files = boardVO.getFiles();

        if(files == null) {
            return;
        }

        // 게시글 첨부파일 입력처리
        for (String fileName : files) {
            fileMapper.addFile(fileName);
        }

    }

    @Override
    public void updateBoard(BoardVO boardVO) throws Exception {
        mapper.updateBoard(boardVO);
    }

    @Override
    public int deleteBoard(int num) throws Exception {
        return mapper.deleteBoard(num);
    }

    @Override
    public List<BoardVO> selectBoardTypeList() throws Exception {
        return mapper.selectBoardTypeList();
    }

    @Override
    public void updateUseYnN(BoardVO boardVO) throws Exception {
        mapper.updateUseYnN(boardVO);
    }

    @Override
    public void updateUseYnY(BoardVO boardVO) throws Exception {
        mapper.updateUseYnY(boardVO);
    }

    @Override
    public List<BoardVO> selectBoardType() throws Exception {
        return mapper.selectBoardType();
    }
}
