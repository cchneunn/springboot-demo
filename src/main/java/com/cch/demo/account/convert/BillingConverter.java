package com.cch.demo.account.convert;

import com.cch.demo.account.entity.Billing;
import com.cch.demo.account.vo.BillingVo;
import com.cch.demo.global.convert.PageConverter;
import com.cch.demo.global.page.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring", uses = PageConverter.class)
public interface BillingConverter {

    @Mappings({
            @Mapping( target="paymentamount",source="paymentAmount"),
            @Mapping( target="paystarttime",source="payStartTime"),
            @Mapping( target="payendtime",source="payEndTime")})
    Billing convert(BillingVo billingVo);

    @Mappings({@Mapping( target="paymentAmount",source="paymentamount"),
            @Mapping( target="payStartTime",source="paystarttime"),
            @Mapping( target="payEndTime",source="payendtime")})
    BillingVo convert(Billing billing);

    @Mappings({})
    List<BillingVo> convert(List<Billing> billingList);

    @Mappings({})
    PageResponse<BillingVo> convert(PageResponse<Billing> billingPages);
}
