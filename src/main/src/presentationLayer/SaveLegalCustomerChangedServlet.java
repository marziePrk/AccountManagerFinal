package presentationLayer;

import bussinessLogicLayer.LegalCustomerLogic;
import exception.DuplicateCodeException;
import exception.EmptyFieldException;
import util.OutputGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static util.OutputGenerator.generateSuccessfulPage;

/**
 * Created by Dotin school 6 on 8/14/2016.
 */
public class SaveLegalCustomerChangedServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        long id = Long.parseLong(request.getParameter("id"));
        String companyName = request.getParameter("companyName");
        String registerDate = request.getParameter("registerDate");
        String economicId = request.getParameter("economicId");
        String outHTML = "";

        try {
            //LegalCustomerLogic.deleteEconomicId(id);
            LegalCustomerLogic.checkValidation( id ,companyName.trim(), registerDate.trim(), economicId.trim());
            LegalCustomerLogic.updateLegalCustomer(id, companyName, registerDate, economicId);
            outHTML = generateSuccessfulPage("عملیات بروزرسانی با موفقیت انجام شد.");
        } catch (SQLException e) {
            outHTML = OutputGenerator.generateExceptionPage(e.getMessage() + "خطا در پایگاه داده...");
        } catch (DuplicateCodeException e) {
            outHTML = OutputGenerator.generateExceptionPage(e.getMessage());
        } catch (EmptyFieldException e) {
            outHTML = OutputGenerator.generateExceptionPage(e.getMessage());
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
