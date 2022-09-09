package com.cch.demo.global.page;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> records;

    private long total;

    private long pageSize;

    private long pageNum;
}
