package com.live.debate.resolver.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AmqpConfiguration {

    @Bean
    public TopicExchange roundCompleteExchange(
            @Value("${amqp.exchange.round}") final String exchangeName
    ){
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    public Queue roundResolvedQueue(
            @Value("${amqp.queue.round}") final String queueName
    ){
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding resolvedRoundBinding(
            final TopicExchange exchange,
            final Queue queue
    ){
        String routingKey = "round.*.duel";

        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(routingKey);
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory(){
        DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();

        final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.getObjectMapper().registerModule(
                new ParameterNamesModule(JsonCreator.Mode.PROPERTIES)
        );

        defaultMessageHandlerMethodFactory.setMessageConverter(converter);
        return defaultMessageHandlerMethodFactory;
    }

    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(
            final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return (c) -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }

}
