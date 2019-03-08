package rest.cxf.coniguration;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * interceptory moga pełnić te same zadania co filtry servletów
 * dodatkowo wykonuja się jesli zapytanie posiada body
 * filtry moga przechwytywać wszystkie zapytania i dzialać przed interveptorami
 * filtry sa przydatne do kontroli bezpieczenstwa
 * interceptoy sa lepsze do pracy na body zapytania
 */
public class MessageOutInterceptor extends AbstractPhaseInterceptor<Message> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    MessageOutInterceptor() {
        super(Phase.SEND);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        logger.info("widadomość wychodzaca: " + message);
    }
}
