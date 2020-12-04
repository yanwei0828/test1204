package com.gtmc.carapp.service.workorder.mapper;

import com.gtmc.carapp.service.workorder.entity.TtEsignAccount;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TtEsignAccountMapper extends Mapper<TtEsignAccount> {

    /**
     * 查询用户是否注册过e签宝
     * @param phone 用户当前登录的手机号码
     * @param userId 用户ID
     * @return
     */
    Integer isOpenSign(@Param("phone") String phone, @Param("userId") String userId);
}
