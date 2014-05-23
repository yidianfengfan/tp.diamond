package com.starit.diamond.main;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by leishouguo on 2014/5/23.
 */
public class HttpClients {

    public HttpClient init() throws IOException {
        BasicClientConnectionManager manager = new BasicClientConnectionManager();
        manager.closeIdleConnections(60, TimeUnit.SECONDS);
        HttpClient httpclient = new DefaultHttpClient(manager );
        httpclient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
        HttpHost host = new HttpHost("127.0.0.1", 8080);
        HttpGet httpget = new HttpGet("http://baidu.com");
        httpget.setHeader(HttpHeaders.CONTENT_MD5, "14254");
        HttpResponse response = httpclient.execute(host, httpget);
        try{
            HttpEntity entity =  response.getEntity();
            if(entity != null){
                EntityUtils.toString(entity);
            }
        }catch (Exception e){

        }

        return httpclient;
    }
}
