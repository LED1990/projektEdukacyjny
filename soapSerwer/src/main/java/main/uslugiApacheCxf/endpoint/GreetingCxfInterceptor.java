package main.uslugiApacheCxf.endpoint;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.signature.X509Data;
import org.opensaml.xml.util.Base64;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;


@Component
public class GreetingCxfInterceptor extends AbstractSoapInterceptor {

    private static final Logger log = LogManager.getLogger(GreetingCxfInterceptor.class);

    public GreetingCxfInterceptor() {
        super(Phase.INVOKE);
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        log.info("soap message phase invoke interceptor - czyli przechwytywacz na etapie wywoływania usługi");
        List<Header> soapHeaders = soapMessage.getHeaders();
        Assertion samlAssertion = null;
        List<X509Certificate> listaCertyfikataowSaml = new ArrayList<>();
        if (soapHeaders.isEmpty()) {
            log.error("brak headera w wiadomości saml - brak assercji");
        } else {
            Node node = (Node) soapHeaders.get(0).getObject();
            samlAssertion = pobierzAssercjeSamlZHeadera(node);
        }
        if (samlAssertion != null) {
            listaCertyfikataowSaml = pobierzListeCertyfikatowSaml(samlAssertion);
        } else {
            log.error("brak certyfikatu w zapytaniu saml");
        }
        for (X509Certificate cert : listaCertyfikataowSaml
        ) {
            log.info(cert);
        }

        log.info("koniec interceptora");
    }

    private List<X509Certificate> pobierzListeCertyfikatowSaml(Assertion assertion) {
        List<X509Data> listaCertSaml = assertion.getSignature().getKeyInfo().getX509Datas();
        List<org.opensaml.xml.signature.X509Certificate> listaCertyfikatowX509PrzedKonwersja = new ArrayList<>();
        for (X509Data data : listaCertSaml) {
            listaCertyfikatowX509PrzedKonwersja.addAll(data.getX509Certificates());
        }
        return konwertujCertyfikaty(listaCertyfikatowX509PrzedKonwersja);
    }

    private List<X509Certificate> konwertujCertyfikaty(List<org.opensaml.xml.signature.X509Certificate> lista) {
        List<X509Certificate> listaCert = new ArrayList<>();
        for (org.opensaml.xml.signature.X509Certificate certificate : lista
        ) {
            try {
                listaCert.add((java.security.cert.X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(certificate.getValue()))));
            } catch (CertificateException e) {
                log.error("błąd podczas konwersji certyfikatów ", e);
            }
        }
        return listaCert;
    }

    private Assertion pobierzAssercjeSamlZHeadera(Node node) {
        ElementNSImpl assertionElement = (ElementNSImpl) node.getFirstChild().getNextSibling();
        try {
            DefaultBootstrap.bootstrap();
        } catch (ConfigurationException e) {
            log.error("błąd konfiguracji open saml ", e);
        }
        UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
        Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(assertionElement);
        try {
            return (Assertion) unmarshaller.unmarshall(assertionElement);
        } catch (UnmarshallingException e) {
            log.error("błąd podczas pobiernia assercji z odpowiedzi saml ", e);
        }
        return null;
    }
}
