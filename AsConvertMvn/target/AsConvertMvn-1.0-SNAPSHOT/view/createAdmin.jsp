<%-- 
    Document   : createAdmin
    Created on : Mar 2, 2024, 10:02:50 PM
    Author     : tranm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Admin</title>
        <link rel="stylesheet" href="/store/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>

        <style>

            #create-admin{
                margin-top: 150px;
            }

            .form {
                background-color: #fff;
                display: block;
                padding: 1rem;
                max-width: 350px;
                border-radius: 0.5rem;
                box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
            }

            .form-title {
                font-size: 1.25rem;
                line-height: 1.75rem;
                font-weight: 600;
                text-align: center;
                color: #000;
            }

            .input-container {
                position: relative;
            }

            .input-container input, .form button {
                outline: none;
                border: 1px solid #e5e7eb;
                margin: 8px 0;
            }

            .input-container input {
                background-color: #fff;
                padding: 1rem;
                padding-right: 3rem;
                font-size: 0.875rem;
                line-height: 1.25rem;
                width: 300px;
                border-radius: 0.5rem;
                box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
            }

            .submit {
                display: block;
                padding-top: 0.75rem;
                padding-bottom: 0.75rem;
                padding-left: 1.25rem;
                padding-right: 1.25rem;
                background-color: #4F46E5;
                color: #ffffff;
                font-size: 0.875rem;
                line-height: 1.25rem;
                font-weight: 500;
                width: 100%;
                border-radius: 0.5rem;
                text-transform: uppercase;
            }

            .signup-link {
                color: #6B7280;
                font-size: 0.875rem;
                line-height: 1.25rem;
                text-align: center;
            }

            .signup-link a {
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <div>
            <jsp:include page="header.jsp" />
        </div>

        <div id = "create-admin">

            <form class="form" action="createAdmin" method="post">
                <p class="form-title">Tạo tài khoản quản trị viên</p>
                <div class="input-container">
                    <span>
                        Tài khoản 
                    </span>
                    <input type="text" name="adminAccount" placeholder="Nhập tài khoản">

                </div>
                <div class="input-container">
                    <span>
                        Mật khẩu
                    </span>
                    <input type="password" class="password" placeholder="Nhập mật khẩu" name="password">
                </div>
                <div class="input-container">
                    <span>
                        Xác nhận mật khẩu
                    </span>
                    <input type="password" placeholder="Nhập lại mật khẩu" class="confirm-password" name="password">
                </div>
                <button type="submit" class="submit">
                    Sign in
                </button>


            </form>

        </div>

        <script>

            document.addEventListener("DOMContentLoaded", function () {
                // Get form element
                var form = document.querySelector('.form');

                // Add event listener to form submit
                form.addEventListener("submit", function (event) {
                    // Get the values of new password and confirm password fields
                    var newPassword = document.querySelector('.password').value;
                    var confirmPassword = document.querySelector('.confirm-password').value;
                    // Check if new password and confirm password match
                    if (newPassword !== confirmPassword) {
                        // Prevent form submission
                        event.preventDefault();
                        // Display an error message or handle it as per your UI requirement
                        alert("Mật khẩu mới và xác nhận mật khẩu không trùng khớp.");
                    }
                });
            });
        </script>
    </body>
</html>
