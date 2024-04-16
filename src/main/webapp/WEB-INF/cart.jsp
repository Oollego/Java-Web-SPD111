<%@ page import="step.learning.dal.dto.CartItem" %>
<%@ page import="step.learning.models.CartPageModel" %>
<%@ page import="step.learning.dal.dto.ProductItem" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String context = request.getContextPath() ;
    // Вилучаємо дані, передані сервлетом (контролером)
    CartPageModel model = (CartPageModel) request.getAttribute("model");
%>
<div class="row">
    <div class="col s8">

        <div class="row">
            <% for(ProductItem product: model.getProducts() ){ %>
            <div class="col s3 m4">
                <div class="card">
                    <div class="card-image">
                        <img src="<%=context%>/img/products/<%=product.getImgPath() == null ? "no_image.jpg" : product.getImgPath()%>">
                        <span class="card-title black-text"><%=product.getName()%></span>
                        <a data-product="<%=product.getId()%>" class="product-cart-btn btn-floating halfway-fab waves-effect waves-light red"><i class="material-icons">shopping_cart</i></a>
                    </div>
                    <div class="card-content">
                        <p><%=product.getDescription()%></p>
                    </div>
                </div>
            </div>
            <% } %>
        </div>


    </div>
    <div class="col s4">
        <h1>Ваш кошик</h1>
        <%-- Відображаємо дані --%>
        <% for(CartItem item : model.getCartItems()) { %>
        <div class="col s12 m7">
            <div class="card horizontal">
                <div class="card-image flex1">
                    <img src="<%=context%>/img/products/no_image.jpg" alt="img" />
                </div>
                <div class="card-stacked flex3">
                    <div class="card-content">
                        <p><%= item.getProductId() %></p>
                        <p><%= item.getCount() %></p>
                    </div>
                    <div class="card-action">
                        <a href="#">видалити з кошику</a>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>


