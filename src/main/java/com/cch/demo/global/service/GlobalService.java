package com.cch.demo.global.service;

import com.cch.demo.global.page.PageResponse;


public interface GlobalService<T> {

    T get(String id);

    T save(T entity);

//    T findTranlogByCid(T cid);

    PageResponse<T> page();
}
