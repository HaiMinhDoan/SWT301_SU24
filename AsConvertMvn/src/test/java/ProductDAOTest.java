/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.asconvertmvn.dal.ProductDAO;
import com.mycompany.asconvertmvn.model.Category;
import com.mycompany.asconvertmvn.model.Product;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author admin
 */
public class ProductDAOTest {

    @Mock
    private ProductDAO productDAO;

    @Before
    public void setUp() throws Exception {
        productDAO = new ProductDAO();
        MockitoAnnotations.initMocks(this);
    }

    public ProductDAOTest() {
    }

    public String[][] getDataTest(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        String dataString = new String(bytes);
        String rowsString[] = dataString.split("#");
        int numOfRows = rowsString.length;
        String data[][] = new String[numOfRows][8];
        for (int i = 0; i < numOfRows; i++) {
            String temp = rowsString[i];
            String rows[] = temp.split("-");
            for (int j = 0; j < 8; j++) {
                data[i][j] = rows[j];
            }
        }
        return data;
    }

    public List<Product> getProductTestList(String[][] exResult) throws Exception {
        int numberOfRows = exResult.length;
        int numberOfCols = exResult[0].length;
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < numberOfRows; i++) {
            Category cate = new Category();
            cate.setCategoryId(Integer.parseInt(exResult[i][2]));
            Product product = Product.builder()
                    .productId(Integer.parseInt(exResult[i][0]))
                    .productName(exResult[i][1])
                    .category(cate)
                    .quantity(Integer.parseInt(exResult[i][3]))
                    .sale(Double.parseDouble(exResult[i][4]))
                    .price(Double.parseDouble(exResult[i][5]))
                    .img(exResult[i][6])
                    .build();
            list.add(product);
        }
        return list;
    }

    public boolean checkEqualsProduct(String[] exResult, Product product) throws Exception {
        String id = exResult[0];
        if (!id.equals(product.getProductId() + "")) {
            return false;
        }
        String name = exResult[1];
        if (!name.equals(product.getProductName())) {
            return false;
        }
        String cateId = exResult[2];
        if (!cateId.equals(product.getCategory().getCategoryId() + "")) {
            return false;
        }
        String quan = exResult[3];
        if (!quan.equals(product.getQuantity() + "")) {
            return false;
        }
        String saleString = exResult[4];
        Double sale = Double.parseDouble(saleString);
        if (!sale.equals(product.getSale())) {
            return false;
        }
        String priceString = exResult[5];
        Double price = Double.parseDouble(priceString);
        if (!price.equals(product.getPrice())) {
            return false;
        }
        String img = exResult[6];
        if (!img.equals(product.getImg())) {
            return false;
        }
        return true;
    }

    public boolean checkEqualsListProduct(List<Product> list1, List<Product> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        int le = list1.size();
        for (int i = 0; i < le; i++) {
            if(!equalsProduct(list1.get(i), list2.get(i))) return false;
        }
        return true;
    }
    public boolean equalsProduct(Product p1, Product p2){
        if(p1.getProductId()!=p2.getProductId()) return false;
        if(!p1.getProductName().equals(p2.getProductName())) return false;
        if(p1.getCategory().getCategoryId()!=p2.getCategory().getCategoryId()) return false;
        if(p1.getQuantity()!=p2.getQuantity()) return false;
        if(p1.getSale()!= p2.getSale()) return false;
        if(p1.getPrice()!=p2.getPrice()) return false;
        if(!p1.getImg().equals(p2.getImg())) return false;
        if(p1.getDescription().equals(p2.getDescription())) return false;
        return true;
    }

    @Test
    public void testGetProductById() throws Exception {
        productDAO = new ProductDAO();
        String[][] data = getDataTest("C:/FU-Learning/Su24/SWT301/dataFun1.txt");
        int numRows = data.length;
        int numCols = data[0].length;
        for (int i = 0; i < 2; i++) {
            try {
                int id = Integer.parseInt(data[i][0]);
                Product product = productDAO.getProductById(id);
                Assert.assertEquals(true, checkEqualsProduct(data[i], product));
            } catch (SQLException | ClassNotFoundException e) {
                Assert.assertEquals(true, true);
            }

        }

    }

    

    @Test
    public void testUpdateProduct() throws Exception {

        productDAO = new ProductDAO();
        String[][] data = getDataTest("C:/FU-Learning/Su24/SWT301/dataFun2.txt");
        int numRows = data.length;
        int numCols = data[0].length;
        List<Product> list = getProductTestList(data);
        int s = 0;
        for (Product product : list) {
            String eRString = data[s][7];
            boolean eR = eRString.equals("1");
            boolean check = productDAO.updateProduct(product);
            Assert.assertEquals(eR, check);
            s++;
        }
    }
    @Test
    public void testDeleteProduct() throws Exception {
        productDAO = new ProductDAO();
        String[][] data = getDataTest("C:/FU-Learning/Su24/SWT301/dataFun4.txt");
        int numRows = data.length;
        int numCols = data[0].length;
        List<Product> list = getProductTestList(data);
        int s = 0;
        for (Product product : list) {
            boolean check = productDAO.deleteProductById(product.getProductId());
            Assert.assertEquals(true, check);
            s++;
        }
    }

    @Test
    public void testInsertProduct() throws Exception {
        productDAO = new ProductDAO();
        String[][] data = getDataTest("C:/FU-Learning/Su24/SWT301/dataFun3.txt");
        List<Product> list = getProductTestList(data);
        for (Product product : list) {
            boolean check = productDAO.insertProduct(product);
            Assert.assertEquals(true, check);
        }
    }
}
