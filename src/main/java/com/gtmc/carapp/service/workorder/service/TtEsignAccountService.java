package com.gtmc.carapp.service.workorder.service;

import com.gtmc.carapp.service.workorder.entity.TtEsignAccount;
import com.gtmc.carapp.service.workorder.mapper.TtEsignAccountMapper;
import com.yonyou.cloud.common.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TtEsignAccountService  extends BaseService<TtEsignAccountMapper, TtEsignAccount> {

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 查询用户是否注册过e签宝
     * @param phone 用户手机号
     * @param userId 用户ID
     * @return 0否 1是
     */
    public Integer isOpenSign(String phone,String userId){
        logger.info("查询用户是否注册过e签宝Service start");
        try {
            return mapper.isOpenSign(phone,userId);
        } catch (Exception e) {
            logger.error("查询用户是否注册过e签宝 error:",e);
            return -1;
        }
    }

}
