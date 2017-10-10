var consolePaths = ['/console-classify', '/console-train', '/console-classifiers'];
var path = window.location.pathname;

$(document).ready(function() {
    console.log("shit");
    $('.nav > li > a[href="'+path+'"]').parent().addClass('active');
    $('.dropdown-menu > li > a[href="'+path+'"]').parent().addClass('active');

    for(var i = 0; i < consolePaths.length; i++) {
        if(path == consolePaths[i]) {
            console.log(consolePaths[i]);
            $('.nav > li > a[href="/console-overview"]').parent().addClass('active');
        }
    }
});