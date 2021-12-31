
package com.stw.jmscommunications.jmslistener;

import com.google.gson.Gson;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.SendTo;
/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
//@Component
@Slf4j
public class JMSListenerDeactivated {

	@JmsListener(destination = "inbound.queue")
	@SendTo("outbound.queue")
	public String receiveMessage(final Message txtMessage) throws JMSException {
                log.info("INBOUND: Received Message:"+((TextMessage)txtMessage).getText());
                String response="Response to:"+((TextMessage)txtMessage).getText();
		return response;
	}

}