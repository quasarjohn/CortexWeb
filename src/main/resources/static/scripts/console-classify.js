//On page load
$(document).ready(function() {
    $("#outputImage").hide();
    $(".outputPanels").hide();
    $("#progressBar").hide();
    $("#progressBarMessage").hide();
    $('[data-toggle="tooltip"]').tooltip();
});

function output() {
    $("#progressBar").hide();
    $("#progressBarMessage").hide();
    $("#outputImage").show();
    $(".outputPanels").show();
}

$("#classifyURLBtn").click(function () {
       var hideOnLoad = document.getElementById("hideOnLoad");
       hideOnLoad.style.display = hideOnLoad.style.display.replace("none", "block");

       $("#outputImage").hide();
       $(".outputPanels").hide();
       $("#progressBar").show();
       $("#progressBarMessage").show();
       setTimeout(output, 3000);
});