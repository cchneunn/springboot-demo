package com.cch.demo.global.condition;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseCondition {

    @ApiParam(value = "单页查询记录条数")
    private int pageSize;

    @ApiParam(value = "页码")
    private int pageNum;

    @ApiParam(value = "升序字段")
    private String[] ascs;

    @ApiParam(value = "降序字段")
    private String[] descs;

}
