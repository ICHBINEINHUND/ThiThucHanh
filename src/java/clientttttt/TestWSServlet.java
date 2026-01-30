package clientttttt;

import caculator.CalculatorService;
import caculator.CalculatorService_Service;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;

/**
 * Servlet xu ly logic goi Web Service
 * JSP chi hien thi view
 */
@WebServlet("/calculator")
public class TestWSServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Buoc 1: Tao instance cua Service
            CalculatorService_Service service = new CalculatorService_Service();

            // Buoc 2: Lay port de goi cac method
            CalculatorService port = service.getCalculatorServicePort();

            // Buoc 3: Goi cac phuong thuc va luu vao request attribute

            // Loi chao
            String greeting = port.greet("Sinh vien");
            request.setAttribute("greeting", greeting);

            // Cac phep tinh co ban
            request.setAttribute("sum", port.add(10, 5));
            request.setAttribute("diff", port.subtract(20, 8));
            request.setAttribute("product", port.multiply(7, 6));
            request.setAttribute("quotient", port.divide(100, 4));

            // Cac phep tinh them
            request.setAttribute("sum2", port.add(15, 25));
            request.setAttribute("diff2", port.subtract(100, 37));
            request.setAttribute("product2", port.multiply(12, 12));
            request.setAttribute("quotient2", port.divide(50, 3));

            // Danh dau thanh cong
            request.setAttribute("success", true);

        } catch (Exception e) {
            request.setAttribute("success", false);
            request.setAttribute("errorMessage", e.getMessage());
        }

        // Forward sang JSP de hien thi
        RequestDispatcher dispatcher = request.getRequestDispatcher("/calculator.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lay tham so tu form
        String aStr = request.getParameter("a");
        String bStr = request.getParameter("b");
        String operation = request.getParameter("operation");

        try {
            int a = Integer.parseInt(aStr);
            int b = Integer.parseInt(bStr);

            // Goi Web Service
            CalculatorService_Service service = new CalculatorService_Service();
            CalculatorService port = service.getCalculatorServicePort();

            Object result = null;

            switch (operation) {
                case "add":
                    result = port.add(a, b);
                    break;
                case "subtract":
                    result = port.subtract(a, b);
                    break;
                case "multiply":
                    result = port.multiply(a, b);
                    break;
                case "divide":
                    result = port.divide(a, b);
                    break;
            }

            request.setAttribute("customResult", result);
            request.setAttribute("customA", a);
            request.setAttribute("customB", b);
            request.setAttribute("customOp", operation);
            request.setAttribute("success", true);

            // Goi lai cac ket qua mac dinh
            request.setAttribute("greeting", port.greet("Sinh vien"));
            request.setAttribute("sum", port.add(10, 5));
            request.setAttribute("diff", port.subtract(20, 8));
            request.setAttribute("product", port.multiply(7, 6));
            request.setAttribute("quotient", port.divide(100, 4));
            request.setAttribute("sum2", port.add(15, 25));
            request.setAttribute("diff2", port.subtract(100, 37));
            request.setAttribute("product2", port.multiply(12, 12));
            request.setAttribute("quotient2", port.divide(50, 3));

        } catch (Exception e) {
            request.setAttribute("success", false);
            request.setAttribute("errorMessage", e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/calculator.jsp");
        dispatcher.forward(request, response);
    }
}