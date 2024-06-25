/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.asconvertmvn.controller;

import com.mycompany.asconvertmvn.dal.CartDAO;
import com.mycompany.asconvertmvn.dal.ProductDAO;
import com.mycompany.asconvertmvn.model.Cart;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranm
 */
public class BuyServlet extends HttpServlet {

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
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("view/buy.jsp").forward(request, response);
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
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }

            String num = request.getParameter("num");
            String id = request.getParameter("id");
            int idRaw= Integer.parseInt(id);
            Cart cart;
            if (cok.isEmpty()) {
                cok = id + ":" + num;  
            } else {
                cart = new Cart(cok, list);
                Product p = null;
                try {
                    p = ProductDAO.INSTANCE.getProductById(idRaw);
                } catch (Exception ex) {
                    Logger.getLogger(BuyServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                int quantityItem = cart.getQuantityById(idRaw);
                int numStore = p.getQuantity();
                if (numStore > quantityItem) {
                    cok = cok + "." + id + ":" + num;
                } else {
                    session.setAttribute("messAddCart", "Không thể thêm vì sản phẩm này trong giỏ hàng của bạn đã nhiều hơn số lượng sản phẩm còn");
                }

            }
            Cookie c = new Cookie("cart", cok);
            c.setMaxAge(1 * 24 * 60 * 60);
            response.addCookie(c);

            response.sendRedirect("home");

        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            int customerId = Integer.parseInt(request.getParameter("cusId"));
            Cart cart = CartDAO.INSTANCE.getCartByCusId(customerId);
            if (cart == null) {
                CartDAO.INSTANCE.insertCart(customerId);
            } else {
                Product p = null;
                try {
                    p = ProductDAO.INSTANCE.getProductById(id);
                } catch (Exception ex) {
                    Logger.getLogger(BuyServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                int quantityItem = CartDAO.INSTANCE.getQuantityItem(id);
                int numStore = p.getQuantity();
                if (numStore > quantityItem) {
                    double price = p.getNewPrice();
                    Item t = new Item();
                    t.setProduct(p);
                    t.setQuantity(1);
                    t.setPrice(price);
                    cart.addItem(t);
                    List<Item> list = cart.getItems();
                    CartDAO.INSTANCE.addItemsToCart(list, cart);
                } else {
                    session.setAttribute("messAddCart", "Không thể thêm vì sản phẩm này trong giỏ hàng của bạn đã nhiều hơn số lượng sản phẩm còn");
                }

            }
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
