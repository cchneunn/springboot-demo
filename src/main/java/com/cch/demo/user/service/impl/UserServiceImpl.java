package com.cch.demo.user.service.impl;

import com.cch.demo.global.service.impl.GlobalServiceImpl;
import com.cch.demo.user.entity.User;
import com.cch.demo.user.mapper.UserMapper;
import com.cch.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends GlobalServiceImpl<UserMapper, User> implements UserService {

}
