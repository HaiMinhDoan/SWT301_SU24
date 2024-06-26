<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.asconvertmvn.model.Product"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

        <link rel="stylesheet" href="/store/css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            .category-item:hover {
                cursor: pointer;

            }
            .home-filter__btn:hover{
                color :#000;
            }
            /* Modal style */
            .modal-product {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.5);
            }

            /* Modal content */
            .modal-product-content {
                background-color: #fefefe;
                margin: 10% auto;
                padding: 10px; /* Điều chỉnh khoảng cách giữa nội dung và viền */
                border: 1px solid #888;
                width: 30%; /* Điều chỉnh chiều rộng của modal */
                border-radius: 5px;
            }
            .product-details {
                max-width: 100%; /* Giữ cho chi tiết sản phẩm không vượt quá kích thước của modal */
            }

            /* Close button */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .home-product-des{
                color: #000;
                display: none;
            }


        </style>

    </head>
    <body>
        <div>     
            <jsp:include page="header.jsp" />        
        </div>


        <div id="body">   

            <c:if test="${sessionScope.messAddCart != null}">
                <div class="toast-add--container">
                    <div style="color: red;   border-color: red;" class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messAddCart}</p>
                        </div>    
                    </div>
                </div>
                <c:remove var="messAddCart" scope="session" />
                <c:set var="isToastShown" value="true" scope="session" />
            </c:if>

            <c:set var="session" value="${pageContext.session}" />
            <c:set var="paymentSuccess" value="${sessionScope.paymentSuccess}" />
            <c:if test="${not empty paymentSuccess}">
                <div id="paymentSuccessAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                    Thanh toán thành công!
                </div>
                <c:remove var="paymentSuccess" scope="session" />
            </c:if>        
            <c:if test="${sessionScope.messChange != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.messChange}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="messChange" scope="session" />
            </c:if>

            <c:if test="${sessionScope.message_update != null}">
                <div class="toast-add--container">
                    <div class="toast-add">
                        <div class="toast-icon">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="toast-body">
                            <p class="toast-mess">${sessionScope.message_update}</p>
                        </div>
                        <div class="toast-close">
                            <i class="fa-solid fa-xmark"></i>
                        </div>
                    </div>
                </div>
                <c:remove var="message_update" scope="session" />
            </c:if>
            <div class="container">
                <div class="row">
                    <div class="col-md-2">
                        <nav class="category">
                            <h3 class="category__heading">
                                <i class="category__heading-icon fa-solid fa-list"></i>
                                Danh Mục
                            </h3>
                            <ul class="category-list">
                                <c:forEach items="${requestScope.listCategory}" var="category">

                                    <li class="category-item ${category.categoryId == requestScope.activeCategoryId ? 'category-item__active' : ''}">
                                        <button style="outline: none; background-color: transparent; border: none" class="category-item__link" onclick="searchCategory(${category.categoryId})">
                                            ${category.categoryName}
                                        </button>
                                    </li>
                                </c:forEach>


                            </ul>
                        </nav>
                    </div>
                    <div class="col-md-10">
                        <div class="home-filter hide-on-mobile-tablet">
                            <span class="home-filter__label">Sắp xếp theo</span>          
                            <a class="home-filter__btn ${requestScope.activeFilter == 1 ? "btn--primary" : ''}" href="filter?choose=1">Mới nhất</a>
                            <a class="home-filter__btn ${requestScope.activeFilter == 2 ? "btn--primary" : ''}" href="filter?choose=2">Bán chạy</a>
                            <div class="select-input">
                                <span class="select-input__label">Giá</span>
                                <i class="select-input__icon fa-solid fa-angle-down"></i>
                                <ul class="select-input__list">
                                    <li class="select-input__item">
                                        <a href="filter?choose=3" class="select-input__link">Giá thấp đến cao</a>
                                    </li>

                                    <li class="select-input__item">
                                        <a href="filter?choose=4" class="select-input__link">Giá cao đến thấp</a>
                                    </li>
                                </ul>
                            </div>

                        </div>

                        <div class="home-product">
                            <div class="row">
                                <c:if test="${requestScope.listProductByCate != null}">
                                    <c:forEach var="product" items="${requestScope.listProductByCate}">
                                        <c:if test="${product.quantity > 0}">
                                            <div class="col-md-3">

                                                <div class="home-product-item">
                                                    <div class="home-product-item__img"
                                                         style="background-image: url(${product.img});">
                                                    </div>
                                                    <h4 class="home-product-item__name">${product.productName}</h4>
                                                    <p class="home-product-des"> ${product.description} </p>
                                                    <div class="home-product-item__price">
                                                        <c:choose>
                                                            <c:when test="${product.sale > 0}">
                                                                <span class="home-product-item__price-old">${product.price}</span>
                                                                <span class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                    <div class="home-product-item__action">


                                                        <form name="cartForm" action="" method="post"> 
                                                            <button class="add-to-cart-button" type="button" onclick="buy('${product.productId}')">
                                                                <i class="fa-solid fa-shopping-cart"></i>
                                                            </button>                                                                       
                                                        </form>


                                                    </div>

                                                    <div class="home-product-item__origin">
                                                        <span class="home-product-item__brand">${(product.category).categoryName}</span>
                                                        <span class="home-product-item__origin-name">Việt Nam</span>
                                                    </div>

                                                    <div class="home-product-item__sale">
                                                        <span class="home-product-item__sale-percent">${product.getPercentSale()}%</span>
                                                        <span class="home-product-item__sale-label">Giảm</span>
                                                    </div>
                                                </div>

                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${requestScope.listProductSearch != null}">
                                    <c:forEach var="product" items="${requestScope.listProductSearch}">
                                        <c:if test="${product.quantity > 0}">
                                            <div class="col-md-3">
                                                <div href="" class="home-product-item">
                                                    <div class="home-product-item__img"
                                                         style="background-image: url(${product.img});">
                                                    </div>
                                                    <h4 class="home-product-item__name">${product.productName}</h4>
                                                    <p class="home-product-des"> ${product.description} </p>
                                                    <div class="home-product-item__price">
                                                        <c:choose>
                                                            <c:when test="${product.sale > 0}">
                                                                <span class="home-product-item__price-old">${product.price}</span>
                                                                <span class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                    <div class="home-product-item__action">


                                                        <form name="cartForm" action="" method="post"> 
                                                            <c:if test="${sessionScope.account eq null}">
                                                                <button class="add-to-cart-button" type="button" onclick="buy('${product.productId}')">
                                                                    <i class="fa-solid fa-shopping-cart"></i>
                                                                </button>
                                                            </c:if>

                                                            <c:if test="${sessionScope.account ne null}">
                                                                <button class="add-to-cart-button" type="button" onclick="buy('${product.productId}', '${sessionScope.account.customerId}')">
                                                                    <i class="fa-solid fa-shopping-cart"></i>
                                                                </button>
                                                            </c:if>
                                                        </form>


                                                    </div>

                                                    <div class="home-product-item__origin">
                                                        <span class="home-product-item__brand">${(product.category).categoryName}</span>
                                                        <span class="home-product-item__origin-name">Việt Nam</span>
                                                    </div>

                                                    <div class="home-product-item__sale">
                                                        <span class="home-product-item__sale-percent">${product.getPercentSale()}%</span>
                                                        <span class="home-product-item__sale-label">Giảm</span>
                                                    </div>
                                                </div>

                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${requestScope.productFilter != null}">
                                    <c:forEach var="product" items="${requestScope.productFilter}">
                                        <c:if test="${product.quantity > 0}">
                                            <div class="col-md-3">
                                                <div href="" class="home-product-item">
                                                    <div class="home-product-item__img"
                                                         style="background-image: url(${product.img});">
                                                    </div>
                                                    <h4 class="home-product-item__name">${product.productName}</h4>
                                                    <p class="home-product-des"> ${product.description} </p>
                                                    <div class="home-product-item__price">
                                                        <c:choose>
                                                            <c:when test="${product.sale > 0}">
                                                                <span class="home-product-item__price-old">${product.price}</span>
                                                                <span class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                    <div class="home-product-item__action">


                                                        <form name="cartForm" action="" method="post"> 
                                                            <c:if test="${sessionScope.account eq null}">
                                                                <button class="add-to-cart-button" type="button" onclick="buy('${product.productId}')">
                                                                    <i class="fa-solid fa-shopping-cart"></i>
                                                                </button>
                                                            </c:if>

                                                            <c:if test="${sessionScope.account ne null}">
                                                                <button class="add-to-cart-button" type="button" onclick="buy('${product.productId}', '${sessionScope.account.customerId}')">
                                                                    <i class="fa-solid fa-shopping-cart"></i>
                                                                </button>
                                                            </c:if>                                                                  
                                                        </form>


                                                    </div>

                                                    <div class="home-product-item__origin">
                                                        <span class="home-product-item__brand">${(product.category).categoryName}</span>
                                                        <span class="home-product-item__origin-name">Việt Nam</span>
                                                    </div>

                                                    <div class="home-product-item__sale">
                                                        <span class="home-product-item__sale-percent">${product.getPercentSale()}%</span>
                                                        <span class="home-product-item__sale-label">Giảm</span>
                                                    </div>
                                                </div>

                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${requestScope.listProductByCate == null && requestScope.productFilter == null && requestScope.listProductSearch == null}">
                                    <c:forEach var="product" items="${requestScope.data}">
                                        <c:if test="${product.quantity > 0}">
                                            <div class="col-md-3">               
                                                <div  class="home-product-item">
                                                    <div class="home-product-item__img"
                                                         style="background-image: url(${product.img});">
                                                    </div>
                                                    <h4 class="home-product-item__name">${product.productName}</h4>
                                                    <p class="home-product-des"> ${product.description} </p>
                                                    <div class="home-product-item__price">
                                                        <c:choose>
                                                            <c:when test="${product.sale > 0}">
                                                                <span class="home-product-item__price-old">${product.price}</span>
                                                                <span class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="home-product-item__price-curr">${product.getNewPrice()}</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>

                                                    <div class="home-product-item__action">


                                                        <form name="cartForm" action="" method="post"> 
                                                            <c:if test="${sessionScope.account eq null}">
                                                                <button class="add-to-cart-button" type="button" onclick="buy('${product.productId}')">
                                                                    <i class="fa-solid fa-shopping-cart"></i>
                                                                </button>
                                                            </c:if>

                                                            <c:if test="${sessionScope.account ne null}">
                                                                <button class="add-to-cart-button" type="button" onclick="buy('${product.productId}', '${sessionScope.account.customerId}')">
                                                                    <i class="fa-solid fa-shopping-cart"></i>
                                                                </button>
                                                            </c:if>                                                                       
                                                        </form>
                                                    </div>

                                                    <div class="home-product-item__origin">
                                                        <span class="home-product-item__brand">${(product.category).categoryName}</span>
                                                        <span class="home-product-item__origin-name">Việt Nam</span>
                                                    </div>

                                                    <div class="home-product-item__sale">
                                                        <span class="home-product-item__sale-percent">${product.getPercentSale()}%</span>
                                                        <span class="home-product-item__sale-label">Giảm</span>
                                                    </div>

                                                </div>


                                            </div>
                                        </c:if>
                                    </c:forEach>

                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="pagination">
                <a href="#" class="prev">&laquo; Trước</a>
                <a href="#" class="page">1</a>
                <a href="#" class="page">2</a>
                <a href="#" class="page">3</a>
                <!-- Add more page links as needed -->
                <a href="#" class="next">Sau &raquo;</a>
            </div>
            <!-- Modal -->
            <div id="productModal" class="modal-product">
                <div class="modal-product-content">
                    <span class="close">&times;</span>
                    <div id="productDetails" class="product-details"></div>
                </div>
            </div>


        </div>  



        <div>
            <jsp:include page="footer.jsp" />
        </div>



        <script type="text/javascript">
            const categoryItems = document.querySelectorAll('.category-item');
            const homeFilterBtns = document.querySelectorAll('.home-filter__btn');
//            const productDetails = document.querySelectorAll('.home-product-item');
            //            categoryItems.forEach(item => {
            //                item.addEventListener('click', () => {
            //                    categoryItems.forEach(item => {
            //                        item.classList.remove('category-item__active');
            //                    });
            //                    item.classList.add('category-item__active');
            //                });
            //            });

//
//            productDetails.forEach(item => {
//                item.addEventListener('click', () => {
//                    console.log(item);
//                });
//            });






            function searchCategory(categoryId) {
                // Thay đổi URL trên trình duyệt bằng cách thêm tham số query categoryId
                window.location.href = "searchCate?id=" + categoryId;
            }



            homeFilterBtns.forEach(btn => {
                btn.addEventListener('click', function () {
                    homeFilterBtns.forEach(btn => {
                        btn.classList.remove('btn--primary');
                    });
                    this.classList.add('btn--primary');
                });
            });

            // Hàm chuyển đổi số thành chuỗi định dạng tiền tệ xx.xxx.xxx
            function formatCurrency(price) {
                return price.toFixed(0).replace(/\d(?=(\d{3})+$)/g, '$&,');
            }

            // Sử dụng hàm formatCurrency để định dạng giá sản phẩm
            document.querySelectorAll('.home-product-item__price-old').forEach(element => {
                // Lấy giá trị hiện tại
                let price = parseFloat(element.textContent);
                // Định dạng lại giá trị
                let formattedPrice = formatCurrency(price);
                // Gán giá trị mới cho phần tử
                element.textContent = formattedPrice + 'đ';
            });

            document.querySelectorAll('.home-product-item__price-curr').forEach(element => {
                // Lấy giá trị hiện tại
                let price = parseFloat(element.textContent);
                // Định dạng lại giá trị
                let formattedPrice = formatCurrency(price);
                // Gán giá trị mới cho phần tử
                element.textContent = formattedPrice + 'đ';
            });


            document.addEventListener('DOMContentLoaded', function () {
                const prevBtn = document.querySelector('.prev');
                const nextBtn = document.querySelector('.next');
                const pages = document.querySelectorAll('.page');

                let currentPage = 1;

                function showPage(pageNum) {
                    // Hide all product items
                    const productItems = document.querySelectorAll('.home-product-item');
                    productItems.forEach(item => {
                        item.style.display = 'none';
                    });

                    // Calculate starting and ending indexes of products to display
                    const startIndex = (pageNum - 1) * 20;
                    const endIndex = pageNum * 20;

                    // Show products for the current page
                    for (let i = startIndex; i < endIndex; i++) {
                        if (productItems[i]) {
                            productItems[i].style.display = 'block';
                        }
                    }

                    // Update active page link
                    pages.forEach(page => {
                        page.classList.remove('active');
                    });
                    pages[pageNum - 1].classList.add('active');
                }

                // Show initial page
                showPage(currentPage);

                // Previous button functionality
                prevBtn.addEventListener('click', function () {
                    if (currentPage > 1) {
                        currentPage--;
                        showPage(currentPage);
                    }
                });

                // Next button functionality
                nextBtn.addEventListener('click', function () {
                    if (currentPage < pages.length) {
                        currentPage++;
                        showPage(currentPage);
                    }
                });

                // Page link functionality
                pages.forEach((page, index) => {
                    page.addEventListener('click', function () {
                        currentPage = index + 1;
                        showPage(currentPage);
                    });
                });
            });

            function handleAddToCart(id, cusId) {
                const cartForm = document.querySelector('form[name="cartForm"]');
                if (cartForm) {
                    cartForm.action = "buy?id=" + id + "&num=1" + "&cusId=" + cusId;
                    cartForm.submit();
                } else {
                    console.error("Không tìm thấy form có tên 'cartForm'");
                }
            }

            function buy(id, cusId) {
                const toastShown = document.querySelector('.toast-add--container');
                if (!toastShown) {
                    handleAddToCart(id, cusId);
                    alert("Thêm vào giỏ hàng thành công");
                } else {
                    handleAddToCart(id, cusId);
                }
            }


            setTimeout(function () {
                document.getElementById("paymentSuccessAlert").style.display = "none";
            }, 4000);

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



            // Get the modal
            var modal = document.getElementById("productModal");

// Get the <span> element that closes the modal
            var closeBtn = document.getElementsByClassName("close")[0];

// Get all product items
            var productItems = document.querySelectorAll('.home-product-item');

// Loop through all product items and attach click event
            productItems.forEach(item => {
                item.addEventListener('click', function () {
                    modal.style.display = "block";

                    // Get the product details
                    var productDetails = item.innerHTML; // Or get details from specific elements inside item
                    document.getElementById("productDetails").innerHTML = productDetails;

                    // Display .home-product-des in modal
                    var productDes = modal.querySelector('.home-product-des');
                    productDes.style.display = "block";


                });
            });

// When the user clicks on <span> (x), close the modal
            closeBtn.onclick = function () {
                modal.style.display = "none";
            }

// When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }


        </script>
    </body>
</html>
