package ir.maktabsharif115.jpa.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;

import java.io.PrintWriter;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@WebServlet(name = "date", urlPatterns = "/date")
public class DateServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.println(
                objectMapper.writeValueAsString(
                        new DateDTO(
                                ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        )
                )
        );
        writer.close();
    }


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateDTO implements Serializable {
        private String currentDate;
    }
}
