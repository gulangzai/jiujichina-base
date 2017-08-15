package com.lanbao.activemq.producer.queue;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class QueueSender {
	
	@Autowired
	JmsTemplate jmsQueueTemplate;
	
	public void send(){ 
		
		jmsQueueTemplate.send(new MessageCreator() {
		    
		    public Message createMessage(Session session) throws JMSException {
		        String text = "A test for MessageCreator.";
		        Message message = session.createTextMessage(text); 
		        return message;
		    }
		});
		
	}
	
}
