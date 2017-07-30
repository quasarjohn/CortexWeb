//On page load
$(document).ready(function() {
   $("#progressBar").hide();
   $("#progressBarMessage").hide();
   $(".cancelBtn").hide();
   $("#warning").hide();
   $("#warningMessage").hide();
   $("#finished").hide();
   $("#finishedMessage").hide();
   $('[data-toggle="tooltip"]').tooltip();
});

//Execute after the uploading of files is finished
function trainingFinished() {
    $("#progressBar").hide();
    $("#progressBarMessage").hide();
    $(".cancelBtn").hide();
    $(".createBtn").show();
    $("#finished").show();
    $("#finishedMessage").show();
}

//Executes after the user clicked the cancel button
function trainingCancelled() {
    $("#warning").show();
    $("#warningMessage").show();
}

var timeout;

//Hide create button, show cancel button and training finished message
$(".createBtn").click(function() {
    var hideOnLoad = document.getElementById("hideOnLoad");
    hideOnLoad.style.display = hideOnLoad.style.display.replace("none", "");
    var hideOnLoad1 = document.getElementById("hideOnLoad1");
    hideOnLoad1.style.display = hideOnLoad1.style.display.replace("none", "");

    $("#warning").hide();
    $("#warningMessage").hide();
    $("#finished").hide();
    $("#finishedMessage").hide();
    $("#progressBar").show();
    $("#progressBarMessage").show();
    $(".cancelBtn").show();
    $(".createBtn").hide();
    timeout = setTimeout(trainingFinished, 3000);
});

//Hide cancel button, show create button and training cancelled message
$(".cancelBtn").click(function() {
    clearTimeout(timeout);
    $("#progressBar").hide();
    $("#progressBarMessage").hide();
    trainingCancelled();
    $(".cancelBtn").hide();
    $(".createBtn").show();
});