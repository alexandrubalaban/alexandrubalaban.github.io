var executeJAR = function () {

    $.ajax({
        method: "GET",
        url: "/laravel/public/webhose",
        data: {execJAR: "true"}
    }).success(function() {
        window.alert("succes");
    });
}
