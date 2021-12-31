
package com.stw.jmscommunications.config;

import javax.jms.JMSException;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@Configuration
@Setter
@Slf4j
public class JMSConfig {
       
        private String brokerUrl;  
        
        private String user; 
        
        private String password; 
       
        @Autowired
        public JMSConfig(@Value("${spring.activemq.broker-url}") String brokerUrl,
                @Value( "${spring.activemq.user}" ) String user,
                @Value( "${spring.activemq.password}") String password)
        {
            this.brokerUrl=brokerUrl;
            this.user=user;
            this.password=password;
        }
        
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
            
  	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            try {
                connectionFactory.setBrokerURL(brokerUrl);
            } catch (JMSException ex) {
                log.error(ex.toString());
            }
	    connectionFactory.setPassword(password);
	    connectionFactory.setUser(user);
           
	    return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
	    JmsTemplate template = new JmsTemplate();
	    template.setConnectionFactory(connectionFactory());
	    return template;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws JMSException {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory());
	    factory.setConcurrency("1-1");
	    return factory;
	}

}