package com.lanbao.activemq;

import java.util.Hashtable;  
import java.util.Map;  
  
import javax.jms.Connection;  
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.MapMessage;  
import javax.jms.Message;  
import javax.jms.MessageProducer;  
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;  
import org.apache.activemq.command.ActiveMQMapMessage;  
  
public class Publisher {  
      
    protected int MAX_DELTA_PERCENT = 1;  
    protected Map<String, Double> LAST_PRICES = new Hashtable<String, Double>();  
    protected static int count = 10;  
    protected static int total;  
      
    protected static String brokerURL = "tcp://www.jiujichina.com:61616";  
    protected static transient ConnectionFactory factory;  
    protected transient Connection connection;  
    protected transient Session session;  
    protected transient MessageProducer producer;  
      
    public static void main(String[] args) throws JMSException {  
    	 // 连接到ActiveMQ服务器  
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);  
        Connection connection = factory.createConnection();  
        connection.start();  
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
        // 创建主题  
        Topic topic = session.createTopic("slimsmart.topic.test");  
        MessageProducer producer = session.createProducer(topic);  
        // NON_PERSISTENT 非持久化 PERSISTENT 持久化,发送消息时用使用持久模式  
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
        for (int i = 0; i < 10; i++) {
        	 TextMessage message = session.createTextMessage();  
             message.setText("topic 消息。"+i);  
             message.setStringProperty("property", "消息Property");  
             // 发布主题消息  
             producer.send(message);  
             System.out.println("Sent message: " + message.getText());  
		}
       
       
        session.commit();  
        session.close();  
        connection.close();  
    }  
    
    public Publisher() throws JMSException {  
        factory = new ActiveMQConnectionFactory(brokerURL);  
        connection = factory.createConnection();  
        try {  
        connection.start();  
        } catch (JMSException jmse) {  
            connection.close();  
            throw jmse;  
        }  
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
        producer = session.createProducer(null);  
        producer.setDeliveryMode(DeliveryMode.PERSISTENT); 
    }  
      
    public void close() throws JMSException {  
        if (connection != null) {  
            connection.close();  
        }  
    }  
      

  
    protected void sendMessage(String[] stocks) throws JMSException {  
        int idx = 0;  
        while (true) {  
            idx = (int)Math.round(stocks.length * Math.random());  
            if (idx < stocks.length) {  
                break;  
            }  
        }  
        String stock = stocks[idx];  
        Destination destination = session.createTopic("STOCKS." + stock);   
        Message message = createStockMessage(stock, session);  
        System.out.println("Sending: " + ((ActiveMQMapMessage)message).getContentMap() + " on destination: " + destination);  
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        producer.send(destination, message);  
    }  
  
    protected Message createStockMessage(String stock, Session session) throws JMSException {  
        Double value = LAST_PRICES.get(stock);  
        if (value == null) {  
            value = new Double(Math.random() * 100);  
        }  
  
        // lets mutate the value by some percentage  
        double oldPrice = value.doubleValue();  
        value = new Double(mutatePrice(oldPrice));  
        LAST_PRICES.put(stock, value);  
        double price = value.doubleValue();  
  
        double offer = price * 1.001;  
  
        boolean up = (price > oldPrice);  
  
        MapMessage message = session.createMapMessage();  
        message.setString("stock", stock);  
        message.setDouble("price", price);  
        message.setString("name", "李益");
        message.setDouble("offer", offer);  
        message.setBoolean("up", up);  
        return message;  
    }  
  
    protected double mutatePrice(double price) {  
        double percentChange = (2 * Math.random() * MAX_DELTA_PERCENT) - MAX_DELTA_PERCENT;  
  
        return price * (100 + percentChange) / 100;  
    }  
  
}  