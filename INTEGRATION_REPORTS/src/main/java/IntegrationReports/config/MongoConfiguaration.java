package IntegrationReports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
//Convert Date ZonedDateTime
public class MongoConfiguaration {

    @Bean
    public MongoCustomConversions customConversions(){
        List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(DateToZonedDateTimeConverter.INSTANCE);
        converters.add( ZonedDateTimeToDateConverter.INSTANCE);
        return new MongoCustomConversions(converters);
    }

    enum DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

        INSTANCE;

        @Override
        public ZonedDateTime convert(Date source) {
        	return ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
//             ofInstant(source.toInstant(), systemDefault());
        }
    }

    enum ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

        INSTANCE;

        @Override
        public Date convert(ZonedDateTime source) {
            return Date.from(source.toInstant());
        }
    }
}
