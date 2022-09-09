package com.cch.demo.user.controller;


import com.cch.demo.user.convert.UserConverter;
import com.cch.demo.user.entity.User;
import com.cch.demo.user.service.UserService;
import com.cch.demo.user.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
@RequestMapping("/user")
@Api(value = "用户接口")
public class UserController {

    @Autowired
    UserService userService;

    @Resource
    UserConverter userConverter;

    @ApiOperation(value = "添加用户")
    @PostMapping()
    @ResponseBody
    public UserVo addUser(@RequestBody @ApiParam(value = "用户信息", required = true) UserVo entity) {
        /*
         * TODO
         * 这里对传来的参数进行格式校验，转换等
         */
        User userDo = userConverter.convert(entity);
        User user = userService.save(userDo);
        return userConverter.convert(user);
    }

    @ApiOperation(value = "根据ID查询用户信息")
    @GetMapping("/{userId}")
    @ResponseBody
    public UserVo getUserById(@PathVariable @ApiParam(value = "用户ID", required = true) String userId) {
        User user = userService.get(userId);
        return userConverter.convert(user);
    }
}
