<%-- 
    Document   : news
    Created on : 11-feb-2018, 21:54:14
    Author     : admin
--%>

<%@page import="com.wownews.entities.News"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    List<News> list = (List<News>) request.getAttribute("News");


%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>News World of Warcraft</title>
        <meta property="og:site_name" content="News World of Warcraft">
        <meta property="og:url" content="http://news1.news80103.private.jelastic.cloud/WorldOfWarcraftNewsEE-war/">
        <meta property="og:title" content="News World of Warcraft">
        <meta property="og:image" content="http://news1.news80103.private.jelastic.cloud/WorldOfWarcraftNewsEE-war/img/icon.png">
        <meta property="og:description" content="Ultimas noticias y novedades sobre World of Warcraft.">
        <meta name="description" content="Ultimas noticias y novedades sobre World of Warcraft.">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel='stylesheet' href='css/style_inicio.css'>
        <link rel="icon" type="img/png" href="img/icon.png" />
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src='js/js_inicio.js'></script>
        <link rel="alternate" type="application/rss+xml" title="News World of Warcraft" href="rss/rss.xml">
        <script src="js/jquery.toast.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.toast.min.css" />
        <script>$(document).ready(function () {<%for (News aux : list) {%>
                noticias.push(<%=aux.getId()%>);
            <%}%>
            });</script>
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


                    <a class="navbar-brand" href="./"><img id="logo" class="img-responsive" src="img/logo.png" alt="logo img"></a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="./">Inicio</a></li>
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
                        <li class="active"><a href="addnew.html">Añadir Noticia</a></li>
                    </ul>
                </div>

            </div>

        </nav>

        <div id="Carousel" class="carousel slide" data-ride="carousel">


            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="img/1.jpg" alt="Primera" />

                </div>

                <div class="item">
                    <img src="img/2.jpg" alt="Segunda" />
                </div>

                <div class="item">
                    <img src="img/3.jpg" alt="Tercera" />
                </div>
            </div>
        </div>
        <div class="publicidadver">
            <img class="img-responsive center-block" src="img\publi_vert.jpg" alt="..." id="publiver"></div>
        <div id="noticias">
            <div class="container-fluid">
                <div class="row noticias">
                    <div class="col-sm-6 col-md-6">
                        <a href="noticias/<%=list.get(0).getSlug()%>">
                            <div class="thumbnail">
                                <div class="caption title">
                                    <h3 class="text-justify"><%=list.get(0).getTitle()%></h3>
                                </div>
                                <img src="img/noticias/imgmid/<%=list.get(0).getId()%>.png" alt="...">
                                <div class="caption">
                                    <p class="text-justify"><%=list.get(0).getDescription()%></p>
                                    <p class="text-right"><em><%=list.get(0).getDate()%></em></p>

                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <a href="noticias/<%=list.get(1).getSlug()%>">
                            <div class="thumbnail">
                                <div class="caption title">
                                    <h3 class="text-justify"><%=list.get(1).getTitle()%></h3>
                                </div>
                                <img src="img/noticias/imgmid/<%=list.get(1).getId()%>.png" alt="...">
                                <div class="caption">
                                    <p class="text-justify"><%=list.get(1).getDescription()%></p>
                                    <p class="text-right"><em><%=list.get(1).getDate()%></em></p>

                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <a href="noticias/<%=list.get(2).getSlug()%>">
                            <div class="thumbnail">
                                <div class="caption title">
                                    <h3 class="text-justify"><%=list.get(2).getTitle()%></h3>
                                </div>
                                <img src="img/noticias/imgmid/<%=list.get(2).getId()%>.png" alt="...">
                                <div class="caption">
                                    <p class="text-justify"><%=list.get(2).getDescription()%>
                                    </p>
                                    <p class="text-right"><em><%=list.get(2).getDate()%></em></p>

                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <a href="noticias/<%=list.get(3).getSlug()%>">
                            <div class="thumbnail">
                                <div class="caption title">
                                    <h3 class="text-justify"><%=list.get(3).getTitle()%></h3>
                                </div>
                                <img src="img/noticias/imgmid/<%=list.get(3).getId()%>.png" alt="...">
                                <div class="caption">
                                    <p class="text-justify"><%=list.get(3).getDescription()%></p>
                                    <p class="text-right"><em><%=list.get(3).getDate()%></em></p>

                                </div>
                            </div>
                        </a>
                    </div>

                </div>
            </div>
            <button onclick="cargar();" id="mas" class="btn btn-lg btn-default center-block">
                Más noticias
            </button>

        </div>

        <div class="publicidadhor"> <img src="img\publi.jpg" alt="..." id="publihor"></div>
        <div class="twitter"><a class="twitter-timeline" data-width="300" data-height="655" href="https://twitter.com/Warcraft_ES">Tweets by Warcraft_ES</a></div>
        <div class="footer fondo">
            <h4 class="seguirtitulo">Compartir Warcraft Noticias</h4>
            <ul class="list-inline">
                <li class="seguirlink">
                <li class="seguirlink">
                    <img class="img-responsive center-block" src="img/facebook.png" alt="facebook logo" id="compface" />
                </li>
                <li class="seguirlink">
                    <img class="img-responsive center-block" src="img/twitter.png" alt="twitter logo" id="comptw" />
                </li>
                </li>
            </ul>
        </div>

    </body>

</html>
