<%-- 
    Document   : pagination
    Created on : Jun 21, 2022, 1:17:31 AM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
