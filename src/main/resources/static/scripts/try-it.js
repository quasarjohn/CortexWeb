/*function progressbar() {
    var bar = new ProgressBar.Circle(progressbarCon, {
        color: '#aaa',
        // This has to be the same size as the maximum width to
        // prevent clipping
        strokeWidth: 4,
        trailWidth: 1,
        easing: 'easeInOut',
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
        }
    });
    bar.text.style.fontFamily = '"Raleway", Helvetica, sans-serif';
    bar.text.style.fontSize = '2rem';

    bar.animate(1.0);  // Number from 0.0 to 1.0
    return bar;
}*/

//on page load, hide some elements that will only be shown when classifying images

var classifier = "flowers";

$(document).ready(function () {
    $(".url").prop("disabled", false);
    $("#progressbarCon").hide();
    $("#progressbarText").hide();
    $(".result").hide();
});

//block for classifying images when clicked
$(document).ready(function () {
    $(".classifyImg").click(function () {

        onClassificationStarted();
        classifyImageFromURL(this.src);
    });
});

//block for classifying images using url
$(document).ready(function () {
    $("#classifyURLBtn").click(function () {
        $(".result").hide();
        onClassificationStarted();
        var img_url = document.getElementById('img_url_input').value;
        classifyImageFromURL(img_url);
    });
});

function onClassificationStarted() {
    $("#resultImg").hide();
    $('#labelsList').empty();
    $('#progressList').empty();
    $(".url").prop("disabled", true);
    $("#progressbarCon").show();
    $("#progressbarText").show();
    //progressbar();
    $("#fileChooserBtn").addClass("disableFileChooserBtn");
    $(".classifyBtn").addClass("disableClassifyBtn");
    $(".classifyImg").addClass("opacityClassifyImg");
}

function onClassificationDone() {
    $(".url").prop("disabled", false);
    $("#fileChooserBtn").removeClass("disableFileChooserBtn");
    $(".classifyBtn").removeClass("disableClassifyBtn");
    $(".classifyImg").removeClass("opacityClassifyImg");
    $("#resultImg").show();
    $("#progressbarCon").empty();
    $("#progressbarCon").hide();
    $("#progressbarText").hide();
    $(".result").show();
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

//uploads file using ajax
function ajax_classify_image() {
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
        url: "http://192.168.0.149:8091/api/publicapikey/classifier/upload_classify_image/" + classifier,
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

function classifyImageFromURL(img_url) {
    //send async request to the server
    var xhr = new XMLHttpRequest();
    /*
     as of the moment, the request is sent to localhost, but it should be sent later to the
     dedicated server for image classification
     */
    xhr.open('GET', "http://192.168.0.149:8091/api/publicapikey/classifier/classify_image/" + classifier + "?img_url=" +
        img_url + "&max_results=5", true);
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send();

    xhr.onreadystatechange = function () {
        onClassificationDone();
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
                $('#progressList').append("<h6>" + probability_decimal + "% " + "</h6><div class='progress'><div class='progress-bar progress-bar-custom text' " +
                    "role='progressbar' aria-valuenow='" + probability_int + "' aria-valuemin='0' aria-valuemax='100' " +
                    "style='width: " + probability_int + "%;'></div></div>")

                $('#labelsList').append("<p>" + label + "&ensp;</p>")
            }

            //document.getElementById('test_content').innerHTML = xhr.responseText;
            $('#resultImg').attr('src', img_url);
        }
    };
}

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
        // medyo baboy yung pagkakacode. haha
        //TODO please remove the text from the background of the progressbar
        $('#progressList').append("<h6>" + probability_decimal + "% " + "</h6><div class='progress'><div class='progress-bar progress-bar-custom text' " +
            "role='progressbar' aria-valuenow='" + probability_int + "' aria-valuemin='0' aria-valuemax='100' " +
            "style='width: " + probability_int + "%;'></div></div>")

        $('#labelsList').append("<p>" + label + "&ensp;</p>")
    }
}

$(document).ready(function () {

    changeImgSrc("flowers");

    $(".dropdown-menu li").click(function () {
        $(".dropdown-menu li").removeClass("active");
        $(this).addClass("active");

        switch (this.id) {
            case "flowers":
                classifier = "flowers";
                $("#dropdown-preview").html("Flowers <span class=\"caret\">");
                break;
            case "hands":
                classifier = "hand_gestures";
                $("#dropdown-preview").html("Hand Gestures <span class=\"caret\">");
                break;
            case "bills":
                classifier = "bills";
                $("#dropdown-preview").html("Bills <span class=\"caret\">");
                break;
            default:
                break;
        }

        changeImgSrc(this.id);
    });

});

function changeImgSrc(category) {
    var images = [$("#img1"), $("#img2"), $("#img3"), $("#img4"), $("#img5"), $("#img6"), $("#img7")];

    var flowers = ["daisy.jpg", "dandelion.jpg", "rose.jpg", "sunflower.jpg", "daisy1.jpg", "rose1.jpg", "tulip1.jpg"];
    var hand_gestures = ["a.jpg", "i.jpg", "n.jpg", "p.jpg", "s.jpg", "t.jpg", "u.jpg"];
    var bills = ["5.jpg", "10.jpg", "20.jpg", "100.jpg", "1000.jpg", "200.jpg", "500.jpg"];

    if (category == "flowers") {
        for (var i = 0; i < images.length; i++) {
            images[i].attr("src", "images/flowers/" + flowers[i]);
        }
    }
    else if (category == "bills") {
        for (var i = 0; i < images.length; i++) {
            images[i].attr("src", "images/bills/" + bills[i]);
        }
    }
    else {
        for (var i = 0; i < images.length; i++) {
            images[i].attr("src", "images/hand_gestures/" + hand_gestures[i]);
        }
    }
}
