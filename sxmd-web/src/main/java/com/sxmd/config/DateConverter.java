package com.sxmd.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sxmd.constant.ConstantPattern;
import com.sxmd.util.LocalTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Description: 全局日期转换器
 *
 * @author cy
 * @date 2020年04月17日 15:56
 * Version 1.0
 */
@Configuration
@Slf4j
@ConditionalOnExpression("${sxmd.web.date-converter.enabled:true}")
public class DateConverter implements Converter<String, Date> {


    /**
     * 处理json返回的
     */
    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(ConstantPattern.DATE_PATTERN_DATE_TIME));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return x -> x.serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(LocalDateTime.class, localDateTimeDeserializer())
                .serializerByType(BigInteger.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance);
    }


    /**
     * 处理 json 传入的
     */
    private static final List<String> FORMARTS = new ArrayList<>(5);

    static {
        FORMARTS.add("yyyy-MM");
        FORMARTS.add("yyyy-MM-dd");
        FORMARTS.add("yyyy-MM-dd HH:mm");
        FORMARTS.add("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, FORMARTS.get(0));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, FORMARTS.get(1));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, FORMARTS.get(2));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, FORMARTS.get(3));
        } else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    public Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            log.warn("时间转换失败", e);
        }
        return date;
    }


    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return x -> Optional.ofNullable(LocalTimeUtil.stringToLocalDate(x)).orElse(null);
    }

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return x -> Optional.ofNullable(LocalTimeUtil.stringToLocalDateTime(x)).orElse(null);
    }

    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return x -> Optional.ofNullable(LocalTimeUtil.stringToLocalTime(x)).orElse(null);
    }
}
