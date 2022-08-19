package com.ldm.stat.service.impl;

import com.ldm.stat.domain.BaseEntity;
import com.ldm.stat.mapper.StatMapper;
import com.ldm.stat.service.IStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements IStatService {

    @Autowired
    private StatMapper statMapper;

    /**
     * 查询客户地区统计数据
     * @return
     */
    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        return this.statMapper.queryCustomerAreaStat();
    }

}
