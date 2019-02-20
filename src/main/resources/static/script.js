let checked;
$(document)
    .on('mouseenter','.customer_info_wrapper', function() {
    $(this).css("background-color", "dimgray");
}).on('mouseleave','.customer_info_wrapper', function() {
    $(this).css("background-color", "darkgrey");
}).on('click', '.customer_info_wrapper', function(){
    if(checked !== undefined) {
        checked.removeClass("checked");
    }
    checked = $(this);
    checked.addClass("checked");

    $("#first_name_input").val(checked.find(".first_name").html());
    $("#last_name_input").val(checked.find(".last_name").html());
    $("#phone_input").val(checked.find(".phone").html())
});

function add() {
    $.ajax({
        type: "POST",
        url: "/add",
        data: {
            "first": $("#first_name_input").val(),
            "last": $("#last_name_input").val(),
            "phone": $("#phone_input").val(),
        },
        cache: false,
        success: function(res){
            $(".customers").append(res)
        },
        complete: function (err) {
            if (err.status === 204) {
                badInput();
            } else {
                $("#first_name_input, #last_name_input, #phone_input").removeAttr("style");
            }
        }
    });
}

function badInput() {
    $("#first_name_input, #last_name_input, #phone_input").css({
        "-webkit-box-shadow": "-1px 3px 43px -8px rgba(242,72,72,0.72)",
        "-moz-box-shadow": "-1px 3px 43px -8px rgba(242,72,72,0.72)",
        "box-shadow": "-1px 3px 43px -8px rgba(242,72,72,0.72)"
    })
}

function remove() {
    $.ajax({
        type: "GET",
        url: "/remove",
        data: {
            "id": checked.data("id")
        },
        cache: false,
        success: function(){
            checked.remove()
            $("#first_name_input").val("");
            $("#last_name_input").val("");
            $("#phone_input").val("")
        }
    });
}

function change() {
    $.ajax({
        type: "POST",
        url: "/change",
        data: {
            "id": checked.data("id"),
            "first": $("#first_name_input").val(),
            "last": $("#last_name_input").val(),
            "phone": $("#phone_input").val()
        },
        cache: false,
        success: function(res){
            checked.html(res)
        }
    });
}


