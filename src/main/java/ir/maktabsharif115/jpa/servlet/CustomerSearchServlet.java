package ir.maktabsharif115.jpa.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktabsharif115.jpa.domain.Customer;
import ir.maktabsharif115.jpa.dto.CustomerCardboardDTO;
import ir.maktabsharif115.jpa.dto.CustomerSearch;
import ir.maktabsharif115.jpa.dto.ErrorDTO;
import ir.maktabsharif115.jpa.service.CustomerService;
import ir.maktabsharif115.jpa.util.ApplicationContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/customer/search")
public class CustomerSearchServlet extends HttpServlet {

    private ObjectMapper objectMapper;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        this.objectMapper = ApplicationContext.getInstance().getObjectMapper();
        this.customerService = ApplicationContext.getInstance().getCustomerService();
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        if ("application/json".equalsIgnoreCase(request.getContentType())) {
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            CustomerSearch customerSearch;
            try {
                customerSearch = objectMapper.readValue(
                        body, CustomerSearch.class
                );
            } catch (Exception e) {
                ErrorDTO errorDTO = new ErrorDTO(
                        ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                        "bad json"
                );
                writer.print(objectMapper.writeValueAsString(errorDTO));
                response.setStatus(400);
                return;
            }
            List<Customer> customers = customerService.findAll(customerSearch);
            writer.print(objectMapper.writeValueAsString(
                    new ArrayList<>(
                            customers.stream().map(
                                            customer -> new CustomerCardboardDTO(
                                                    customer.getId(),
                                                    customer.getFirstName(),
                                                    customer.getLastName(),
                                                    customer.getUsername(),
                                                    customer.getMobileNumber()
                                            )
                                    )
                                    .toList()
                    )
            ));
        } else {
            ErrorDTO errorDTO = new ErrorDTO(
                    ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    "only application/json supported"
            );
            writer.print(objectMapper.writeValueAsString(errorDTO));
            response.setStatus(415);
        }

    }
}
