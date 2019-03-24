package com.o98k.grab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description: 创建线程任务服务
 * @ClassName: CustomMultiThreadingService
 * @Author: OnlyMate
 * @Date: 2018年9月21日 下午3:17:57
 */
@Service
public class CustomMultiThreadingService {

    @Autowired
    private GrabHttp grabHttp;
    /**
     * @Description:通过@Async注解表明该方法是一个异步方法，
     * 如果注解在类级别上，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     * @Title: executeAysncTask1
     * @Date: 2018年9月21日 下午2:54:32
     * @Author: OnlyMate
     * @Throws
     * @param
     */
    @Async
    public void executeAysncTask(){
        for(int i = 0;i<100;i++){
            grabHttp.grabBag();
        }
    }

    @Async
    public void executeAsync2() {
        System.out.println("异步任务::2");
    }

}