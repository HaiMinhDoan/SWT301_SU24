/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asconvertmvn.dal;
import com.mycompany.asconvertmvn.model.Category;
import com.mycompany.asconvertmvn.model.Product;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tranm
 */
public class ProductDAO {

    private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

    public static final ProductDAO INSTANCE = new ProductDAO();
    public Connection con;
    public String sql = "select * from Product p inner join Category c ON p.CategoryID = c.CategoryID where ProductID = ?";

    public ProductDAO() {
        con = new DBContext().connection;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        sql = "select * from Product p inner join Category c ON p.CategoryID = c.CategoryID";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setSale(rs.getDouble("Sale"));
                p.setPrice(rs.getDouble("Price"));
                p.setImg(rs.getString("Image"));
                p.setDescription(rs.getString("Description"));
                Category c = new Category();
                c.setCategoryId(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                p.setCategory(c);
                list.add(p);
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

    public Product getProductById(int id) {

        sql = "select * from Product p inner join Category c ON p.CategoryID = c.CategoryID where ProductID = ?";
        Product p = new Product();
        try (Connection connection = DBContextTest.getConnection() ;PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                p.setProductId(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setSale(rs.getDouble("Sale"));
                float priceFloat = rs.getFloat("Price");
                double priceDouble = priceFloat; // Chuyển đổi từ float sang double
                p.setPrice(priceDouble);
                p.setImg(rs.getString("Image"));
                Category c = new Category();
                c.setCategoryId(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                p.setCategory(c);

            }

        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "", e);
            p = null;
        }
        return p;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        sql = "select * from Category";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("CategoryID"), rs.getString("CategoryName"));
                list.add(c);
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

    public List<Product> getProductsByCategoryId(int id) {
        List<Product> list = new ArrayList<>();
        sql = "select * from Product p inner join Category c ON p.CategoryID = c.CategoryID where c.CategoryID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setSale(rs.getDouble("Sale"));
                p.setPrice(rs.getDouble("Price"));
                p.setImg(rs.getString("Image"));
                p.setDescription(rs.getString("Description"));
                Category c = new Category();
                c.setCategoryId(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                p.setCategory(c);
                list.add(p);
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

    public List<Product> getProductFilter(int choose) {
        List<Product> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        switch (choose) {
            case 1:
                sql = "select * from Product p inner join Category c ON p.CategoryID = c.CategoryID Order by ProductID desc";
                try {
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Product p = new Product();
                        p.setProductId(rs.getInt("ProductID"));
                        p.setProductName(rs.getString("ProductName"));
                        p.setQuantity(rs.getInt("Quantity"));
                        p.setSale(rs.getDouble("Sale"));
                        p.setPrice(rs.getDouble("Price"));
                        p.setImg(rs.getString("Image"));
                        p.setDescription(rs.getString("Description"));
                        Category c = new Category();
                        c.setCategoryId(rs.getInt("CategoryID"));
                        c.setCategoryName(rs.getString("CategoryName"));
                        p.setCategory(c);
                        list.add(p);
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

                break;
            case 2:
                sql = "SELECT Product.*, COALESCE(SUM(OrderDetail.Quantity), 0) AS TotalQuantity\n"
                        + "FROM Product\n"
                        + "LEFT JOIN OrderDetail ON Product.ProductID = OrderDetail.ProductID\n"
                        + "GROUP BY Product.ProductID, Product.ProductName, Product.CategoryID, Product.Quantity, Product.Description, Product.Sale, Product.Price, Product.Image\n"
                        + "ORDER BY TotalQuantity DESC;";
                try {
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Product p = new Product();
                        p.setProductId(rs.getInt("ProductID"));
                        p.setProductName(rs.getString("ProductName"));
                        p.setQuantity(rs.getInt("Quantity"));
                        p.setSale(rs.getDouble("Sale"));
                        p.setPrice(rs.getDouble("Price"));
                        p.setImg(rs.getString("Image"));
                        p.setDescription(rs.getString("Description"));
                        Category c = new Category();
                        p.setCategory(c);
                        list.add(p);
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
                break;
            case 3:
                sql = "select * from Product p inner join Category c ON p.CategoryID = c.CategoryID Order by Price asc";
                try {
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Product p = new Product();
                        p.setProductId(rs.getInt("ProductID"));
                        p.setProductName(rs.getString("ProductName"));
                        p.setQuantity(rs.getInt("Quantity"));
                        p.setSale(rs.getDouble("Sale"));
                        p.setPrice(rs.getDouble("Price"));
                        p.setImg(rs.getString("Image"));
                        p.setDescription(rs.getString("Description"));
                        Category c = new Category();
                        c.setCategoryId(rs.getInt("CategoryID"));
                        c.setCategoryName(rs.getString("CategoryName"));
                        p.setCategory(c);
                        list.add(p);
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
                break;

            case 4:
                sql = "select * from Product p inner join Category c ON p.CategoryID = c.CategoryID Order by Price desc";
                try {
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Product p = new Product();
                        p.setProductId(rs.getInt("ProductID"));
                        p.setProductName(rs.getString("ProductName"));
                        p.setQuantity(rs.getInt("Quantity"));
                        p.setSale(rs.getDouble("Sale"));
                        p.setPrice(rs.getDouble("Price"));
                        p.setImg(rs.getString("Image"));
                        p.setDescription(rs.getString("Description"));
                        Category c = new Category();
                        c.setCategoryId(rs.getInt("CategoryID"));
                        c.setCategoryName(rs.getString("CategoryName"));
                        p.setCategory(c);
                        list.add(p);
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

                break;

            default:
                logger.log(Level.SEVERE, "incorrect input");
                break;
        }

        return list;
    }

    public List<Product> searchProductByName(String key) {
        List<Product> list = new ArrayList<>();

        sql = "select * from Product p inner join Category c ON p.CategoryID = c.CategoryID where ProductName like N'%" + key + "%'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setSale(rs.getDouble("Sale"));
                p.setPrice(rs.getDouble("Price"));
                p.setImg(rs.getString("Image"));
                p.setDescription(rs.getString("Description"));
                Category c = new Category();
                c.setCategoryId(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                p.setCategory(c);
                list.add(p);
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

    public boolean insertProduct(Product p)  {
        sql = "INSERT INTO Product (ProductName, CategoryID, Price, Image, Quantity, Sale) VALUES (?, ?, ?, ?, ?, ?)";

        int check = 0;
        try (Connection connection = DBContextTest.getConnection() ;PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategory().getCategoryId());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getImg());
            ps.setInt(5, p.getQuantity());
            ps.setDouble(6, p.getSale());
            check = ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "", e);

        }
        return check > 0;
    }

    public boolean updateProduct(Product p) {
        sql = "Update Product SET ProductName = ?, Quantity = ?, Sale = ?, Price = ?, Image = ? where ProductID = ? ";

        int check = 0;
        try (Connection connection = DBContextTest.getConnection() ;PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getQuantity());
            ps.setDouble(3, p.getSale());
            ps.setDouble(4, p.getPrice());
            ps.setString(5, p.getImg());
            ps.setInt(6, p.getProductId());
            check = ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "", e);

        }

        return check > 0;
    }

    public boolean deleteProductById(int id) {
        sql = "Delete Product where ProductID = ?";

        int check = 0;
        try (Connection connection = DBContextTest.getConnection() ;PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            check = ps.executeUpdate();
        } catch (SQLException| ClassNotFoundException e) {
            logger.log(Level.SEVERE, "", e);
            return false;
        }
        return check >= 0;
    }
}
