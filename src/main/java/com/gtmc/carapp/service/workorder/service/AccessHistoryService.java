package com.gtmc.carapp.service.workorder.service;

import org.springframework.stereotype.Service;

import com.gtmc.carapp.service.workorder.entity.TtApiAccessHistory;
import com.gtmc.carapp.service.workorder.mapper.TtApiAccessHistoryMapper;
import com.yonyou.cloud.common.service.BaseService;

@Service
public class AccessHistoryService  extends BaseService<TtApiAccessHistoryMapper, TtApiAccessHistory> {

}
