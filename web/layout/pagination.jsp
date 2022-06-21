<%-- 
    Document   : pagination
    Created on : Jun 21, 2022, 1:17:31 AM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <nav aria-label="Page navigation example " style="margin-top: 20px" class="d-flex justify-content-center" id="pagination-container">
            <ul class="pagination">
                <c:if test="${not empty sort}">
                    <li class="page-item">
                        <a class="page-link bg-dark text-white" href="products?category=${category}&page=${prevPage}&sort=${sort}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach begin="${1}" end="${totalPage}" var="i">
                        <c:if test = "${i == pageCurrent}">
                            <li class="page-item active"><a class="page-link bg-dark text-white" href="products?category=${category}&page=${i}&sort=${sort}">${i}</a></li>
                            </c:if>
                            <c:if test = "${i != pageCurrent}">
                            <li class="page-item"><a class="page-link bg-dark text-white" href="products?category=${category}&page=${i}&sort=${sort}">${i}</a></li>
                            </c:if>
                        </c:forEach>
                    <li class="page-item">
                        <a class="page-link bg-dark text-white" href="products?category=${category}&page=${nextPage}&sort=${sort}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </c:if>
            <c:if test="${empty sort}">
                <li class="page-item">
                    <a class="page-link bg-dark text-white" href="products?category=${category}&page=${prevPage}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="${1}" end="${totalPage}" var="i">
                    <c:if test = "${i == pageCurrent}">
                        <li class="page-item active"><a class="page-link bg-dark text-white" href="products?category=${category}&page=${i}">${i}</a></li>
                        </c:if>
                        <c:if test = "${i != pageCurrent}">
                        <li class="page-item"><a class="page-link bg-dark text-white" href="products?category=${category}&page=${i}">${i}</a></li>
                        </c:if>
                    </c:forEach>
                <li class="page-item">
                    <a class="page-link bg-dark text-white" href="products?category=${category}&page=${nextPage}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </c:if>
    </nav>      
</body>
</html>
