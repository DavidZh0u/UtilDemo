package com.o98k.excel;

import com.o98k.excel.util.PoiExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelUtilDemoApplicationTests {

    private static String new_hotel_sql = "insert into `serve_hotel` (`city`,`hotel_id`,`hotel_name`,`brand_name`,`type_name`,`hotel_address`,`opening_hours`, `operating_state`,`is_delete`,`modify_user`,`update_time`,`hotel_lng`,`hotel_lat` ) values" +
            " ({0},{1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{11},{12});";

    private static String new_hotel_service_sql = "insert into `serve_hotel_desc`(`hotel_id`,`serve_type`,`is_use`,`modify_user`,`update_time`) values  ({0},{1},{2},{3},{4});";

    @Test
    public void contextLoads() {
    }

    @Test
    public void handleHotels() throws Exception{
        String filePath = "C:\\Users\\David-PC\\Desktop\\需求\\2019-3-13数据变更\\行李寄送_新增配置名单_20190313.xlsx";
        List<List<String>> list = PoiExcelUtil.readInExcel(filePath);
        int flag = 0;
        for (List<String> obj : list) {
            if(!obj.get(2).equals("CityName")){
                flag ++;
                String sql = MessageFormat.format(new_hotel_sql,"'"+obj.get(2)+"'","'"+obj.get(0)+"'","'"+obj.get(1)+"'","'"+obj.get(3)+"'","'"+obj.get(4)+"'","'"+obj.get(6)+"'","'全天'","0","0","'admin'","SYSDATE()","'"+obj.get(10)+"'","'"+obj.get(9)+"'");
                System.out.println(sql);
            }
        }
        System.out.println("一共："+flag+"条");
    }

    @Test
    public void handleHotelService() throws Exception{
        String filePath = "C:\\Users\\David-PC\\Desktop\\需求\\2019-3-13数据变更\\行李寄送_新增配置名单_20190313.xlsx";
        List<List<String>> list = PoiExcelUtil.readInExcel(filePath);
        int flag = 0;
        for (List<String> obj : list) {
            if(!obj.get(2).equals("CityName")){
                flag ++;
                String sql = MessageFormat.format(new_hotel_service_sql,"'"+obj.get(0)+"'","1","1","'admin'","SYSDATE()");
                System.out.println(sql);
            }
        }
        System.out.println("一共："+flag+"条");
    }

}