package com.gtmc.carapp.service.workorder.service;

import com.gtmc.carapp.service.workorder.entity.TtEsignConstructionOrderDetail;
import com.gtmc.carapp.service.workorder.mapper.TtEsignConstructionOrderDetailMapper;
import com.yonyou.cloud.common.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TtEsignConstructionOrderDetailService extends BaseService<TtEsignConstructionOrderDetailMapper, TtEsignConstructionOrderDetail> {

    private Logger logger = Logger.getLogger(this.getClass());



}
