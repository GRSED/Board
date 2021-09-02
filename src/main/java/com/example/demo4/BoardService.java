package com.example.demo4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService{
    @Autowired
    private BoardMapper boardMapper;


    public int insertBoard(BoardDto boardDto) {
        return boardMapper.insertBoard(boardDto);
    }

    public int deleteBoard(int board_no) {
        return boardMapper.deleteBoard(board_no);
    }


    public int updateBoard(BoardDto boardDto) {
        return boardMapper.updateBoard(boardDto);
    }

    public List<BoardDto> selectAll() {
        return boardMapper.selectAll();
    }

    public BoardDto selectByNo(int board_no) {
        return boardMapper.selectByNo(board_no);
    }

    public List<BoardDto> selectForPaging(StartEndNo startEndNo) {
        return boardMapper.selectForPaging(startEndNo);
    }
}
