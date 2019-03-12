package com.o98k.excel.model;

/**
 * @Author: 周大伟
 * @Date: 2019/2/14 11:26
 */
public class City {

    public static final String BEIJING = "北京";
    public static final String SHANGHAI = "上海";
    public static final String SHANGHAI_XUJIAHUI = "徐家汇";
    public static final String TIANJING = "天津";
    public static final String HANGZHOU = "杭州";
    public static final String CHENGDU = "成都";
    public static final String CHENGDU_WUHOU = "武侯";
    public static final String CHENGDU_HUANHUAXI= "浣花溪";
    public static final String CHENGDU_HQZX= "精选环球中心";
    public static final String BEIJING_ZGC= "精选中关村";

    public static String cityMatch(String str){
        if(str.indexOf(BEIJING)>=0){
            return BEIJING;
        }else if(str.indexOf(SHANGHAI)>=0){
            return SHANGHAI;
        }else if(str.indexOf(TIANJING)>=0){
            return TIANJING;
        }else if(str.indexOf(HANGZHOU)>=0){
            return HANGZHOU;
        }else if(str.indexOf(CHENGDU)>=0){
            return CHENGDU;
        }else if(str.indexOf(SHANGHAI_XUJIAHUI)>=0){
            return SHANGHAI;
        }else if(str.indexOf(CHENGDU_WUHOU)>=0){
            return CHENGDU;
        }else if(str.indexOf(CHENGDU_HUANHUAXI)>=0){
            return CHENGDU;
        }else if(str.indexOf(CHENGDU_HQZX)>=0){
            return CHENGDU;
        }else if(str.indexOf(BEIJING_ZGC)>=0){
            return BEIJING;
        }else {
            return null;
        }
    }

}
