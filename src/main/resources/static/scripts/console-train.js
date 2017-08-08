$(document).ready(function () {
    $("#progressBar").hide();
    $("#progressBarMessage").hide();
    $("#progressBarPercentage").hide();
    $("#hideOnLoad").attr("style", "display: block;");

    $("#fileChooserBtn").click(function () {
        $("#file1").click();
    });

    $("#file1").change(function () {
        $("#btnSubmit").click();
    });

    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        $("#progressBar").show();
        ajax_upload_training_data();
    });
});

function ajax_upload_training_data() {
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
        xhr: function () {
            var xhr = new window.XMLHttpRequest();
            xhr.upload.addEventListener('progress', function (e) {
                if (e.lengthComputable) {
                    // Append progress percentage.
                    var loaded = e.loaded;
                    var total = e.total;
                    var progressValue = Math.round(( loaded / total ) * 100);

                    // Bytes received.
                    var percentage = (loaded / total) * 100;
                    var percentage_str = Number(Math.round(percentage + 'e2') + 'e-2').toFixed(2);

                    var status = document.getElementById('uploadStatus');

                    if (percentage < 100) {
                        status.innerHTML = percentage_str + "%";
                        $("#progressBarPercentage").show();
                        $("#progressBarPercentage").text(percentage_str + "%");
                        //progressbar(percentage_str, percentage_str + "%");
                        //$("#progressbarText").setText(percentage_str + "%");
                    }
                    else {
                        status.innerHTML = "Unzipping files";
                        $("#progressBarMessage").show();
                        $("#progressBarMessage").text("Unzipping files");
                        //progressbar(percentage_str,  percentage_str + "%");
                        //$("#progressbarText").setText("Unzipping files");
                    }
                }
            }, false);
            return xhr;
        },
        //user1 is the temporary api key
        enctype: 'multipart/form-data',
        url: "http://192.168.0.140:8091/api/user1/trainer/upload_train_model/hand_gestures/500",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        //2 hours timeout
        timeout: 7200000,
        success: function (data) {
            // displayClassificationResults(data);
            var str_data = JSON.stringify(data);
            console.log(str_data);
            var status = document.getElementById('uploadStatus');
            //TODO create a method for checking the training status every minute
            status.innerHTML = "CORTEX is training your image classifier.";
            $("#progressBarMessage").text("CORTEX is training your image classifier...");
            $("#progressBarPercentage").hide();
            //$("#progressbarText").setText("CORTEX is training your image classifier.");
            var count = 0;
            var hour;

            var now = new Date();
            var delay = 1000 * 60; // 60 sec
            var start = delay - (now.getMinutes() * 60 + now.getSeconds()) * 1000 + now.getMilliseconds();

            setTimeout(function doSomething() {
                // do the operation
                // ... your code here...
                // status.innerHTML = "CORTEX is training your image classifier." + count + " seconds ago.";
                //$("#progressbarText").setText("CORTEX is training your image classifier." + count + " seconds ago.");
                if(count == 0) {
                    $("#progressBarMessage").text("CORTEX is training your image classifier...");
                } else if(count > 0) {
                    $("#progressBarMessage").text("CORTEX is training your image classifier... " + count + " minute/s ago.");
                } else if(count == 60) {
                    count /= 60;
                    $("#progressBarMessage").text("CORTEX is training your image classifier... " + count + " hour/s ago.");
                } else if(count > 60) {
                    hour = count/60;
                    count%=60;
                    $("#progressBarMessage").text("CORTEX is training your image classifier... " + hour + " hour/s and " + count + " minute/s ago.");
                }

                count++;

                // schedule the next tick
                var xhr = new XMLHttpRequest();
                xhr.open('GET', "http://192.168.0.140:8091/api/user1/trainer/status");
                xhr.setRequestHeader('Content-Type', 'application/json');
                xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
                xhr.setRequestHeader('Access-Control-Allow-Methods', 'GET, POST, PATCH, PUT, DELETE, OPTIONS');
                xhr.setRequestHeader('Access-Control-Allow-Headers', 'Origin, Content-Type, X-Auth-Token');
                xhr.send();

                xhr.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var jsonData = JSON.parse(xhr.responseText);
                        status.innerHTML = JSON.stringify(jsonData);
                        var progressPercentage = jsonData.content["percentage"];
                        $("#progressBarPercentage").show();
                        $("#progressBarPercentage").text(progressPercentage + "%");
                    }
                    //$("#hideOnLoad").attr("style", "display: none;");
                };
                setTimeout(doSomething, delay);
            }, start);
        },
        error: function (e) {
            console.log("ERROR : ", e);
            $("#progressBarMessage").text("Error : ", e);
        }
    });
}