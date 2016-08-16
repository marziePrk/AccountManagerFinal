package presentationLayer;

import bussinessLogicLayer.RealCustomerLogic;
import dataAccessLayer.RealCustomer;
import util.OutputGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import static util.OutputGenerator.generateRealCustomerUpdatePage;

/**
 * Created by Dotin school 6 on 8/13/2016.
 */
public class UpdateRealCustomerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        String customerNumber = request.getParameter("customerNumber");
        String outHTML = "";


        try {
            ArrayList<RealCustomer> realCustomers = RealCustomerLogic.retrieveRealCustomerByCustomerNumber(customerNumber);
            RealCustomer realCustomer = realCustomers.get(0);
            outHTML = generateRealCustomerUpdatePage(realCustomer);
        } catch (SQLException e) {
            outHTML = OutputGenerator.generateExceptionPage(e.getMessage() + "خطا در پایگاه داده...");
        }
        response.setContentType("text/html ;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(outHTML);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
