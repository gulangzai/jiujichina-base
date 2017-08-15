package com.lanbao.activemq;

import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.Destination;  
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;  
  
public class Consumer {  
  
    private static String brokerURL = "tcp://www.jiujichina.com:61616";  
    private static transient ConnectionFactory factory;  
    private transient Connection connection;  
    private transient Session session;  
      
    public Consumer() throws JMSException {  
        factory = new ActiveMQConnectionFactory(brokerURL);  
        connection = factory.createConnection();  
        connection.setClientID("bbb");
        connection.start();  
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
    }  
      
    public void close() throws JMSException {  
        if (connection != null) {  
            connection.close();  
        }  
    }      
      
    public static void main(String[] args) throws JMSException {   
    	 String clientId = "client_id";   
         // 连接到ActiveMQ服务器  
         ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);  
         Connection connection = factory.createConnection();  
         //客户端ID,持久订阅需要设置  
         connection.setClientID(clientId);  
         connection.start();  
         Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);  
         // 创建主题  
         Topic topic = session.createTopic("slimsmart.topic.test");  
         // 创建持久订阅,指定客户端ID。  
        // MessageConsumer consumer = session.createDurableSubscriber(topic,clientId);  
         MessageConsumer consumer = session.createConsumer(topic);
         consumer.setMessageListener(new MessageListener() {  
             // 订阅接收方法 ` 
             public void onMessage(Message message) {  
                 TextMessage tm = (TextMessage) message;  
                 try {  
                     System.out.println("Received message: " + tm.getText()+":"+tm.getStringProperty("property"));  
                 } catch (JMSException e) {  
                     e.printStackTrace();  
                 }  
             }  
         });  
         
    	/*args=new String[]{"orcl"};
        Consumer consumer = new Consumer();  
        for (String stock : args) {  
            Destination destination = consumer.getSession().createTopic("STOCKS." + stock); 
            Topic topic = consumer.getSession().createTopic("STOCKS." + stock);
            MessageConsumer messageConsumer = consumer.getSession().createDurableSubscriber(topic,"bbb");  
            //MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);  
             messageConsumer.setMessageListener(new Listener());  
            
        }  */
    }  
      
    public Session getSession() {  
        return session;  
    }  
  
}  