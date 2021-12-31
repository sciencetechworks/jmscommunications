package com.stw.jmscommunications;

import com.stw.jmscommunications.jmsproducer.JMSProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableJms
@EnableScheduling
@Slf4j
public class JmscommunicationsApplication {
        private static int msgNumber=0;
        
        JMSProducer jmsProducer;
        
        private String brokerUrl;  
        
        private String user; 
        
        private String password; 
       
        @Autowired
        public JmscommunicationsApplication(
                @Value("${spring.activemq.broker-url}") String brokerUrl,
                @Value( "${spring.activemq.user}" ) String user,
                @Value( "${spring.activemq.password}") String password)
        {
            this.brokerUrl=brokerUrl;
            this.user=user;
            this.password=password;
            jmsProducer=new JMSProducer(brokerUrl,user,password);
        }
        
        @Scheduled(fixedRate = 2500)
        public void sendMessage()
        {
            String message= "Hello World! ("+msgNumber+")";
            jmsProducer.sendMessage("inbound.queue",message);
            msgNumber++;         
            log.info("JMS MESSAGE SENT:"+message);
        }
        
             
	public static void main(String[] args) {
		ApplicationContext c=SpringApplication.run(JmscommunicationsApplication.class, args);
     }
	

}
