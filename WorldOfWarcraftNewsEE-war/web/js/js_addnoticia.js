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
                    height: '150px'
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
        //...
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

$(document).ready(function () {
    $("#compface").click(function () {
        var shareurl = window.location.href;
        window.open('https://www.facebook.com/sharer/sharer.php?u=' + escape(shareurl), document.title,
                'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');
        return false;
    });

    $("#comptw").click(function () {
        var shareurl = window.location.href;
        window.open('http://twitter.com/share?text=' + document.title + '&url=' + escape(shareurl), document.title,
                'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');
        return false;
    });
    $("#divCargando").fadeOut(400);
    initTextArea();

    $("#formAddNoticia").submit(function () {
        if (tinymce.activeEditor.getContent().length > 0) {
            addNoticia();
        } else {
            showToast("Contenido vacio", "Complete el campo", "warning", "#D43721");
        }
        return false;
    });

});


function initTextArea() {
    tinymce.init({
        selector: 'textarea',
        height: 300,
        max_height: 500,
        menubar: true,
        entity_encoding: "raw",
        encoding: "UTF-8",
        themes: "modern",

        plugins: [
            'anchor textcolor',
            ' media'
        ],
        toolbar: 'insert | undo redo |  formatselect | bold italic backcolor  | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat ',
        content_css: [
            '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
            '//www.tinymce.com/css/codepen.min.css']
    }
    );
}
;

function addNoticia() {
    $("#divCargando").fadeIn(400);

    var form = $('#formAddNoticia')[0];

    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "AddNoticia",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,

        success: function (rsp) {
            $("#divCargando").fadeOut(400);
            showToast("Successfull", rsp["mess"], "success", "#36B62D");
            form.reset();
        },
        error: function (e) {
            $("#divCargando").fadeOut(400);
            if (e["responseJSON"] === undefined)
                showToast("UNKNOWN ERROR", "Try it later", "error", "#D43721");
            else
                showToast(e["responseJSON"]["error"], "Whoops!", "error", "#D43721");


        }
    });


}


function bs_input_file() {
    $(".input-file").before(
            function () {
                if (!$(this).prev().hasClass('input-ghost')) {
                    var element = $("<input id='fileinput' type='file' class='input-ghost' name='file'  accept='.jpg,.jpeg,.png' style='visibility:hidden; height:0' required>");
                    element.attr("name", $(this).attr("name"));
                    element.change(function () {
                        element.next(element).find('input').val((element.val()).split('\\').pop());
                    });
                    $(this).find("button.btn-choose").click(function () {
                        element.click();
                    });
                    $(this).find("button.btn-reset").click(function () {
                        element.val(null);
                        $(this).parents(".input-file").find('input').val('');
                    });
                    $(this).find('input').css("cursor", "pointer");
                    $(this).find('input').focus(function () {
                        $(this).parents('.input-file').prev().click();
                        return false;
                    });
                    $(this).find('input').mousedown(function () {
                        $(this).parents('.input-file').prev().click();
                        return false;
                    });
                    return element;
                }
            }
    );
}
$(function () {
    bs_input_file();
}
);

/**
 * The toast is an external librery developed by https://github.com/kamranahmedse/jquery-toast-plugin/
 * Here there are the documentation about how to use it: http://kamranahmed.info/toast
 * @param {type} head Main text message
 * @param {type} text Submessage
 * @param {type} icon (warning | success | error | info)
 * @param {type} bgColor Color of the toast
 * @returns {undefined}
 */
function showToast(head, text, icon, bgColor) {
    $.toast({
        text: text, // Text that is to be shown in the toast
        heading: head, // Optional heading to be shown on the toast
        icon: icon, // Type of toast icon: warning | success | error | info
        showHideTransition: 'fade', // fade, slide or plain
        allowToastClose: false, // Boolean value true or false
        hideAfter: 3000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
        position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
        textAlign: 'left', // Text alignment i.e. left, right or center
        loader: true, // Whether to show loader or not. True by default
        loaderBg: '#9EC600', // Background color of the toast loader
        bgColor: bgColor
    });
}