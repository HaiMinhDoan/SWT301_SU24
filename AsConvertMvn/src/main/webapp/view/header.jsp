<%-- 
    Document   : header
    Created on : Feb 22, 2024, 10:29:21 AM
    Author     : tranm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.asconvertmvn.model.Account"%>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="../css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
              integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>

        <style>
            .header_search--input {
                position: relative;
                display: flex;
                align-items: center;

            }

            .header_search--input input[type="text"] {
                padding: 10px;
                border-radius: 20px;
                border: 1px solid #ccc;
                font-size: 16px;
                outline: none;
                width: 500px;
            }

            .header_search--input button {
                background-color: #f1f1f1;
                border: none;
                padding: 10px;
                border-radius: 0 20px 20px 0;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .header_search--input button:hover {
                background-color: #ddd;
            }

            .header_search--input button i {
                color: #555;
            }



            /* ADMIN Header  */

            .container-header--admin {
                width: 80%;
                margin: 0 auto;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            /* Header styles */
            .header-admin {
                background-color: #007bff; /* Màu xanh dương */
                color: #fff;
                padding: 20px 0;
            }

            .header__title {
                margin: 0;
            }

            .header__nav {
                display: flex;
            }

            .header__menu {
                list-style-type: none;
                margin: 0;
                padding: 0;
                display: flex;
            }

            .header__menu-item {
                margin-left: 20px;
            }

            .header__menu-link {
                color: #fff;
                text-decoration: none;
            }


        </style>
    </head>

    <body>

        <header id="header">
            <%
                        Account a = (Account)session.getAttribute("account");
                        if(a == null || (a != null && a.getRole() != 0))  {
            %>

            <div class="header_container">
                <div class="header_logo">
                    <a class="header_logo--link" href="home">
                        <img class="header_logo--image" src="/store/images/main-logo.png" alt="">
                    </a>
                </div>

                <div class="header_search--input">
                    <form action="search">
                        <input type="text" name="search" placeholder="Tìm kiếm...">
                        <button type="submit"><i class="fas fa-search"></i></button>
                    </form>
                </div>




                <div class="header-left">
                    <div class="header_nav">
                        <ul class="header_nav--list">
                            <li class="header_nav--item">
                                <a class="nav_item--link" href="home">Trang chủ</a>
                            </li>
                            <li class="header_nav--item">
                                <a class="nav_item--link" href="">Dịch vụ</a>
                            </li>
                            <li class="header_nav--item">
                                <a class="nav_item--link" href="home">Sản phẩm</a>
                            </li>

                            <li class="header_nav--item">
                                <a class="nav_item--link" href="home">Giảm giá</a>
                            </li>

                        </ul>
                    </div>

                    <%
                        if(a == null)  {
                    %>
                    <div class="header_user">
                        <a class="user" href="#" id="userDropdownBtn">
                            <i class="fa-solid fa-user"></i>
                        </a>
                        <div class="dropdown-content" id="userDropdown">
                            <a href="register">Đăng ký</a>
                            <a href="login">Đăng nhập</a>
                        </div>
                        <a class="cart" href="cart">
                            <i class="fa-solid fa-cart-shopping"></i>
                        </a>
                    </div>

                    <%
                        
                        } else if(a.getRole() == 1){
                    %>
                    <div class="header_user">
                        <a class="user" href="#" id="userDropdownBtn">
                            <img width="30px" height="30px"
                                 src="/store/images/user.png"
                                 alt=""
                                 />
                            <span><%= a.getUserName()%></span>
                        </a>
                        <div class="dropdown-content" id="userDropdown">
                            <a href="infoUser?user=<%= a.getCustomerId()%>">Thông tin tài khoản</a>
                            <a href="logout">Đăng xuất</a>
                        </div>
                        <a class="cart" href="cart">
                            <i class="fa-solid fa-cart-shopping"></i>
                        </a>
                    </div>


                    <%
                       } 
                    } else if(a.getRole() == 0){

                    %>
                    <header class="header-admin">
                        <div class="container-header--admin">
                            <a href="adminHome" style="text-decoration: none; color: #fff"><h1 class="header__title">Admin Dashboard</h1></a>
                            <nav class="header__nav">
                                <ul class="header__menu">
                                    <li class="header__menu-item"><a href="adminHome" class="header__menu-link">Trang chủ</a></li>
                                    <li class="header__menu-item"><a href="home" class="header__menu-link">Người dùng</a></li>
                                    <li class="header__menu-item"><a href="changePassAdmin?admin=<%= a.getUserName() %>" class="header__menu-link">Đổi mật khẩu</a></li>
                                    <li class="header__menu-item"><a href="createAdmin" class="header__menu-link">Thêm quản trị viên</a></li>
                                    <li class="header__menu-item">
                                        <img width="30px" height="30px"
                                             src="/store/images/admin.png"
                                             alt=""
                                             />
                                        <span><%= a.getUserName()%></span>
                                    </li>

                                    <li class="header__menu-item"><a href="logout" class="header__menu-link">Logout</a></li>
                                </ul>
                            </nav>
                        </div>
                    </header>

                    <%
                      } 
                    %>

                </div>


            </div>
        </header>

        <script>

            document.addEventListener('DOMContentLoaded', function () {
                var dropdownBtn = document.getElementById('userDropdownBtn');
                var dropdown = document.getElementById('userDropdown');

                // Bắt sự kiện click vào biểu tượng người dùng
                dropdownBtn.addEventListener('click', function (event) {
                    // Ngăn chặn hành vi mặc định của thẻ a
                    event.preventDefault();
                    // Đảo ngược trạng thái hiển thị của menu dropdown
                    if (dropdown.style.display === 'block') {
                        dropdown.style.display = 'none';
                    } else {
                        dropdown.style.display = 'block';
                    }
                    // Ngăn sự kiện click lan rộng để không gây hiện tượng đóng ngay sau khi mở
                    event.stopPropagation();
                });

                // Bắt sự kiện click ra ngoài để đóng menu dropdown
                document.addEventListener('click', function (event) {
                    if (event.target !== dropdownBtn && !dropdown.contains(event.target)) {
                        dropdown.style.display = 'none';
                    }
                });
            });

        </script>
    </body>

</html>