$(document).ready(function () {

    "use strict";

    $(document).ready(function () {

        var thirdRow = $("#third-row");

        var img = $(".img-test");

        thirdRow.bind("mouseover", function () {
            $(".third-block-text").fadeIn(2000);
            img.animate({bottom: 20}, 200)
        });

    });


});
