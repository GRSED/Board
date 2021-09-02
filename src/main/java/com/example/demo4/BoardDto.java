package com.example.demo4;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardDto {
    private int board_no;
    private Date board_date;
    private String board_title;
    private String board_content;
}
