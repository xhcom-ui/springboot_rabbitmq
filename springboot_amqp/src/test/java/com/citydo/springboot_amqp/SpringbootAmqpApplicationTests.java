package com.citydo.springboot_amqp;

import com.citydo.springboot_amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public  void  createExchange(){
        //创建交换器
        //amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        //创建队列
        //amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        //绑定规则
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,
                "amqpadmin.exchange","amqp.text",null));
        //删除
        amqpAdmin.deleteExchange("amqpadmin.exchange");
        amqpAdmin.deleteQueue("amqpadmin.queue");
    }

    //点对点
    @Test
    public void contextLoads() {
        //message 自己构造  定制消息体内容和消息头
        //rabbitTemplate.send(exchange,routekey,message);
        //只需要传入发送消息对象 自动化序列化发个rabbitmq
        //rabbitTemplate.convertAndSend(exchange,routekey,object);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","消息发送中");
        map.put("data", java.util.Arrays.asList("hello",123,true));
        //对象默认序列化以后发送
        //rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("添加","孙悟空"));
    }

    @Test
    public void reciveLoads(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.err.println(o);
    }

    //广播
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
        rabbitTemplate.convertAndSend("exchange.news","",new Book("红楼梦","曹雪芹"));
    }

}
