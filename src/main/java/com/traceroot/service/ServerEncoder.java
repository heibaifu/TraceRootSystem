package com.traceroot.service;

import com.google.gson.Gson;
import com.traceroot.vo.UpdateBoatVO;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * 自定义的encoder
 */
public class ServerEncoder implements Encoder.Text<Object> {

    @Override
    public void destroy() {


    }

    @Override
    public void init(EndpointConfig arg0) {


    }

    @Override
    public String encode(Object o) throws EncodeException {
        Gson gson=new Gson();
        return gson.toJson(o);
    }

}
