package org.devs.heythere_backend.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;


import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguration {
    //Sender config
    @Bean
    public ProducerFactory<String, ChatMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), null, new JsonSerializer<ChatMessage>());
    }

    @Bean
    public KafkaTemplate<String, ChatMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public Map<String, Object> producerConfigs() {

        Map<String, Object> producerConfigs = new HashMap<>();

        producerConfigs.put("bootstrap.servers", "localhost:9092");//kafka server ip & port
        producerConfigs.put("key.serializer", IntegerSerializer.class);
        producerConfigs.put("value.serializer", JsonSerializer.class);//Object json parser
        producerConfigs.put("group.id", "spring-boot-test"); // chatting  group id

        return producerConfigs;
    }

    //Receiver config
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChatMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ChatMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ChatMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), null, new JsonDeserializer<>(ChatMessage.class));
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        final Map<String, Object> consumerConfigs = new HashMap<>();

        consumerConfigs.put("bootstrap.servers", "localhost:9092");
        consumerConfigs.put("key.deserializer", IntegerDeserializer.class);
        consumerConfigs.put("value.deserializer", JsonDeserializer.class);
        consumerConfigs.put("group.id", "spring-boot-test");

        return consumerConfigs;
    }
}
