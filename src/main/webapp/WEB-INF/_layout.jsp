<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String pageBody = (String) request.getAttribute("page-body");
    if( pageBody == null){
        pageBody = "home";
    }
    String bodyFile = "/WEB-INF/" + pageBody + ".jsp" ;

    String context = request.getContextPath() ;
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>

    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="<%=context%>/css/site.css">
</head>
<body>
<header>
    <nav>
        <div class="nav-wrapper green darken-2">
            <a href="<%= context%>/" class="brand-logo left">Logo</a>
            <ul id="nav-mobile" class="right">
                <li><a href="<%= context%>/cart"><i class="material-icons prefix">shopping_cart</i></a></li>
                <li><a href="<%= context%>/prop"><img class="saleImg" src="<%=context%>/img/sale.png" alt="sale"></a></li>
                <li><a href="<%= context%>/signup"><i class="material-icons">person_add</i></a></li>
                <li><a href="<%= context%>/item"><i class="material-icons">note_add</i></a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="container">
        <jsp:include page="<%= bodyFile %>"/>
    </div>
</main>


<footer class="page-footer green darken-2">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Footer Content</h5>
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2024 Copyright Text
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
        </div>
    </div>
</footer>
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="<%=context%>/js/site.js"></script>
    <script src="<%=context%>/js/newItem.js"></script>
</body>
</html>
