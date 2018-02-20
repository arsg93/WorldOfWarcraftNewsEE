<%-- 
    Document   : new
    Created on : 12-feb-2018, 12:36:36
    Author     : admin
--%>

<%@page import="org.jsoup.Jsoup"%>
<%@page import="java.util.List"%>
<%@page import="com.wownews.entities.News"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    News not = (News) request.getAttribute("New");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title><%=not.getTitle()%></title>
        <meta property="og:site_name" content="News World of Warcraft">
        <meta property="og:url" content="http://news1.news80103.private.jelastic.cloud/WorldOfWarcraftNewsEE-war/noticias/<%=not.getSlug()%>">
        <meta property="og:title" content="<%=not.getTitle()%>">
        <meta property="og:image" content="http://news1.news80103.private.jelastic.cloud/WorldOfWarcraftNewsEE-war/res/img/noticias/imgBig/<%=not.getId()%>.png">
        <meta property="og:description" content="<%=changeDescription(not)%>">
        <meta name="description" content="<%=changeDescription(not)%>">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel='stylesheet' href='../css/style_noticia.css'>
        <link rel="icon" type="image/png" href="http://news1.news80103.private.jelastic.cloud/WorldOfWarcraftNewsEE-war/img/icon.png" />
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src='../js/js_noticia.js'></script>
    </head>

    <body>
        <nav class="navbar navbar-default navbar-inverse navbar-fixed-top fondo" role="navigation">
            <div class="container-fluid">

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>


                    <a class="navbar-brand" href="../"><img id="logo" class="img-responsive" src="../img/logo.png" alt="logo img"></a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="../">Inicio</a></li>
                        <li><a href="#">Parches</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Clases <span class="caret"></span></a>
                            <ul class="dropdown-menu menu fondo" role="menu">
                                <li class="blanco"><a href="#">Guerrero</a></li>
                                <li class="blanco"><a href="#">Paladín</a></li>
                                <li class="blanco"><a href="#">Cazador</a></li>
                                <li class="blanco"><a href="#">Pícaro</a></li>
                                <li class="blanco"><a href="#">Sacerdote</a></li>
                                <li class="blanco"><a href="#">Brujo</a></li>
                                <li class="blanco"><a href="#">Druida</a></li>
                            </ul>
                        </li>
                        <li class="active"><a href="../../WorldOfWarcraftNewsEEAdmin-war">Modo Desarrollo</a></li>
                    </ul>
                </div>

            </div>

        </nav>

        <div class="publicidadver">
            <img class="img-responsive center-block" src="../img/publi_vert.jpg" alt="..." id="publiver"></div>

        <div id="noticias">
            <div class="container-fluid">
                <div class="row noticias">
                    <div class="col-sm-12">
                        <div class="thumbnail">
                            <div class="caption">
                                <h1 class="text-right"><%=not.getTitle()%></h1>
                                <p class="text-right"><em><%=not.getDate()%></em></p>
                                <p class="text-right"><em><%=not.getUsername()%></em></p>
                            </div>
                            <img src="../res/img/noticias/imgBig/<%=not.getId()%>.png" alt="...">
                            <div class="caption">
                                <%=not.getDescription()%>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="publicidadhor"> <img src="../img/publi.jpg" alt="..." id="publihor"></div>

        <div class="twitter"><a class="twitter-timeline" data-width="300" data-height="615" href="https://twitter.com/Warcraft_ES">Tweets by Warcraft_ES</a></div>
        <div class="footer fondo">
            <h4 class="seguirtitulo">Compartir Noticia</h4>
            <ul class="list-inline">
                <li class="seguirlink">
                    <img class="img-responsive center-block" src="../img/facebook.png" alt="facebook logo" id="compface"/>
                </li>
                <li class="seguirlink">
                    <img class="img-responsive center-block" src="../img/twitter.png" alt="twitter logo" id="comptw"/>
                </li>
            </ul>
        </div>
    </body>

</html>


<%!
    private String changeDescription(News news) {

        String desc = html2text(news.getDescription()).substring(0, 150);
        if (desc.lastIndexOf(' ') != -1) {
            desc = desc.substring(0, desc.lastIndexOf(' ')) + "...";
        }

        return desc;
    }

    private String html2text(String html) {
        return Jsoup.parse(html).text();
    }

%>