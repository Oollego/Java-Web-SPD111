
<%@ page contentType="text/html;charset=UTF-8"

%>
<%
    String context = request.getContextPath() ;
%>

<div class="row">
    <div class="col s6">
        <form>
            <div class="col s12 ">
                <img id="boardImg" class="item_img" src="<%=context%>/img/no_image.jpg" alt="item_img">
            </div>
            <div class="col s12">
                <div class="img-box">
                    <img src="<%=context%>/img/no_image.jpg" alt="no_image.jpg">
                </div>
            </div>
            <div>
                <div class="col s12">
                    <label class="btn" for="image_uploads">Додати зображення</label>
                    <input type="file" id="image_uploads" name="image_uploads" accept=".jpg, .jpeg, .png, .webp" multiple />
                </div>
            </div>
            <div class="col s12">
                <div >
                    <h3>Опис</h3>

                     <div class="row">
                         <div class="input-field col s12">
                             <textarea id="textarea1" name="descrTextArea" class="materialize-textarea"></textarea>
                             <label for="textarea1">Опис</label>
                         </div>
<%--                         <div class="col s12">--%>
<%--                             <a id="descBtn" class="btn col">Додати опис</a>--%>
<%--                         </div>--%>
                     </div>
                </div>
            </div>
            <div class="row">
                <div class="col s12">
                    <h3>Характеристики</h3>
                </div>
                <div class="col s12 inputPadNull">
                    <div id="featureDiv" >
                        <div class="input-field col s6">
                            <input id="features_name_0" name="features_name_0" type="text">
                            <label for="features_name_0">Ім'я</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="feature_0" name="feature_0" type="text">
                            <label for="feature_0">Опис функції</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col s6">
                        <a id="addFeatureBtn"  class="btn">Додати</a>
                    </div>
                    <div class="col s6">
                        <a id="deleteFeatureBtn"  class="btn red disabled">Прибрати</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12">
                        <a id="newItemBtn"  class="btn">Відправити</a>
                    </div>
                </div>
            </div>

        </form>
    </div>
<%--    <div class="col s6">--%>

<%--        <div>--%>
<%--            <label class="btn" for="image_uploads">Додати зображення</label>--%>
<%--            <input type="file" id="image_uploads" name="image_uploads" accept=".jpg, .jpeg, .png, .webp" multiple />--%>
<%--        </div>--%>
<%--        <div class="row div-preview" id="preview">--%>

<%--        </div>--%>

<%--    </div>--%>

</div>

