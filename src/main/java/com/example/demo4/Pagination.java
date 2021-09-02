package com.example.demo4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Pagination {
    private int current_page_no;

    public int block_no () {
        return current_page_no/Constant.PAGE_COUNT_PER_BLOCK;
    }

    public StartEndNo startEndNo () {
        int startNo = current_page_no * Constant.ARTICLE_COUNT_PER_PAGE + 1;
        int endNo = (current_page_no + 1) * Constant.ARTICLE_COUNT_PER_PAGE;
        return new StartEndNo(startNo, endNo);
    }

    public int total_pages_no (int count_posts) {
        if (count_posts%Constant.ARTICLE_COUNT_PER_PAGE == 0) {
            return count_posts/Constant.ARTICLE_COUNT_PER_PAGE;
        } else {
            return count_posts/Constant.ARTICLE_COUNT_PER_PAGE + 1;
        }
    }
}
