package mjersey;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * implementacja jersy przy pomocy javy (annotacje) działa ale:!!!!!
 * może być problem z uruchomieniem na zwykłym tomcacie!!!
 * działa na serwerach EE (np. wildfly!!)
 * url dla tej aplikacji wygląda np tak: http://localhost:9080/nazwawara/api/hello/world
 * nazwa wara zdefiniowana w pomie
 */
@ApplicationPath("api")
public class RestApplication extends Application {
}
