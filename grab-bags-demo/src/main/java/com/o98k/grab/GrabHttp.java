package com.o98k.grab;

import com.o98k.util.HttpClient;
import okhttp3.FormBody;
import okhttp3.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GrabHttp {

    private static final String GRAB_BAG_URL = "https://063801hb.com/Home/Fetch";

    private static int success_times = 1;

    private static int fail_times = 1;


    public void grabBag(){
        try {
            FormBody formBody = new FormBody.Builder()
                    .add("username","zdw321")
                    .build();
            Headers.Builder headerBuilder = new Headers.Builder();
            headerBuilder.add("Connection","keep-alive");
            headerBuilder.add("Content-Length","16");
            headerBuilder.add("Content-Type","application/x-www-form-urlencoded");
            headerBuilder.add("charset","UTF-8");
            String result = HttpClient.doPost(GRAB_BAG_URL,formBody,headerBuilder.build());
            System.out.println("success times = "+success_times++ + "次，返回消息=>"+result);
        }catch (IOException e){
            System.out.println("fail times = "+fail_times++ + "次");
        }
    }

}
