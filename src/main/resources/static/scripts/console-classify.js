var model_key = null;
var api_key = null;

//On page load
$(document).ready(function () {
    $("#outputImage").hide();
    $(".outputPanels").hide();
    $("#progressBar").hide();
    $("#progressBarMessage").hide();
    $('[data-toggle="tooltip"]').tooltip();
});


$(document).ready(function () {
    var xhr = requestJson(getWebServerAddress() + "/session/user-info");
    xhr.onreadystatechange = function () {
        var user = JSON.parse(xhr.responseText);
        api_key = user.api_key;

        loadClassifiers();
    }
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
            $(".dropdown-menu").empty();
            populateClassifiersList(classifiers.content);
        }
    }
}

function populateClassifiersList(classifiers) {
    classifiers.forEach(function (classifier) {
        $(".dropdown-menu").append("<li id='" + classifier.model_key + "'><a href=\"#\">" + classifier.title + "</a></li>")
    });
    listenToClassifierSelection();
}

function listenToClassifierSelection() {
    $(".dropdown-menu li").click(function () {
        alert("CLASSIFIER SELECTED");
        // console.log("CLASSIFIER SELECTED");
        alert(this.id);
        model_key = this.id.toString();
        alert(model_key);
    });
}

//uploads file using ajax
function ajax_classify_image() {
    alert(model_key);
    onClassificationStarted();
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);

    data.append("key", "testVALUE");

    $.ajax({
        type: "POST",
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            'Access-Control-Allow-Credentials': true,
        },
        //user1 is the temporary api key
        enctype: 'multipart/form-data',
        url: getServerAddress() + "/api/" + api_key + "/classifier/upload_classify_image/" + model_key,
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            // displayClassificationResults(data);
            var str_data = JSON.stringify(data);
            onClassificationDone();
            console.log(str_data);
            //document.getElementById('test_content').innerHTML = str_data;
            displayClassificationResults(str_data);
        },
        error: function (e) {
            onClassificationDone();
            console.log("ERROR : ", e);
        }
    });
}

function onClassificationStarted() {

    var hideOnLoad = document.getElementById("hideOnLoad");
    hideOnLoad.style.display = hideOnLoad.style.display.replace("none", "block");

    $("#outputImage").hide();
    $(".outputPanels").hide();
    $("#progressBar").show();
    $("#progressBarMessage").show();
}

function onClassificationDone() {
    $("#outputImage").show();
    $(".outputPanels").show();
    $("#progressBar").hide();
    $("#progressBarMessage").hide();
}

// block for classifying image uploaded by user
$(document).ready(function () {
    $("#fileChooserBtn").click(function () {
        $("#file1").click();
    });

    var input = document.getElementById('file1');
    $("#file1").change(function (event) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#resultImg').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
        $("#btnSubmit").click();
    });

    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        ajax_classify_image();
    });

});

function displayClassificationResults(data) {
    var jsonData = JSON.parse(data);
    for (var i = 0; i < jsonData.content.length; i++) {
        var label = jsonData.content[i].label;
        var probability = jsonData.content[i].probability * 100;
        //probability to display
        var probability_decimal = Number(Math.round(probability + 'e2') + 'e-2').toFixed(2);
        //used for determining the width of the progressbar
        var probability_int = Math.round(probability);

        //the probabilities of the classifications are displayed using this piece of code.
        $('#progressList').append("<h6>" + probability_decimal + "% " + "</h6><div class='progress'><div class='progress-bar progress-bar-custom text' " +
            "role='progressbar' aria-valuenow='" + probability_int + "' aria-valuemin='0' aria-valuemax='100' " +
            "style='width: " + probability_int + "%;'></div></div>")

        $('#labelsList').append("<p>" + label + "&ensp;</p>")
    }
}