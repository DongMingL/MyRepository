package com.yeqifu.stat.service.impl;

import com.yeqifu.stat.domain.BaseEntity;
import com.yeqifu.stat.mapper.StatMapper;
import com.yeqifu.stat.service.IStatService;
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
