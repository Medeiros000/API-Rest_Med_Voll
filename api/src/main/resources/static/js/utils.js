$(function() {
    $(".accord").click(function() {
        $(this).toggleClass("active");
        var panel = $(this).next();
        if (panel.css("display") === "block") {
            panel.css("display", "none");
        } else {
            panel.css("display", "block");
        }
    });
});