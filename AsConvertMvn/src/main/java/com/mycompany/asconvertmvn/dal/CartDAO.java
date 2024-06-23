/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asconvertmvn.dal;

import com.mycompany.asconvertmvn.model.Cart;
import com.mycompany.asconvertmvn.model.Item;
import com.mycompany.asconvertmvn.model.Product;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranm
 */
public class CartDAO {

    private static final Logger logger = Logger.getLogger(CartDAO.class.getName());

    public static final CartDAO INSTANCE = new CartDAO();
    private Connection con;

    public CartDAO() {

        con = new DBContext().connection;

    }

    public void insertCart(int customerId) {
        String sql = "Insert into Cart(CustomerID) values(?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public Cart getCartByCusId(int customerId) {
        String sql = "select * from Cart where CustomerID = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart(rs.getInt("CartId"), rs.getInt("CustomerID"));
                return cart;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return null;
    }

    public Cart getCartById(int id) {
        String sql = "select * from Cart where CartId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart(rs.getInt("CartId"), rs.getInt("CustomerID"));
                return cart;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return null;
    }

    public List<Cart> getAllCart() {
        List<Cart> list = new ArrayList<>();
        String sql = "select * from Cart";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart(rs.getInt("CartId"), rs.getInt("CustomerID"));
                list.add(cart);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return list;
    }

    public List<Item> getAllItem() {
        List<Item> list = new ArrayList<>();
        String sql = "select * from CartItem c join Product p ON c.ProductID = p.ProductID";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item i = new Item();
                i.setCartId(rs.getInt("CartId"));
                i.setPrice(rs.getDouble("Price"));
                i.setQuantity(rs.getInt("Quantity"));
                Product p = new Product();
                p.setProductId(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setSale(rs.getDouble("Sale"));
                p.setPrice(rs.getDouble("Price"));
                p.setImg(rs.getString("Image"));
                p.setDescription(rs.getString("Description"));
                i.setProduct(p);
                list.add(i);

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return list;
    }

    public int getQuantityItem(int productId) {
        String sql = "select Quantity from CartItem where ProductID = ?";
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("Quantity");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

        return result;
    }

    public void addItemsToCart(List<Item> items, Cart cart) {
        String sql = "MERGE INTO CartItem AS target USING (VALUES (?, ?, ?, ?)) AS source (CartId, ProductID, Quantity, Price) ON target.CartId = source.CartId AND target.ProductID = source.ProductID\n"
                + "WHEN MATCHED THEN UPDATE SET target.Quantity = target.Quantity + source.Quantity, target.Price = target.Price + (source.Quantity * source.Price)\n"
                + "WHEN NOT MATCHED THEN INSERT (CartId, ProductID, Quantity, Price) VALUES (source.CartId, source.ProductID, source.Quantity, source.Price);";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            for (Item item : items) {
                ps.setInt(1, cart.getCartId());
                ps.setInt(2, item.getProduct().getProductId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getPrice());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public void deleteItemById(int productId, int cartId) {
        String sql1 = "UPDATE CartItem SET Quantity = Quantity - 1 WHERE CartId = ? AND ProductID = ?";
        try {
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, cartId);
            ps1.setInt(2, productId);
            ps1.executeUpdate();
            String sql2 = "DELETE FROM CartItem\n"
                    + "WHERE CartId = ? AND ProductID = ? AND Quantity = 0;";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, cartId);
            ps2.setInt(2, productId);
            ps2.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        }

    }

    public void deleteItemByProId(int productId) {
        String sql1 = "Delete CartItem Where ProductID = ?";
        PreparedStatement ps1 = null;

        try {
            ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, productId);
            ps1.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {

                if (ps1 != null) {
                    ps1.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public void updateItemByProId(int productId, int quantity) {
        String sql1 = "Update CartItem set Quantity = ? Where ProductID = ?";
        PreparedStatement ps1 = null;

        try {
            ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, quantity);
            ps1.setInt(2, productId);
            ps1.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {
                if (ps1 != null) {
                    ps1.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public void deleteAllItemByProId(int productId) {
        String sql = "DELETE  FROM CartItem WHERE ProductID = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public void deleteAllItemByCartId(int cartId) {
        String sql = "DELETE  FROM CartItem WHERE CartId = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public void deleteAllItemByCusId(int customerId) {
        String sql = "DELETE  FROM Cart WHERE CustomerID = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);

        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "", e);
            }

        }

    }

    public static void main(String[] args) {

    }

}
