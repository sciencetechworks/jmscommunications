
package com.stw.jmscommunications.jmslistener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@Component
@Slf4j
public class JMSResponseListener {

	@JmsListener(destination = "outbound.queue")
	public void receiveMessage(final Message txtMessage) throws JMSException {
                log.info("OUTBOUND: Received Message:"+((TextMessage)txtMessage).getText());
	}

}