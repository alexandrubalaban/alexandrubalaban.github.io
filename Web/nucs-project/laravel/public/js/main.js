var executeJAR = function () {

    $.ajax({
        method: "GET",
        url: "/nucs-project/laravel/public/webhose",
        data: {execJAR: "true"}
    }).success(function() {
        window.alert("succes");
    });
}
$( document ).ready(function( $ ) {
    $( '#news-slider' ).sliderPro({
            width: '100%',
            height: 500,
            arrows: true,
            buttons: false,
            waitForLayers: true,
            thumbnailWidth: 240,
            thumbnailHeight: 100,
            thumbnailPointer: true,
            autoplay: false,
            autoScaleLayers: false,
            breakpoints: {
                500: {
                    thumbnailWidth: 120,
                    thumbnailHeight: 50
                }
            }
    });
 });
$(document).ready(function() {

    $("#inspiring-quote-carousel").owlCarousel({
        autoPlay : 3000,
        stopOnHover : true,
        paginationSpeed : 1000,
        goToFirstSpeed : 2000,
        singleItem : true
    });
    $("#interesting-fact-carousel").owlCarousel({
        autoPlay : 3000,
        stopOnHover : true,
        paginationSpeed : 1000,
        goToFirstSpeed : 2000,
        singleItem : true
    });

});