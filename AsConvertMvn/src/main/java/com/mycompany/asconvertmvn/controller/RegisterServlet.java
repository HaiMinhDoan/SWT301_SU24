/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.asconvertmvn.controller;
import com.mycompany.asconvertmvn.dal.AccountDAO;
import com.mycompany.asconvertmvn.dal.CartDAO;
import com.mycompany.asconvertmvn.dal.CustomerDAO;
import com.mycompany.asconvertmvn.model.Account;
import com.mycompany.asconvertmvn.model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author tranm
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        Account a = AccountDAO.INSTANCE.getAccountByUser(user);
        if (a == null) {
            Account newAccount = new Account();
            newAccount.setUserName(user);
            newAccount.setPassword(password);
            Customer c = new Customer();
            c.setFirstName(firstName);
            c.setLastName(lastName);
            c.setEmail(email);
            c.setPhone(phone);
            c.setAddress(address);
            c.setGender(gender);
            int cusId = CustomerDAO.INSTANCE.insertCustomer(c);
            AccountDAO.INSTANCE.insertAccount(newAccount, cusId);
            CartDAO.INSTANCE.insertCart(cusId);
            request.setAttribute("message", "Đăng ký thành công");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);

        } else {
            request.setAttribute("message", "Tài khoản đã tồn tại");
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("gender", gender);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("user", user);
            request.setAttribute("password", password);
            request.setAttribute("confirm", confirm);

            request.getRequestDispatcher("view/register.jsp").forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
