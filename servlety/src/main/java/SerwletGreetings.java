import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * źeby servlet działał trzeba go zarejestrować wewnątrz web.xml
 * servlety potrzebują kontenera serwletów do działania
 */
public class SerwletGreetings extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("metoda init servletu");
        super.init();
    }

    /**
     * jak jest zaimplementowana metoda service to nie wywołuje się do get
     * trzeba wywołać ręcznie
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("metoda service servletu");
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("metoda do get servletu");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("hello from do get!!");

    }

    @Override
    public void destroy() {
        System.out.println("metoda destroy servletu");
    }
}
