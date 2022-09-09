package com.cch.demo.user.convert;

import com.cch.demo.global.convert.PageConverter;
import com.cch.demo.global.page.PageResponse;
import com.cch.demo.user.entity.User;
import com.cch.demo.user.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring", uses = PageConverter.class)
public interface UserConverter {

    @Mappings({})
    User convert(UserVo userVo);

    @Mappings({})
    UserVo convert(User user);

    @Mappings({})
    List<UserVo> convert(List<User> userList);

    @Mappings({})
    PageResponse<UserVo> convert(PageResponse<User> userPages);
}
