package com.example.demo4;

import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("INSERT INTO BOARD VALUES(BOARD_NO_SEQ.NEXTVAL, SYSDATE, #{board_title}, #{board_content})")
    int insertBoard(BoardDto boardDto);

    @Delete("DELETE FROM BOARD WHERE BOARD_NO = #{board_no}")
    int deleteBoard(int board_no);

    @Update("UPDATE BOARD SET BOARD_TITLE = #{board_title}, BOARD_CONTENT = #{board_content} WHERE BOARD_NO = #{board_no}")
    int updateBoard(BoardDto boardDto);

    @Select("SELECT * FROM BOARD ORDER BY BOARD_NO DESC")
    List<BoardDto> selectAll();

    @Select("SELECT * FROM BOARD WHERE BOARD_NO = #{board_no}")
    BoardDto selectByNo(int board_no);

    @Select({
        "SELECT BOARD_NO, BOARD_DATE, BOARD_TITLE, BOARD_CONTENT"
        , "FROM (SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) AS SEQ, BOARD.* FROM BOARD)"
        , "WHERE SEQ BETWEEN #{start_no} AND #{end_no}"
    })
    List<BoardDto> selectForPaging(StartEndNo startEndNo);
}
