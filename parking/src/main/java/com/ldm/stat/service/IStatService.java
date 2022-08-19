package com.ldm.stat.service;

import com.ldm.stat.domain.BaseEntity;

import java.util.List;

/**
 * 统计分析的数据服务接口
 */
public interface IStatService {

    /**
     * 查询客户地区数据
     * @return
     */
    List<BaseEntity> loadCustomerAreaStatList();


}
