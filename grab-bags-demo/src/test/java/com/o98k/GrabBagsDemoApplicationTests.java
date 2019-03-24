package com.o98k;

import com.o98k.grab.CustomMultiThreadingService;
import com.o98k.grab.GrabHttp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrabBagsDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Resource
    private CustomMultiThreadingService customMultiThreadingService;

    @Autowired
    private GrabHttp grabHttp;

    @Test
    public void testTRask(){
        for (int i = 0; i < 10; i++) {
            customMultiThreadingService.executeAysncTask();
        }
    }

    @Test
    public void testTRask1(){
        for (int i = 0; i < 1000000; i++) {
            grabHttp.grabBag();
        }
    }

}
