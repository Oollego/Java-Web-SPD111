<%@ page import="step.learning.dal.dto.PropItem" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    String context = request.getContextPath() ;

    PropItem[] propItems = (PropItem[]) request.getAttribute("Prop");
%>

<div class="row">
<% for(PropItem item : propItems) {%>
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