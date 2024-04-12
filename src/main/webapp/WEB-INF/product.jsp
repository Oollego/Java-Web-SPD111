<%@ page import="step.learning.dal.dto.ProductItem" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    String context = request.getContextPath() ;

    ProductItem[] propItems = (ProductItem[]) request.getAttribute("Prop");
%>

<div class="row">
<% for(ProductItem item : propItems) {%>
<div class="col s4 m4">
    <div class="card">
        <div class="card-image">
            <img class="prop_img" src="<%=context%>/img/<%=item.getImgPath()%>">
        </div>
        <div class="card-stacked">
            <div class="card-content">
                <p class="mar_but_10"><%=item.getDescription()%></p>
                <p class="text_cross"><%=item.getPrice()%>  $</p>
                <p class="red-text"><%=item.getPropPrice()%>  $</p>
            </div>
        </div>
    </div>
</div>
<%}%>
</div>