package com.riskcontrol.kg.datasyncengine.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.net.URL;

@Slf4j
public class YamlParser {
    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static <T> T readValue(String yamlFilePath, Class<T> clazz){
        URL resource = ClassLoader.getSystemClassLoader().getResource(yamlFilePath);

        if(resource == null){
            //log.error("yaml config file not found path is {}",yamlFilePath);
            throw new RuntimeException("cong file not found");
        }
       try {
           return mapper.readValue(resource,clazz);
       }catch (IOException e){
           //log.error("exception is {}", ExceptionUtils.getStackTrace(e));
           throw new RuntimeException(e);
       }
    }
}
