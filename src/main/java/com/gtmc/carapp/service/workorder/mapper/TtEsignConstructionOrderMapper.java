package com.gtmc.carapp.service.workorder.mapper;

import com.gtmc.carapp.service.workorder.entity.TtEsignConstructionOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TtEsignConstructionOrderMapper extends Mapper<TtEsignConstructionOrder> {

    /**
     * 新增电子施工单，并返回id
     * @param entity
     * @return
     */
    int addConstructionOrder(TtEsignConstructionOrder entity);

    int updateConstructionOrder(TtEsignConstructionOrder entity);

    TtEsignConstructionOrder queryConstructionOrder(Map<String, Object> params);

    List<TtEsignConstructionOrder> selectSigned(@Param("orderNumber") String orderNumber);

    /**
     * 获取车型名
     */
     String getModelName(@Param("carmodel") String carmodel);
     void saveAuthorizedServices(@Param("phone") String phone, @Param("userId") int userId);
     List<Map<String,Object>> queryAuthorizedServices(@Param("phone") String phone, @Param("userId") int userId);
     void updateAuthorizedServices(@Param("phone") String phone, @Param("userId") int userId);
     void saveWillingnessToSign(@Param("id") Long id, @Param("userId") int userId);
}
