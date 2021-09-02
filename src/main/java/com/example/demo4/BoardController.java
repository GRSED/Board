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
        return "redirect:list?no=0";
    }

    @RequestMapping(value = "list")
    public String list(HttpServletRequest request, @RequestParam("no") int page_no) {
        Pagination pagination = new Pagination(page_no);
        StartEndNo startEndNo = pagination.startEndNo();

        request.setAttribute("no", page_no);
        request.setAttribute("block_no", page_no/Constant.PAGE_COUNT_PER_BLOCK);
        request.setAttribute(Constant.TOTAL_PAGES_NO, pagination.total_pages_no(boardService));
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
        return "redirect:list?no=0";
    }

    @PostMapping(value = "write")
    public String write_post(@ModelAttribute BoardDto boardDto) {
        boardService.insertBoard(boardDto);
        return "redirect:list?no=0";
    }

    @RequestMapping(value = "edit_form")
    public String edit_form(@RequestParam("no") int board_no, HttpServletRequest request) {
        request.setAttribute("boardDetail", boardService.selectByNo(board_no));
        request.setAttribute("page_no", Integer.parseInt(request.getParameter("page_no")));
        return "edit_form";
    }

    @GetMapping(value = "edit")
    public String edit_get() {
        return "redirect:list?no=0";
    }

    @PostMapping(value = "edit")
    public String edit_post(@ModelAttribute BoardDto boardDto, @RequestParam("page_no") String page_no) {
        boardService.updateBoard(boardDto);
        return "redirect:detail?no=" + boardDto.getBoard_no() +"&page_no=" + page_no;
    }

    @GetMapping(value = "delete")
    public String delete_get() {
        return "redirect:list?no=0";
    }

    @PostMapping(value = "delete")
    public String delete_post(@RequestParam("no") int board_no) {
        boardService.deleteBoard(board_no);
        return "redirect:list?no=0";
    }
}
