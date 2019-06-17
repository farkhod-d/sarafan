package uz.gigalab.sarafan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.gigalab.sarafan.domain.Message;
import uz.gigalab.sarafan.domain.Views;
import uz.gigalab.sarafan.dto.EventType;
import uz.gigalab.sarafan.dto.ObjectType;
import uz.gigalab.sarafan.web.util.WsSender;

import java.util.function.BiConsumer;

@Configuration
public class WsSenderConfig {

    @Bean
    public BiConsumer<EventType, Message> getWsSender(WsSender wsSender) {
        return wsSender.getSender(ObjectType.MESSAGE, Views.Name.class);
    }
}
