//On page load
$(document).ready(function () {
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


$(document).ready(function () {
    loadClassifiers();
    listenToClassifierSelection();
});

function loadClassifiers() {
    console.log("PRINTING");
    console.log("LOADING CLASSIFIERS");
    var user_info_request = getWebServerAddress() + "/session/user-info";
    var xhr = requestJson(user_info_request);
    xhr.onreadystatechange = function () {
        var user = JSON.parse(xhr.responseText);
        var classifiers_request = getServerAddress() +
            "/api/users/" + user.email + "/classifiers";
        console.log(user.email);

        var xhr2 = requestJson(classifiers_request);
        console.log(classifiers_request);
        xhr2.onreadystatechange = function () {
            var classifiers = JSON.parse(xhr2.responseText);
            // $(".dropdown-menu").empty();
            populateClassifiersList(classifiers.content);
        }
    }
}

function populateClassifiersList(classifiers) {
    classifiers.forEach(function (classifier) {
        console.log(classifier.title);
        $(".dropdown-menu").append("<li><a href=\"#\">" + classifier.title + "</a></li>")
    });
}

function listenToClassifierSelection() {

    $(".dropdown-menu li").click(function () {
        alert("CLASSIFIER SELECTED");
        console.log("CLASSIFIER SELECTED");
        alert(this);
    })

}