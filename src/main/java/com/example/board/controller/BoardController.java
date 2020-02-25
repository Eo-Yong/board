package com.example.board.controller;

import com.example.board.service.impl.BoardService;
import com.example.board.vo.BoardVO;
import com.example.board.vo.UserVO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @RequestMapping(value="/")
    public String login() {
        return "login";
    }

    @RequestMapping(value="/loginCheck.do")
    public ModelAndView doLoginCheck(@ModelAttribute UserVO userVO, BoardVO boardVO, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        try {
            int result = service.loginCheck(userVO, session);

            if(result == 1) {
                String userName = (String) request.getSession().getAttribute("userName");
                String userId = (String) request.getSession().getAttribute("userId");
                String userType = (String) request.getSession().getAttribute("userType");

                boardVO.setUserId(userId);
                boardVO.setUserType(userType);

                List<BoardVO> list = service.selectBoardList(boardVO);

                mv.setViewName("boardList");
                mv.addObject("list", list);
                mv.addObject("userName", userName);
                mv.addObject("userId", userId);
                mv.addObject("userType", userType);
            } else if(result == 0) {
                mv.setViewName("login");
                mv.addObject("msg", "failure");
            }
        } catch(NullPointerException e) {
            /*mv.setViewName("login");
            mv.addObject("msg", "failure");*/
        } finally {

        }
        return mv;
    }

    @RequestMapping("/logout.do")
    public String doLogout(HttpSession session) throws Exception {
        service.logout(session);
        return "login";
    }

    @RequestMapping(value="/boardList.do")
    public ModelAndView selectBoardList(HttpServletRequest request, HttpServletResponse response, Model model, BoardVO boardVO) throws Exception {

        String userId = (String) request.getSession().getAttribute("userId");
        String userType = (String) request.getSession().getAttribute("userType");

        boardVO.setUserId(userId);
        boardVO.setUserType(userType);
        List<BoardVO> list = service.selectBoardList(boardVO);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("boardList");
        mv.addObject("list", list);
        return mv;
    }

    @RequestMapping(value="/boardDetail.do")
    public ModelAndView selectBoardDetail(HttpServletRequest request, HttpServletResponse response, Model model, BoardVO boardVO) throws Exception {

        int num = Integer.parseInt(request.getParameter("num"));
        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        String userType = (String) request.getSession().getAttribute("userType");
        boardVO.setNum(num);
        service.addCnt(num);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("boardDetail");
        mv.addObject("detail", service.selectBoardDetail(boardVO));
        mv.addObject("userName", userName);
        mv.addObject("userId", userId);
        mv.addObject("userType", userType);

        //System.out.println(num);
        return mv;
    }

    @RequestMapping(value="/boardInsert.do", method=RequestMethod.GET)
    public String goInsertBoard(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO, Model model) throws Exception {

        List<BoardVO> list = service.selectBoardType();

        String userId = (String) request.getSession().getAttribute("userId");
        String userName = (String) request.getSession().getAttribute("userName");
        String userType = (String) request.getSession().getAttribute("userType");

        model.addAttribute("list", list);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("userType", userType);

        return "boardInsert";
    }

    @RequestMapping(value="/doInsert.do", method=RequestMethod.POST)
    public String insertBoard(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BoardVO boardVO, Model model) throws Exception {

        if(boardVO.getBoardType() != null) {
            if(boardVO.getBoardType().equals("nor")) {
                boardVO.setBoardTypeNm("일반게시판");
            } else if(boardVO.getBoardType().equals("notice")) {
                boardVO.setBoardTypeNm("공지게시판");
            } else if(boardVO.getBoardType().equals("gallery")) {
                boardVO.setBoardTypeNm("갤러리게시판");
            } else if(boardVO.getBoardType().equals("video")) {
                boardVO.setBoardTypeNm("동영상게시판");
            }
        }

        service.insertBoard(boardVO);
        return "redirect:/boardList.do";
    }

    @RequestMapping(value="/doUpdate.do", method=RequestMethod.POST)
    public String updateBoard(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BoardVO boardVO, Model model) throws Exception {
        service.updateBoard(boardVO);
        model.addAttribute("msg", "success");

        return "redirect:/boardList.do";
    }

    @RequestMapping(value="/doDelete.do", method=RequestMethod.GET)
    public String deleteBoard(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="num") int num, Model model) throws Exception {

        service.deleteBoard(num);
        model.addAttribute("msg", "success");
        return "redirect:/boardList.do";
    }

    @RequestMapping(value="/boardManagement.do")
    public String goBoardManagement(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String userId = (String) request.getSession().getAttribute("userId");
        String userName = (String) request.getSession().getAttribute("userName");
        String userType = (String) request.getSession().getAttribute("userType");

        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("userType", userType);

        List<BoardVO> list = service.selectBoardTypeList();

        model.addAttribute("list", list);

        return "boardManagement";
    }

    @RequestMapping(value="/changeUseYnN.do")
    @ResponseBody
    public void changeUseYnN(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO) throws Exception {

        service.updateUseYnN(boardVO);
        List<BoardVO> result = service.selectBoardTypeList();

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(new Gson().toJson(result).toString());
        out.flush();
    }

    @RequestMapping(value="/changeUseYnY.do")
    @ResponseBody
    public void changeUseYnY(HttpServletRequest request, HttpServletResponse response, BoardVO boardVO) throws Exception {

        service.updateUseYnY(boardVO);
        List<BoardVO> result = service.selectBoardTypeList();

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(new Gson().toJson(result).toString());
        out.flush();
    }
}
