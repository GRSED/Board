package com.example.demo4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "list")
    public String list(HttpServletRequest request) {
        StartEndNo startEndNo;
        if (request.getParameter("no") == null) {
            startEndNo = new StartEndNo(1, Constant.ARTICLE_COUNT_PER_PAGE);
            //startEndNo.put(Constant.START_NO, 1);
            //startEndNo.put(Constant.END_NO, Constant.ARTICLE_COUNT_PER_PAGE);
            request.setAttribute("no", 0);
            request.setAttribute("block_no", 0);
        } else {
            startEndNo = new StartEndNo((Integer.parseInt(request.getParameter("no"))) * Constant.ARTICLE_COUNT_PER_PAGE + 1, (Integer.parseInt(request.getParameter("no")) + 1) * Constant.ARTICLE_COUNT_PER_PAGE);
            //startEndNo.put(Constant.START_NO, (Integer.parseInt(request.getParameter("no"))) * Constant.ARTICLE_COUNT_PER_PAGE + 1);
            //startEndNo.put(Constant.END_NO, (Integer.parseInt(request.getParameter("no")) + 1) * Constant.ARTICLE_COUNT_PER_PAGE);
            request.setAttribute("no", request.getParameter("no"));
            request.setAttribute("block_no", Integer.parseInt(request.getParameter("no"))/Constant.PAGE_COUNT_PER_BLOCK);
        }

        if (boardService.selectAll().size()%Constant.ARTICLE_COUNT_PER_PAGE == 0) {
            request.setAttribute(Constant.TOTAL_PAGES_NO, boardService.selectAll().size()/Constant.ARTICLE_COUNT_PER_PAGE);
        } else {
            request.setAttribute(Constant.TOTAL_PAGES_NO, boardService.selectAll().size()/Constant.ARTICLE_COUNT_PER_PAGE + 1);
        }

        request.setAttribute("boardList", boardService.selectForPaging(startEndNo));
        request.setAttribute("page_count_per_block", Constant.PAGE_COUNT_PER_BLOCK);
        return "list";
    }

    @RequestMapping(value = "detail")
    public String detail(HttpServletRequest request, @RequestParam("no") int board_no ) {
        request.setAttribute("boardDetail", boardService.selectByNo(board_no));
        request.setAttribute("page_no", Integer.parseInt(request.getParameter("page_no")));
        return "detail";
    }

    @RequestMapping(value = "write_form")
    public String write_form() {
        return "write_form";
    }

    @GetMapping(value = "write")
    public String write_get() {
        return "redirect:list";
    }

    @PostMapping(value = "write")
    public String write_post(@ModelAttribute BoardDto boardDto) {
        boardService.insertBoard(boardDto);
        return "redirect:list";
    }

    @RequestMapping(value = "edit_form")
    public String edit_form(@RequestParam("no") int board_no, HttpServletRequest request) {
        request.setAttribute("boardDetail", boardService.selectByNo(board_no));
        request.setAttribute("page_no", Integer.parseInt(request.getParameter("page_no")));
        return "edit_form";
    }

    @GetMapping(value = "edit")
    public String edit_get() {
        return "redirect:list";
    }

    @PostMapping(value = "edit")
    public String edit_post(@ModelAttribute BoardDto boardDto, @RequestParam("page_no") String page_no) {
        boardService.updateBoard(boardDto);
        return "redirect:detail?no=" + boardDto.getBoard_no() +"&page_no=" + page_no;
    }

    @GetMapping(value = "delete")
    public String delete_get() {
        return "redirect:list";
    }

    @PostMapping(value = "delete")
    public String delete_post(@RequestParam("no") int board_no) {
        boardService.deleteBoard(board_no);
        return "redirect:list";
    }
}
