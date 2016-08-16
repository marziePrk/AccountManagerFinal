package presentationLayer;

import bussinessLogicLayer.RealCustomerLogic;
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
public class SaveRealCustomerChangedServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF8");
        long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String birthDate = request.getParameter("birthDate");
        String nationalId = request.getParameter("nationalCode");
        String outHTML = "";

        try {
            RealCustomerLogic.checkValidation(id ,firstName.trim(), lastName.trim(), fatherName.trim(), birthDate.trim(), nationalId.trim());
            RealCustomerLogic.updateRealCustomer(id,firstName , lastName , fatherName , birthDate , nationalId);
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
