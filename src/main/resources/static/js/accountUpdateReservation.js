$(document).ready(function() {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajaxSetup({
        headers:
            { 'X-CSRF-TOKEN': token }
    })


    $(".account-accept").on("click", function () {

        let reservation_id = this.id;
        let id = Number(reservation_id.split("_")[1]);

        $.post("/account", {
            id   : id   ,
            result : true
        }, function(data) {

        }).done(function(data) {
            //console.log("ok");
        }).fail(function(xhr, textStatus, errorThrown) {
            console.log(textStatus);
        });
    });

    $(".account-refuse").on("click", function () {

        let reservation_id = this.id;
        let id = Number(reservation_id.split("_")[1]);

        $.post("/account", {
            id   : id   ,
            result : false
        }, function(data) {

        }).done(function(data) {
            //console.log("ok");
        }).fail(function(xhr, textStatus, errorThrown) {
            console.log(textStatus);
        });
    });


});
