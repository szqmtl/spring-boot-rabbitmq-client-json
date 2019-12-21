package rshu.springboot.mq.mqreceiver;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import rshu.springboot.mq.mqreceiver.service.RabbitMqListener;

@EnableRabbit
@SpringBootApplication
public class MqReceiverApplication implements RabbitListenerConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MqReceiverApplication.class, args);
	}

	static final public String ExchangeName = "exchange-test";
	static final public String QueueName = "queue-test";
	static final public String RoutingName = "routing-test";

	@Bean
	DirectExchange exchange(){
		return new DirectExchange(ExchangeName);
	}

	@Bean
	Queue queue(){
		return new Queue(QueueName, false);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(RoutingName);
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter(){
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory(){
		var factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}
	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
}
