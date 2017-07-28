$(document).ready(function() {
    $('[data-toggle="tooltip"]').tooltip();
});

function sidebarOpen() {
    document.getElementById("sidebar").style.display = "block";
    document.getElementById("overlay").style.display = "block";
}

function sidebarClose() {
    document.getElementById("sidebar").style.display = "none";
    document.getElementById("overlay").style.display = "none";
}