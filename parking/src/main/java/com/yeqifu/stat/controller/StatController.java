package com.yeqifu.stat.controller;

import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.domain.Rent;
import com.yeqifu.bus.service.ICustomerService;
import com.yeqifu.bus.service.IRentService;
import com.yeqifu.bus.vo.CustomerVo;
import com.yeqifu.stat.domain.BaseEntity;
import com.yeqifu.stat.service.IStatService;
import com.yeqifu.stat.utils.ExportCustomerUtils;
import com.yeqifu.stat.utils.ExportRentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计分析
 */
@RequestMapping("stat")
@Controller
public class StatController {

    @Autowired
    private IStatService statService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IRentService rentService;

    /**
     * 跳转到客户地区统计页面
     */
    @RequestMapping("toCustomerAreaStat")
    public String toCustomerAreaStat(){
        return "stat/customerAreaStat";
    }

    /**
     * 加载客户地区数据
     * @return
     */
    @RequestMapping("loadCustomerAreaStatJson")
    @ResponseBody
    public List<BaseEntity> loadCustomerAreaStatJson(){
        return this.statService.loadCustomerAreaStatList();
    }

    /**
     * 导出客户数据
     * @param customerVo
     * @param response
     */
    @RequestMapping("exportCustomer")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo, HttpServletResponse response){
        List<Customer> customers = customerService.queryAllCustomerForList(customerVo);
        String fileName="客户数据.xls";
        String sheetName="客户数据";

        ByteArrayOutputStream bos = ExportCustomerUtils.exportCustomer(customers,sheetName);

        try {
            //处理文件名乱码
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建 封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            headers.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<Object>(bos.toByteArray(),headers, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("exportRent")
    public ResponseEntity<Object> exportRent(String rentid){

        Rent rent = rentService.queryRentByRentId(rentid);

        Customer customer = customerService.queryCustomerByIdentity(rent.getIdentity());

        String fileName=customer.getCustname()+"-的车位单.xls";
        String sheetName=customer.getCustname()+"车位单";

        ByteArrayOutputStream bos = ExportRentUtils.exportRent(rent,customer,sheetName);

        try {
            //处理文件名乱码
            fileName= URLEncoder.encode(fileName,"UTF-8");
            //创建 封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            headers.setContentDispositionFormData("attachment",fileName);
            return new ResponseEntity<Object>(bos.toByteArray(),headers, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
