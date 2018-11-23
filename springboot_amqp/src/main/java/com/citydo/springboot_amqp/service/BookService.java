package com.citydo.springboot_amqp.service;

import com.citydo.springboot_amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues="atguigu.news")
    public void receive(Book book){
        System.err.println("接收到消息"+book);
    }
    //获取响应头
    @RabbitListener(queues="atguigu")
    public void receiveMsg(Message message){
        System.err.println(message.getBody());
        System.err.println(message.getMessageProperties());
    }
}
