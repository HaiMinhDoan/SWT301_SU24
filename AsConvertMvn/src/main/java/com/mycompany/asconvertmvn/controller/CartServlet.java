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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranm
 */
public class CartServlet extends HttpServlet {

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
            List<Item> listItem = cart.getItems();
            int n;
            if (listItem != null) {
                n = listItem.size();
            } else {
                n = 0;
            }

            for (int i = 0; i < cart.getItems().size(); i++) {
                if (cart.getItems().get(i).getQuantity() > cart.getItems().get(i).getProduct().getQuantity() && cart.getItems().get(i).getProduct().getQuantity() == 0) {
                    cart.removeItem(cart.getItems().get(i).getProduct().getProductId());
                } else if (cart.getItems().get(i).getQuantity() > cart.getItems().get(i).getProduct().getQuantity() && cart.getItems().get(i).getProduct().getQuantity() != 0) {
                    cart.getItems().get(i).setQuantity(cart.getItems().get(i).getProduct().getQuantity());
                }
            }
            request.setAttribute("size", n);
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("view/cart.jsp").forward(request, response);
        } else {
            List<Item> listItems = CartDAO.INSTANCE.getAllItem();
            List<Cart> listCarts = CartDAO.INSTANCE.getAllCart();
            for (Cart cart : listCarts) {
                for (Item item : listItems) {
                    if (item.getCartId() == cart.getCartId()) {
                        cart.addItem(item);
                    }
                }
            }
            for (Cart listCart : listCarts) {
                for (int i = 0; i < listCart.getItems().size(); i++) {
                    Product p = null;
                    try {
                        p = ProductDAO.INSTANCE.getProductById(listCart.getItems().get(i).getProduct().getProductId());
                    } catch (Exception ex) {
                        Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (listCart.getItems().get(i).getQuantity() > p.getQuantity() && p.getQuantity() == 0) {
                        CartDAO.INSTANCE.deleteAllItemByProId(listCart.getItems().get(i).getProduct().getProductId());
                    } else if (listCart.getItems().get(i).getQuantity() > p.getQuantity() && p.getQuantity() != 0) {
                        CartDAO.INSTANCE.updateItemByProId(listCart.getItems().get(i).getProduct().getProductId(), p.getQuantity());
                    }
                   
                }
            }        
            request.setAttribute("listItems", listItems);
            request.setAttribute("listCarts", listCarts);
            request.getRequestDispatcher("view/cart.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
