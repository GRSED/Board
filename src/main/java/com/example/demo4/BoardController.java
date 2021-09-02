package com.example.demo4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "main")
    public String main() {
        return "redirect:list?no=0";
    }

    @RequestMapping(value = "list")
    public String list(Model model, @RequestParam("no") int page_no) {
        Pagination pagination = new Pagination(page_no);

        model.addAttribute("no", page_no);
        model.addAttribute("block_no", pagination.block_no());
        model.addAttribute(Constant.TOTAL_PAGES_NO, pagination.total_pages_no(boardService));
        model.addAttribute("boardList", boardService.selectForPaging(pagination.startEndNo()));
        model.addAttribute("page_count_per_block", Constant.PAGE_COUNT_PER_BLOCK);

        return "list";
    }

    @RequestMapping(value = "detail")
    public String detail(Model model, @RequestParam("no") int board_no, @RequestParam("page_no") int page_no ) {
        model.addAttribute("boardDetail", boardService.selectByNo(board_no));
        model.addAttribute("page_no", page_no);
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
    public String edit_form(@RequestParam("no") int board_no, @RequestParam("page_no") int page_no,  Model model) {
        model.addAttribute("boardDetail", boardService.selectByNo(board_no));
        model.addAttribute("page_no", page_no);
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
