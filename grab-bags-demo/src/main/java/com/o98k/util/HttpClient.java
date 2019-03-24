package com.o98k.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * get请求
     */
    public static String doGet(String url) throws IOException {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        result = response.body().string();
        return result;
    }

    /**
     * get请求
     */
    public static String doGet(String url, Map<String, Object> map) throws IOException {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .headers(setHeaders(map))
                .build();
        Response response = client.newCall(request).execute();
        result = response.body().string();
        return result;
    }

    /**
     * post 请求
     */
    public static String doPost(String url, RequestBody body) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * post 请求
     */
    public static String doPost(String url, RequestBody body, Map<String, Object> headerMap) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .headers(setHeaders(headerMap))
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * json post 请求
     */
    public static String doJosnPost(String url, String params) throws IOException {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        result = response.body().string();
        return result;
    }


    /**
     * json post 请求
     */
    public static String doJosnPost(String url, String params, int second) throws IOException {
        String result = null;
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(second, TimeUnit.SECONDS)
                .readTimeout(second, TimeUnit.SECONDS)
                .writeTimeout(second, TimeUnit.SECONDS)
                .build();
        RequestBody body = RequestBody.create(JSON, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        result = response.body().string();
        return result;
    }

    /**
     * json post 请求
     */
    public static String doJosnPost(String url, String params, Headers headers) throws IOException {
        String result;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(headers)
                .build();
        Response response = client.newCall(request).execute();
        result = response.body().string();
        return result;
    }

    /**
     * json post 请求
     */
    public static String doJosnPost(String url, String params, Headers headers, int second) throws IOException {
        String result;
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(second, TimeUnit.SECONDS)
                .readTimeout(second, TimeUnit.SECONDS)
                .writeTimeout(second, TimeUnit.SECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(headers)
                .build();
        Response response = client.newCall(request).execute();
        result = response.body().string();
        return result;
    }

    /**
     * key-value post 请求
     * FormBody 构造形式 - okhttp3
     * FormBody formBody = new FormBody.Builder()
     * .add("key1", "value1")
     * .add("key2", "value2)
     * .add("key3", "value3")
     * .build();
     */
    public static String doPost(String url, FormBody formBody,Headers headers) throws IOException {
        String result;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .headers(headers)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            result = response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
        return result;
    }

    /**
     * 设置Header头
     *
     * @param headersParams
     * @return
     */
    public static Headers setHeaders(Map<String, Object> headersParams) {
        Headers headers = null;

        Headers.Builder headerBuilder = new Headers.Builder();

        if (headersParams != null) {
            for (String key : headersParams.keySet()) {
                headerBuilder.add(key, headersParams.get(key).toString());
            }
        }

        headers = headerBuilder.build();

        return headers;
    }

    /**
     * post 异步请求
     */
    public static void doAsynPost(String url, String params) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);

        //异步处理方法
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("异步消息推送\n"+ e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                }
            }
        });
    }

    public static void main(String[] args) {
        String string = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjoiNyJ9.93GtxYHI3wydO5YUcUEAE2oSixoFT9ggKnlHD-k-W4U";
        System.out.println(string.length());
    }
}