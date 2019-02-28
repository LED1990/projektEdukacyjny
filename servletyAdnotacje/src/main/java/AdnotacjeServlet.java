import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "greetingAnnotationsServlet", value = "/servletAdnotacje")
public class AdnotacjeServlet extends HttpServlet{


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("metoda service servletu z adnotacjami");
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("metoda do get servletu z adnotacjami");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("hello from do get servlet z adnotacjami!!");

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() throws ServletException {
        System.out.println("metoda init servletu z adnotacjami");
        super.init();
    }
}
