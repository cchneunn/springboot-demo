package com.cch.demo.global.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PageConverter {

    @AfterMapping
    default void afterPage(@MappingTarget Page<?> page, Page<?> data) {

    }
}
