package com.lana.base.syshandle.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson配置类，用于自定义Java时间类型的序列化和反序列化格式。
 * @author liuyulet
 * @date 2024/3/16 13:00
 */
@Configuration
public class Jackson2Config {

    private static final DateTimeFormatter LOCAL_DATE_TIME_TS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter LOCAL_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer configureCustomJackson() {
        return builder -> {

            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(LOCAL_DATE_TIME_TS));
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(LOCAL_DATE_FORMATTER));
            builder.serializerByType(LocalTime.class, new LocalTimeSerializer(LOCAL_TIME_FORMATTER));

            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(LOCAL_DATE_TIME_TS));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(LOCAL_DATE_FORMATTER));
            builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(LOCAL_TIME_FORMATTER));

            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            builder.failOnUnknownProperties(false);
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}


