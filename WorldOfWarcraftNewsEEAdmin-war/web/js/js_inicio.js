var cargado = 1;
$(function () {
    $('.navbar-nav > li > a').css('line-height', '75px');
    if (window.matchMedia('(max-width: 976px)').matches) {
        $('#publiver').attr('src', 'img/publi_vert.jpg');
    }
    ;
    if (window.matchMedia('(max-width: 767px)').matches) {
        $('#publiver').attr('src', 'img/publi_vert2.png');
    }
    ;
});
$(window).resize(function () {
    if (window.matchMedia('(max-width: 976px)').matches) {
        $('#publiver').attr('src', 'img/publi_vert.jpg');
    }
    ;
    if (window.matchMedia('(max-width: 767px)').matches) {
        $('#publiver').attr('src', 'img/publi_vert2.png');
    }
    ;
});
$(function () {
    $('#logo').data('size', 'big');
});

$(window).scroll(function () {
    if (window.matchMedia('(max-width: 767px)').matches) {
        if ($(document).scrollTop() > 0) {
            if ($('#logo').data('size') == 'big') {
                $('#logo').data('size', 'small');
                $('#logo').stop().animate({
                    height: '150%'
                }, 600);
            }
        } else {
            if ($('#logo').data('size') == 'small') {
                $('#logo').data('size', 'big');
                $('#logo').stop().animate({
                    height: '280%'
                }, 600);
            }
        }
    } else {
        if ($(document).scrollTop() > 0) {
            if ($('#logo').data('size') == 'big') {
                $('#logo').data('size', 'small');
                $('#logo').stop().animate({
                    height: '100px'
                }, 600);
            }
        } else {
            if ($('#logo').data('size') == 'small') {
                $('#logo').data('size', 'big');
                $('#logo').stop().animate({
                    height: '200px'
                }, 600);
            }
        }
    }
});

$(function () {
    $('.navbar-nav').data('size', 'big');
});

$(window).scroll(function () {
    if (window.matchMedia('(max-width: 767px)').matches) {

    } else {
        if ($(document).scrollTop() > 0) {
            if ($('.navbar-nav').data('size') == 'big') {
                $('.navbar-nav').data('size', 'small');
                $('.navbar-nav').stop().animate({
                    height: '50px'
                }, 600);

            }
        } else {
            if ($('.navbar-nav').data('size') == 'small') {
                $('.navbar-nav').data('size', 'big');
                $('.navbar-nav').stop().animate({
                    height: '100px'
                }, 600);

            }
        }
    }

});

$(function () {
    $('.navbar-nav > li > a').data('size', 'big');
});

$(window).scroll(function () {
    if (window.matchMedia('(max-width: 767px)').matches) {
    } else {
        if ($(document).scrollTop() > 0) {
            if ($('.navbar-nav > li > a').data('size') == 'big') {
                $('.navbar-nav > li > a').data('size', 'small');
                $('.navbar-nav > li > a').stop().animate({
                    'line-height': '14px'
                }, 600);
            }
        } else {
            if ($('.navbar-nav > li > a').data('size') == 'small') {
                $('.navbar-nav > li > a').data('size', 'big');
                $('.navbar-nav > li > a').stop().animate({
                    'line-height': '75px'
                }, 600);
            }
        }
    }

});




$(window).scroll(function () {
    if ($(window).scrollTop() + $(window).height() + 10 >= $(document).height()) {
        cargar();
    }
});


function cargar() {
    var url = "GetMoreNews";
    var last = cargado;
    $.ajax({
        async: false,
        method: "POST",
        url: url,
        data: {last: last},
        success: function (rsp) {
            if (rsp["noMore"] === undefined) {
                addrow(rsp);
                showToast("Cargado", "Cargado correctamente", "success", "#36B62D");

            } else {
                showToast(rsp["noMore"], "No hay más noticias", "success", "#06493C");
                $('#mas').text('No hay más noticias');
            }
        },
        error: function (e) {
            correct = false;
            if (e["responseJSON"] === undefined) {
                showToast("UNKNOWN ERROR", "Try it later", "error", "#D43721");
            } else {
                showToast(e["responseJSON"]["error"], "", "error", "#D43721");
            }
        }
    });
}
;



function addrow(json) {
    $.each(json, function (i, item) {
        $(".noticias").append('<div class="col-sm-6 col-md-6">' +
                '<a href="noticias/'+item.slug+'">' +
'<div class="thumbnail">' +
    '<div class="caption title">' + '<h3 class="text-justify">' + item.title + "</h3>" + "</div>" +
    '<img src="' + "res/img/noticias/imgMid/" + item.id + ".png" + '" alt="..." />' +
          '<div class="caption">' + '<p class="text-justify">' + item.description + "</p>" +
        '<p class="text-right">' + "<em>" + item.date + "</em>" + "</p>" + "</div>" + "</div>" +"</a>" + "</div>");
        cargado++;
    })
}
;

$(document).ready(function () {
    $("#compface").click(function () {
        var shareurl = window.location.href;
        window.open('https://www.facebook.com/sharer/sharer.php?u=' + escape(shareurl), document.title,
                'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');
        return false;
    });
});

$(document).ready(function () {
    $("#comptw").click(function () {
        var shareurl = window.location.href;
        window.open('http://twitter.com/share?text=' + document.title + '&url=' + escape(shareurl), document.title,
                'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');
        return false;
    });
});

function showToast(head, text, icon, bgColor) {
    $.toast({
        text: text, // Text that is to be shown in the toast
        heading: head, // Optional heading to be shown on the toast
        icon: icon, // Type of toast icon: warning | success | error | info
        showHideTransition: 'fade', // fade, slide or plain
        allowToastClose: false, // Boolean value true or false
        hideAfter: 2000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
        position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
        textAlign: 'left', // Text alignment i.e. left, right or center
        loader: true, // Whether to show loader or not. True by default
        loaderBg: '#9EC600', // Background color of the toast loader
        bgColor: bgColor
    });
}