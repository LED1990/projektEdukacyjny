package main.uslugiApacheCxf.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class GreetingCxfInterceptorTest {
    private static final Logger log = LogManager.getLogger(GreetingCxfInterceptorTest.class);
    String[] keystory = {"certyTls"};
    String[] keystorePass = {"certykey"};


    private CertificateFactory cf = null;

    @Before
    public void przygotujDane() {
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            log.error("nie można stworzyć instancji fabryki certyfikatów", e);
        }

    }

    @Test
    public void pobierzLancuchCertyfikatowZKeystoraTest() {
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("jks");
        } catch (KeyStoreException e) {
            log.error("błąd podczas tworzenia instancji keystore ", e);
        }
        if (keystory.length != keystorePass.length) {
            log.error("keystorów musi być tyle ile haseł!!! powiadomienie email");
        }
        try {
            if (keyStore != null) {
                keyStore.load(new FileInputStream("src/test/resources/" + keystory[0] + ".jks"), keystorePass[0].toCharArray());
            }
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            log.error("błąd podczas tworzenia keystora ", e);
        }
        Set<TrustAnchor> zaufane = new HashSet<>();
        try {
            if (keyStore != null) {
                zaufane = zaufaneCerty(keyStore);
            }
        } catch (KeyStoreException e) {
            log.error("błąd podczas pobierania certyfiaktów z keystora ", e);
        }
        X509Certificate certificate = null;
        try {
            certificate = (X509Certificate) cf.generateCertificate(new FileInputStream("src/test/resources/nieZaufanyCert.cer"));
        } catch (CertificateException | FileNotFoundException e) {
            e.printStackTrace();
        }
        PKIXCertPathBuilderResult wynik = null;
        try {
            wynik = walidujLancuchCertyfikatu(zaufane, certificate);
        } catch (InvalidAlgorithmParameterException | CertPathBuilderException | NoSuchAlgorithmException e) {
            log.error("błąd podczas walidacji certyfiaktu ", e);
        }
        if (wynik == null) {
            log.error("błąd podany certyfikat nie jest zaufany");
        } else {
            log.info("certyfiakt zaufany - SUKCESS");
        }

        log.info("koniec");
    }

    private PKIXCertPathBuilderResult walidujLancuchCertyfikatu(Set<TrustAnchor> zaufane, X509Certificate certificate) throws InvalidAlgorithmParameterException, CertPathBuilderException, NoSuchAlgorithmException {
        X509CertSelector selector = new X509CertSelector();
        selector.setCertificate(certificate);
        PKIXBuilderParameters pkixParams;
        PKIXCertPathBuilderResult result;
        pkixParams = new PKIXBuilderParameters(zaufane, selector);
        pkixParams.setRevocationEnabled(false);
        CertPathBuilder certPathBuilder = CertPathBuilder.getInstance("PKIX");
        result = (PKIXCertPathBuilderResult) certPathBuilder.build(pkixParams);
        return result;
    }

    private Set<TrustAnchor> zaufaneCerty(KeyStore keyStore) throws KeyStoreException {
        Set<TrustAnchor> wynik = new HashSet<>();
        Enumeration<String> aliasyZkeystora;
        aliasyZkeystora = keyStore.aliases();
        while (aliasyZkeystora.hasMoreElements()) {
            String alias = aliasyZkeystora.nextElement();
            if (keyStore.isCertificateEntry(alias)) {
                Certificate cert = keyStore.getCertificate(alias);
                if (cert instanceof X509Certificate) {
                    wynik.add(new TrustAnchor((X509Certificate) cert, null));
                }
            }
        }
        return wynik;
    }

}