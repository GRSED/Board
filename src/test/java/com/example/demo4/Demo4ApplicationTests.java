package com.example.demo4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class Demo4ApplicationTests {
	@Autowired
	private BoardService boardService;

//	@Test
//	public void daoTest() {
//		List<BoardDto> boardDtoList = boardService.selectForPaging();
//		BoardDto boardDto = boardService.selectByNo(3);
//		System.out.println("******************** Test Result selectAll() ********************");
//		for (BoardDto board:
//			 boardDtoList) {
//			System.out.println(board);
//		}
//
//		System.out.println("******************** Test Result selectByNo() ********************");
//		System.out.println(boardDto);
//	}
//
//	@Test
//	public void daoTest2() {
//		BoardDto boardDto = new BoardDto(0, null, "제목3", "내용3");
//		boardService.insertBoard(boardDto);
//
//		System.out.println("******************** Test Result insertBoard() ********************");
//		List<BoardDto> boardDtoList = boardService.selectForPaging();
//		for (BoardDto board:
//				boardDtoList) {
//			System.out.println(board);
//		}
//	}
//
//	@Test
//	public void daoTest3() {
//		boardService.deleteBoard(21);
//		System.out.println("******************** Test Result deleteBoard() ********************");
//		List<BoardDto> boardDtoList = boardService.selectForPaging();
//		for (BoardDto board:
//				boardDtoList) {
//			System.out.println(board);
//		}
//	}
//
//	@Test
//	public void daoTest4() {
//		BoardDto boardDto = new BoardDto(3, null, "제목변경", "내용변경");
//		boardService.updateBoard(boardDto);
//
//		System.out.println("******************** Test Result updateBoard() ********************");
//		List<BoardDto> boardDtoList = boardService.selectForPaging();
//		for (BoardDto board:
//				boardDtoList) {
//			System.out.println(board);
//		}
//	}
}
