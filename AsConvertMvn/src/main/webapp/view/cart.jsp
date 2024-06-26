<%-- 
    Document   : cart
    Created on : Feb 24, 2024, 7:31:57 PM
    Author     : tranm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.asconvertmvn.model.Customer"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="com.mycompany.asconvertmvn.model.Account"%>
<%@page import="com.mycompany.asconvertmvn.dal.AccountDAO"%>
<%@page import="com.mycompany.asconvertmvn.dal.CustomerDAO"%>
<%@page import="com.mycompany.asconvertmvn.dal.CustomerDAO"%>





<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/store/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
        <title>My Cart</title>

        <style>
            .modal-add--container{
                display: none;
            }

            .card-header{
                position: relative;
            }

            .add-close-btn {
                position: absolute;
                font-size: 24px;
                top: -8px;
                right: 5px;
            }

            .add-close-btn:hover {

                cursor: pointer;
            }

            .gender-select{
                display: block;
                font-size: 14px;
                color: #333;
                font-weight: bold;
                margin-bottom: 1px;
                width: 100%;
            }

            .card-checkout {
                width: 350px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-shadow: 2px 2px 8px rgba(0,0,0,0.1);
                overflow: hidden;
                margin-top: 40px;
                background: #fff;
            }

            .card-header {
                background-color: #333;
                padding: 16px;
                text-align: center;

            }

            .card-header .text-header {
                margin: 0;
                font-size: 18px;

            }

            .card-body {
                padding: 16px;
            }

            .form-group {
                margin-bottom: 10px;
            }

            .form-group label {
                display: block;
                font-size: 14px;
                color: #333;
                font-weight: bold;
                margin-bottom: 1px;
            }

            .form-group input[type="text"],
            .form-group input[type="email"],
            .form-group input[type="password"] {
                width: 100%;
                padding: 8px;
                font-size: 14px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .btn-checkout {
                padding: 12px 24px;
                margin-left: 13px;
                font-size: 16px;
                border: none;
                border-radius: 4px;
                background-color: #333;
                color: #fff;
                text-transform: uppercase;
                transition: background-color 0.2s ease-in-out;
                cursor: pointer
            }

            .btn-checkout:hover {
                background-color: #ccc;
                color: #333;
            }
        </style>
    </head>
    <body>
        <div>     
            <jsp:include page="header.jsp" />        
        </div>
        <div id="cart">

            <section class="h-100 h-custom" style="background-color: #d2c9ff;">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12">
                            <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                                <div class="card-body p-0">
                                    <div class="row g-0">
                                        <div class="col-lg-8">
                                            <div class="p-5">
                                                <div class="d-flex justify-content-between align-items-center mb-5">
                                                    <h1 class="fw-bold mb-0 text-black">Giỏ hàng</h1> 

                                                    <c:if test="${sessionScope.account eq null}">

                                                        <c:set var="size" value="${cart.items.size()}" /> 
                                                        <c:if test="${size > 0}">
                                                            <h6 class="mb-0 text-muted">${size} sản phẩm</h6>
                                                        </c:if>
                                                    </div>
                                                    <c:set var="cart" value="${requestScope.cart}" /> 
                                                    <c:forEach items="${cart.items}" var="i">

                                                        <hr class="my-4">
                                                        <div class="row mb-4 d-flex justify-content-between align-items-center">
                                                            <div class="col-md-2 col-lg-2 col-xl-2">
                                                                <img
                                                                    src="${i.product.img}"
                                                                    class="img-fluid rounded-3"
                                                                    alt=""
                                                                    >
                                                                    
                                                            </div>
                                                            <div class="col-md-3 col-lg-3 col-xl-3">
                                                                         
                                                                <h6 class="text-black mb-0">${i.product.productName}</h6>
                                                            </div>
                                                            <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                                <button class="btn btn-link px-2"
                                                                        >
                                                                    <a href="process?num=-1&id=${i.product.productId}"> <i class="fas fa-minus"></i></a>
                                                                </button>

                                                                <div id="form1"  name="quantity"
                                                                     class="form-control form-control-sm">${i.quantity} </div>

                                                                <button class="btn btn-link px-2"
                                                                        >
                                                                    <a href="process?num=1&id=${i.product.productId}"><i class="fas fa-plus"></i></a>
                                                                </button>
                                                            </div>
                                                            <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                                <h6 class="mb-0 product-price">${i.product.getNewPrice() * i.quantity}</h6>
                                                            </div>
                                                            <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                                <form action="process" method="post">
                                                                    <input type="hidden" name="id" value="${i.product.productId}" />
                                                                    <button style="border: none; background-color: #FFF" type="submit" class="text-muted">
                                                                        <i class="fas fa-times"></i>
                                                                    </button>
                                                                </form>

                                                            </div>
                                                        </div>

                                                    </c:forEach>


                                                    <hr class="my-4">

                                                    <div class="pt-5">
                                                        <h6 class="mb-0"><a href="home" class="text-body"><i
                                                                    class="fas fa-long-arrow-alt-left me-2"></i>Tiếp tục mua hàng</a></h6>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 bg-grey">
                                                <div class="p-5">
                                                    <h3 class="fw-bold mb-5 mt-2 pt-1">Hóa đơn</h3>
                                                    <hr class="my-4">

                                                    <c:forEach items="${cart.items}" var="ite">

                                                        <c:set var="t" value="${t+1}"/>
                                                        <div class="d-flex justify-content-between mb-4">
                                                            <h5 class="">Sản phẩm ${t}</h5>
                                                            <h5 class="product-price">${ite.product.getNewPrice() * ite.quantity}</h5>
                                                        </div>

                                                    </c:forEach> 
                                                    <hr class="my-4">
                                                    <c:if test="${size > 0}" >
                                                        <div class="d-flex justify-content-between mb-5">
                                                            <h5 class="text-uppercase">Tổng tiền</h5>
                                                            <h5 class="product-price">${cart.getTotalMoney()}</h5>
                                                        </div>

                                               
                                                        <button type="submit" class="btn btn-dark btn-block btn-lg checkout" data-mdb-ripple-color="dark">Thanh toán</button>
                                                    
                                                    </c:if>
                                                </c:if>

                                                <c:if test="${sessionScope.account ne null}">

                                                    <c:forEach var="cart" items="${requestScope.listCarts}"> 
                                                        <c:if test="${cart.customerId == sessionScope.account.customerId}">
                                                            <c:set var="size" value="${cart.items.size()}" /> 
                                                            <c:if test="${size > 0}">
                                                                <h6 class="mb-0 text-muted">${size} sản phẩm</h6>
                                                            </c:if>
                                                        </div>
                                                        <c:set var="cartItem" value="${cart}" /> 
                                                        <c:forEach items="${cartItem.items}" var="i">
                                                            <hr class="my-4">

                                                            <div class="row mb-4 d-flex justify-content-between align-items-center">
                                                                <div class="col-md-2 col-lg-2 col-xl-2">
                                                                    <img
                                                                        src="${i.product.img}"
                                                                        class="img-fluid rounded-3"
                                                                        alt=""
                                                                        >
                                                                </div>
                                                                <div class="col-md-3 col-lg-3 col-xl-3">
                                                                 
                                                                    <h6 class="text-black mb-0">${i.product.productName}</h6>
                                                                </div>
                                                                <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                                    <button class="btn btn-link px-2"
                                                                            >
                                                                        <a href="process?num=-1&id=${i.product.productId}&cart=${cartItem.cartId}"> <i class="fas fa-minus"></i></a>
                                                                    </button>

                                                                    <div id="form1"  name="quantity"
                                                                         class="form-control form-control-sm">${i.quantity} </div>

                                                                    <button class="btn btn-link px-2"
                                                                            >
                                                                        <a href="process?num=1&id=${i.product.productId}&cart=${cartItem.cartId}"><i class="fas fa-plus"></i></a>
                                                                    </button>

                                                                </div>
                                                                <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                                    <h6 class="mb-0 product-price">${i.product.getPrice()}</h6>
                                                                </div>
                                                                <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                                    <form action="process" method="post">
                                                                        <input type="hidden" name="id" value="${i.product.productId}" />
                                                                        <button style="border: none; background-color: #FFF" type="submit" class="text-muted">
                                                                            <i class="fas fa-times"></i>
                                                                        </button>
                                                                    </form>

                                                                </div>
                                                            </div>

                                                        </c:forEach>


                                                        <hr class="my-4">

                                                        <div class="pt-5">
                                                            <h6 class="mb-0"><a href="home" class="text-body"><i
                                                                        class="fas fa-long-arrow-alt-left me-2"></i>Tiếp tục mua hàng</a></h6>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 bg-grey">
                                                    <div class="p-5">
                                                        <h3 class="fw-bold mb-5 mt-2 pt-1">Hóa đơn</h3>
                                                        <hr class="my-4">

                                                        <c:forEach items="${cart.items}" var="ite">
                                                            <c:set var="t" value="${t+1}"/>
                                                            <div class="d-flex justify-content-between mb-4">
                                                                <h5 class="">Sản phẩm ${t}</h5>
                                                                <h5 class="product-price">${ite.product.getPrice()}</h5>
                                                            </div>
                                                        </c:forEach> 
                                                        <%
                                                            Account a = (Account)session.getAttribute("account");
                                                            Customer customer = CustomerDAO.INSTANCE.getCustomerById(a.getCustomerId());
                                                        %>

                                                        <h5 class="">Thông tin khách hàng</h5>
                                                        <h6>Họ và tên: <%= customer.getFullName()%> </h6>
                                                        <h6>Số điện thoại: <%= customer.getPhone()%> </h6>
                                                        <h6>Địa chỉ giao hàng: <%= customer.getAddress()%> </h6>


                                                        <hr class="my-4">
                                                        <c:if test="${size > 0}" >
                                                            <div class="d-flex justify-content-between mb-5">
                                                                <h5 class="text-uppercase">Tổng tiền</h5>
                                                                <h5 class="product-price">${cart.getTotalMoneyDB()}</h5>
                                                            </div>

                                                            <form action="pay" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn thanh toán?')">
                                                                <button type="submit" class="btn btn-dark btn-block btn-lg " data-mdb-ripple-color="dark">Thanh toán</button>
                                                            </form>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach> 
                                            </c:if>   


                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </section>

    <div class="modal-add--container">
        <div class="modal-add">
            <div class="modal-add--overlay">
            </div>
            <div class="modal-add--body">
                <div class="card-checkout">
                    <div class="card-header">
                        <span class="add-close-btn">×</span>
                        <div class="text-header">Thông tin thanh toán</div>
                    </div>
                    <div class="card-body">
                        <form action="pay" method="post">
                            <div class="form-group">
                                <label for="firstName">Họ</label>
                                <input required="" class="form-control" name="firstName" id="firstName" type="text">
                            </div>
                            <div class="form-group">
                                <label for="lastName">Tên:</label>
                                <input required="" class="form-control" name="lastName" id="lastName" type="text">
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input required="" class="form-control" name="email" id="email" type="email">
                            </div>
                            <div class="form-group">
                                <label for="phone">Số điện thoại:</label>
                                <input required="" class="form-control" name="phone" id="phone" type="tel" pattern="^0\d{9,10}$">
                            </div>
                            <div class="form-group">
                                <label for="address">Địa chỉ:</label>
                                <input required="" class="form-control" name="address" id="address" type="text">
                            </div>
                            <label class="form-group" >
                                <select required="" class="gender-select" name="gender">
                                    <option value="" disabled selected>Giới tính</option>
                                    <option value="Nam">Nam</option>
                                    <option value="Nữ">Nữ</option>
                                    <option value="Khác">Khác</option>
                                </select>
                            </label>
                            <input type="submit" class="btn-checkout" value="Thanh toán">    </form>
                    </div>
                </div>


            </div>

        </div>
    </div>

</div>

<div>
    <jsp:include page="footer.jsp" />
</div>

<script>

    const closeBtn = document.querySelector('.add-close-btn');
    const modalAdd = document.querySelector('.modal-add--container');
    const checkoutBtn = document.querySelector('.checkout');

    function formatCurrency(price) {
        return price.toFixed(0).replace(/\d(?=(\d{3})+$)/g, '$&,');
    }

    // Sử dụng hàm formatCurrency để định dạng giá sản phẩm
    document.querySelectorAll('.product-price').forEach(element => {
        // Lấy giá trị hiện tại
        let price = parseFloat(element.textContent);
        // Định dạng lại giá trị
        let formattedPrice = formatCurrency(price);
        // Gán giá trị mới cho phần tử
        element.textContent = formattedPrice + 'đ';
    });


    closeBtn.addEventListener('click', () => {
        modalAdd.style.display = 'none';
    });

    checkoutBtn.addEventListener('click', () => {
        modalAdd.style.display = 'block';
    });

</script>

</body>
</html>
