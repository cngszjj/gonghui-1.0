package com.gh.util;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class RequestService {
	  //get�������ļ����ȴ�С����   
    public static boolean getRequest(String urlPath) throws Exception  
    {  
        URL url=new URL(urlPath);  
        HttpURLConnection con=(HttpURLConnection)url.openConnection();  
        con.setRequestMethod("GET");  
        con.setReadTimeout(5*1000);  
        if(con.getResponseCode()==200)  
        {  
            return true;
        }  
        return false;  
    }  
    //post�������ļ����ȴ�С����   
    public static boolean postRequest(String urlPath,Map<String,String> map) throws Exception  
    {  
        StringBuilder builder=new StringBuilder(); //ƴ���ַ�   
        //�ó���ֵ   
        if(map!=null && !map.isEmpty())  
        {  
            for(Map.Entry<String, String> param:map.entrySet())  
            {  
                builder.append(param.getKey()).append('=').append(URLEncoder.encode(param.getValue(), "utf-8")).append('&');  
            }  
            builder.deleteCharAt(builder.length()-1);  
        }  
        //�����Content-Length: �����URL�Ķ��������ݳ���   
        byte b[]=builder.toString().getBytes();  
        URL url=new URL(urlPath);  
        HttpURLConnection con=(HttpURLConnection)url.openConnection();  
        con.setRequestMethod("POST");  
        con.setReadTimeout(5*1000);  
        con.setDoOutput(true);//���������   
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//��������   
        con.setRequestProperty("Content-Length",String.valueOf(b.length));//����   
        OutputStream outStream=con.getOutputStream();  
        outStream.write(b);//д������   
        outStream.flush();//ˢ���ڴ�   
        outStream.close();  
        //״̬���ǲ��ɹ�   
        if(con.getResponseCode()==200)  
        {  
            return true;  
        }  
        return false;   
          
    }  
}
