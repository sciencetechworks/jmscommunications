
package com.stw.jmscommunications.jmsproducer;

import com.stw.jmscommunications.config.JMSConfig;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@Component
@RequiredArgsConstructor
public class JMSProducer {
    
	private JMSConfig jmsConfig;
        
        
        private JmsTemplate jmsTemplate; 

        public JMSProducer(String brokerUrl,
                 String user,
                 String password)
        {
            jmsConfig=new JMSConfig(brokerUrl,user,password);
            jmsTemplate=jmsConfig.jmsTemplate();
        }
        
	public void sendMessage(final String queueName, final String the_message) {
		jmsTemplate.send(queueName, new MessageCreator() {

                        @Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
                                message.setText(the_message);
				return message;
			}
		});
	}

}