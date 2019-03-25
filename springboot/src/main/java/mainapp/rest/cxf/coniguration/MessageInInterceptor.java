package mainapp.rest.cxf.coniguration;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * interceptory moga pełnić te same zadania co filtry servletów
 * dodatkowo wykonuja się jesli zapytanie posiada body
 * filtry moga przechwytywać wszystkie zapytania i dzialać przed interveptorami
 * filtry sa przydatne do kontroli bezpieczenstwa
 * interceptoy sa lepsze do pracy na body zapytania
 */
@Component
public class MessageInInterceptor extends AbstractPhaseInterceptor<Message> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    MessageInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        logger.info("wiadomość przychodzaca: " + message);
    }
}
