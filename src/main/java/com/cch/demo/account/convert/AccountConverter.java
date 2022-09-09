package com.cch.demo.account.convert;

import com.cch.demo.account.entity.Account;
import com.cch.demo.account.vo.AccountVo;
import com.cch.demo.global.convert.PageConverter;
import com.cch.demo.global.page.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring", uses = PageConverter.class)
public interface AccountConverter {

    @Mappings({
            @Mapping( target="specialaccountno",source="specialAccountNo")})
    Account convert(AccountVo accountVo);

    @Mappings({ @Mapping( target="specialAccountNo",source="specialaccountno")})
    AccountVo convert(Account account);

    @Mappings({})
    List<AccountVo> convert(List<Account> accountList);

    @Mappings({})
    PageResponse<AccountVo> convert(PageResponse<Account> accountPages);
}
