package com.c1exchange.meta.EventsInterface;

//import com.c1exchange.meta.EventsInterface.repository.SourceDtoRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.EnableScheduling;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
//@EnableSwagger2
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class EventsInterfaceApplication {
	//@Autowired
	//SourceDtoRepository sourceDtoRepository;

	//@Autowired
	//private RedisTemplate redisTemplate;

	public static void main(String[] args) {

		SpringApplication.run(EventsInterfaceApplication.class, args);
	}

}
