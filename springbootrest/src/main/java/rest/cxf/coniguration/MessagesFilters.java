package rest.cxf.coniguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * filtr zapytan który działa niezależnie od interceptorów apache cxf
 * ten filtr przechwytuje wszystkie zapytania
 * jezeli by miał reagować tylko na konktretne zapytania to by musiał zostać zarejestrowany przy pomocy FilterRegistrationBean -> wtedy można określić mapowania na jakie filtr ma reagować
 * najpierw wykonuje sie filtr a potem metody interceptorów apache cxf
 */
@Component
public class MessagesFilters implements Filter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("inicjalizacja filtra");
    }

    /**
     * UWAGA!: ważne tutaj jest wywołanie doFilter na obiekcie filterChain!! - bez tego dalsza obsługa zapytania nie jest wykonywana
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("metoda główna filtra - doFilter ");
        logger.info("metoda główna filtra request: " + servletRequest);
        logger.info("metoda główna filtra request: " + servletResponse);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.debug("niszczenie filtra");
    }
}
