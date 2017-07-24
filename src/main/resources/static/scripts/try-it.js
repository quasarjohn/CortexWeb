function progressbar() {
    var bar = new ProgressBar.Circle(progressbarCon, {
        color: '#aaa',
        // This has to be the same size as the maximum width to
        // prevent clipping
        strokeWidth: 4,
        trailWidth: 1,
        easing: 'easeInOut',
        duration: 10000,
        text: {
            autoStyleContainer: false
        },
        from: {color: '#008080', width: 1},
        to: {color: '#008080', width: 4},
        // Set default step function for all animate calls
        step: function (state, circle) {
            circle.path.setAttribute('stroke', state.color);
            circle.path.setAttribute('stroke-width', state.width);

            var value = Math.round(circle.value() * 100);
            if (value === 0) {
                circle.setText('');
            } else {
                circle.setText(value + "%");
            }

            if (value === 100) {
                $("#progressbarCon").empty();
                $("#progressbarCon").hide();
                $("#progressbarText").hide();
                $(".result").show();
                $("#trainBtn").show();
            }
        }
    });
    bar.text.style.fontFamily = '"Raleway", Helvetica, sans-serif';
    bar.text.style.fontSize = '2rem';

    bar.animate(1.0);  // Number from 0.0 to 1.0
}

function classifyImage(img_url) {
    //send async request to the server
    var xhr = new XMLHttpRequest();
    /*
     as of the moment, the request is sent to localhost, but it should be sent later to the
     dedicated server for image classification
     */
    xhr.open('GET', "http://localhost:8091/api/user1/classifier/classify_image/xxx?img_url=" +
        encodeURIComponent(img_url) + "&max_results=5", true);
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send();

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var progressList = document.getElementById('progressList');
            var jsonData = JSON.parse(xhr.responseText);

            for (var i = 0; i < jsonData.content.length; i++) {
                var label = jsonData.content[i].label;
                var probability = jsonData.content[i].probability * 100;
                //probability to display
                var probability_decimal = Number(Math.round(probability + 'e2') + 'e-2').toFixed(2);
                //used for determining the width of the progressbar
                var probability_int = Math.round(probability);

                //the probabilities of the classifications are displayed using this piece of code.
                // medyo baboy yung pagkakacode. haha
                //TODO please remove the text from the background of the progressbar
                $('#progressList').append("<div class='progress'><div class='progress-bar progress-bar-custom text' " +
                    "role='progressbar' aria-valuenow='" + probability_int + "' aria-valuemin='0' aria-valuemax='100' " +
                    "style='width: " + probability_int + "%;'><span class='text-center'>" + probability_decimal + "% " +
                    "Probability</span></div></div>")

                $('#labelsList').append("<p>" + label + "&ensp;</p>")
                alert(label);
            }

            document.getElementById('test_content').innerHTML = xhr.responseText;
            document.getElementById('resultImg').src = img_url;
        }
    };
}

//on page load, hide some elements that will only be shown when classifying images
$(document).ready(function () {
    $(".url").prop("disabled", false);
    $("#progressbarCon").hide();
    $("#progressbarText").hide();
    $(".result").hide();
    $("#trainBtn").hide();
});

//block for classifying images when clicked
$(document).ready(function () {
    //TODO classify image when clicked. This has no function yet
    $(".classifyImg").click(function () {
        $(".url").prop("disabled", true);
        $("#progressbarCon").show();
        $("#progressbarText").show();
        progressbar();
        $("#fileChooserBtn").addClass("disableFileChooserBtn");
        $(".classifyBtn").addClass("disableClassifyBtn");
        $(".classifyImg").addClass("opacityClassifyImg");
    });
});

//block for classifying images using url
$(document).ready(function () {
    //classify image from URL
    $("#classifyURLBtn").click(function () {
        $(".url").prop("disabled", true);
        $("#progressbarCon").show();
        $("#progressbarText").show();

        var img_url = document.getElementById('img_url_input').value;
        classifyImage(img_url);

        progressbar();
        $("#fileChooserBtn").addClass("disableFileChooserBtn");
        $(".classifyBtn").addClass("disableClassifyBtn");
        $(".classifyImg").addClass("opacityClassifyImg");
    });
});

/*
 block for classifying image uploaded by user
 right now, it can only send images to the api server,
 i will add the code for image classification later
 it will all be done on the server though so this part is complete
 */
$(document).ready(function () {
    $("#fileChooserBtn").click(function () {
        $("#file1").click();
    });

    $("#file1").change(function (event) {
        $("#btnSubmit").click();
    });

    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        ajax_classify_image();
    });

});

//uploads file using ajax
function ajax_classify_image() {

    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            'Access-Control-Allow-Credentials': true,
        },
        enctype: 'multipart/form-data',
        url: "http://192.168.0.137:8091/api/upload/multi",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            console.log("SUCCESS : ", data);
        },
        error: function (e) {

            console.log("ERROR : ", e);
        }
    });
}