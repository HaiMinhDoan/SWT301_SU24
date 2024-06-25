/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.asconvertmvn.controller;


import com.mycompany.asconvertmvn.dal.CartDAO;
import com.mycompany.asconvertmvn.dal.CustomerDAO;
import com.mycompany.asconvertmvn.dal.OrderDAO;
import com.mycompany.asconvertmvn.dal.ProductDAO;
import com.mycompany.asconvertmvn.model.Account;
import com.mycompany.asconvertmvn.model.Cart;
import com.mycompany.asconvertmvn.model.Customer;
import com.mycompany.asconvertmvn.model.Item;
import com.mycompany.asconvertmvn.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author tranm
 */
public class PayServlet extends HttpServlet {

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
            out.println("<title>Servlet PayServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PayServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            List<Product> list = ProductDAO.INSTANCE.getAllProduct();
            Cookie[] arr = request.getCookies();
            String cok = "";
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        cok += o.getValue();
                    }
                }
            }
            Cart cart = new Cart(cok, list);
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            Customer c = new Customer();
            c.setFirstName(firstName);
            c.setLastName(lastName);
            c.setEmail(email);
            c.setAddress(address);
            c.setPhone(phone);
            c.setGender(gender);
            int cusId = CustomerDAO.INSTANCE.insertCustomer(c);
            OrderDAO.INSTANCE.addOrderByCusId(cusId, cart);
            Cookie cookie = new Cookie("cart", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            session.setAttribute("paymentSuccess", true);
            // Chuyển hướng đến trang chính
            response.sendRedirect("home");
        } else {
            Account account = (Account) session.getAttribute("account");
            Customer customer = CustomerDAO.INSTANCE.getCustomerById(account.getCustomerId());
            // Thêm đơn hàng
            List<Item> listItems = CartDAO.INSTANCE.getAllItem();
            List<Cart> listCarts = CartDAO.INSTANCE.getAllCart();
            for (Cart cart : listCarts) {
                for (Item item : listItems) {
                    if (item.getCartId() == cart.getCartId()) {
                        cart.addItem(item);
                    }
                }
            }

            Cart cart = new Cart();
            for (Cart cart1 : listCarts) {
                if (cart1.getCustomerId() == customer.getCustomerId()) {
                    cart = cart1;
                }
            }
            
            OrderDAO.INSTANCE.addOrder(customer, cart);
            CartDAO.INSTANCE.deleteAllItemByCartId(cart.getCartId());
            session.setAttribute("paymentSuccess", true);
           
            response.sendRedirect("home");
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
