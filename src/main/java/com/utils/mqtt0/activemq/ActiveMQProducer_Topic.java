package com.utils.mqtt0.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * http://127.0.0.1:8161/admin/index.jsp
 * admin admin
 */
public class ActiveMQProducer_Topic {
    //java与ActiveMQ服务器连接的url，默认端口是61616
    private static final String url = "tcp://127.0.0.1:61616";
    //Destination是Topic主题，主题的名字
    private static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        /*
         * 基于JMS规范的java连接ActiveMQ的生产者代码实现
         * */
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //根据连接工厂建立连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        System.out.println("生产者已连接MQ...");
        //创建Session，参数1：是否支持事务，参数2：确认机制
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建Destination
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建生产者
        MessageProducer messageProducer = session.createProducer(topic);
        //创建并投递消息
        for (int i=0;i<5;i++){
            Message message = session.createTextMessage("消息："+i);
            messageProducer.send(message);
        }
        System.out.println("消息已发送到MQ...");
        //关闭连接
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("生产者已关闭连接...");
    }
}