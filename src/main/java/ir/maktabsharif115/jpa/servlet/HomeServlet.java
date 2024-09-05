package ir.maktabsharif115.jpa.servlet;

import com.github.javafaker.Faker;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.PrintWriter;


@WebServlet(name = "home", urlPatterns = "/")
public class HomeServlet extends HttpServlet {

    private final Faker faker = new Faker();

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("pageTitle", "page title from attr");
        request.setAttribute("myName", faker.name().firstName());
        request.setAttribute("myName2", faker.name().firstName());
        request.getRequestDispatcher("home.jsp")
                .forward(request, response);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter writer = resp.getWriter();
        writer.println("""
                                <html lang="en">
                                <head><title>My Home Page</title></head>
                                <body>
                                <h1>This is Heading 1 in post method</h1>
                                <p>This is first paragraph in post method</p>
                                </body>
                                </html>
                """);
        writer.close();
    }
}
