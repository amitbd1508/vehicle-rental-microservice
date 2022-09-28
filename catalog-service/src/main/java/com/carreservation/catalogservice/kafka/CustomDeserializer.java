package com.carreservation.catalogservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomDeserializer implements Deserializer{
    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] request = arg1;
        try {

            //request = mapper.readValue(arg1, Object.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return request;
    }


    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
