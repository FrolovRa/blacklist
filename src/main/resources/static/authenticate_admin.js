function login() {
    $.ajax({
        type: "Get",
        url: "/login",
        data: {
            "login": $("#login_input").val(),
            "password": $("#password_input").val()
        },
        cache: false,
        success: function(res){
            console.log(res);
            if (JSON.parse(res).status) {
                window.location = "/admin"
            } else {
                badCredentials()
            }
        }
    });
}
function badCredentials() {
    $("#password_input, #login_input").css({
        "-webkit-box-shadow": "-1px 3px 43px -8px rgba(242,72,72,0.72)",
        "-moz-box-shadow": "-1px 3px 43px -8px rgba(242,72,72,0.72)",
        "box-shadow": "-1px 3px 43px -8px rgba(242,72,72,0.72)"
    })
}