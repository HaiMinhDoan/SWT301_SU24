<%-- 
    Document   : login
    Created on : Feb 23, 2024, 6:23:16 PM
    Author     : tranm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/store/css/main.css">
        <title>Login</title>
        <style>
            .link-dir{
                color: blue;
            }

        </style>
    </head>
    <body>
        <div> <%@include file="header.jsp" %> </div>
        <div id="login">
            <c:if test="${requestScope.message != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${requestScope.message}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>     
            </c:if>


            <div class="login-card">
                <div class="card-header">
                    <div class="log">Đăng nhập</div>
                </div>
                <div class="login-error"> 
                    <%
                        if(request.getAttribute("error") != null) {
                            String error = (String)request.getAttribute("error");
                    %> 
                    <p style="color: red"><%= error%></p>
                    <%
                     }
                
                    %>
                </div>



                <form action="login" method="post">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input required="" name="username" id="username" type="text" value="${requestScope.user}">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input required="" name="password" id="password" type="password" value="${requestScope.password}">
                    </div>
                    <div class="form-group">
                        <input value="Đăng nhập" type="submit">
                    </div>

                </form>

                <div>
                    <p style="color: #000">Bạn chưa có tài khoản? <a class="link-dir" href="register">Đăng ký ngay</a></p>
                </div>

            </div>


        </div>
        <div> <%@include file="footer.jsp" %> </div>


        <script>
            // Function to hide the toast after 3 seconds
            function hideToast() {
                const toast = document.querySelector('.toast-add');
                toast.style.animation = 'slideOutRight ease .3s';
                setTimeout(() => {
                    toast.style.display = 'none';
                }, 300); // 300 milliseconds for slideOutRight animation duration
            }

            // Add this line in your code where you display the toast
            setTimeout(hideToast, 3000); // 3000 milliseconds for 3 seconds delay

            setTimeout(function () {
                var toastContainer = document.querySelector('.toast-add--container');
                if (toastContainer) {
                    toastContainer.remove();
                }
            }, 3000); // 3000 milliseconds for 3 seconds delay



        </script>
    </body>
</html>
