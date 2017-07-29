function sidebarOpen() {
    document.getElementById("sidebar").style.display = "block";
    document.getElementById("overlay").style.display = "block";
}

function sidebarClose() {
    document.getElementById("sidebar").style.display = "none";
    document.getElementById("overlay").style.display = "none";
}

$(document).ready(function () {
    $("#fileChooserBtn").click(function () {
        $("#file1").click();
    });

    $("#file1").change(function () {
        $("#btnSubmit").click();
    })

    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        ajax_upload_training_data();
    })
})

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

                    if(percentage < 100)
                        status.innerHTML = percentage_str + "%";
                    else
                        status.innerHTML = "Unzipping files";

                }
            }, false);
            return xhr;
        },
        //user1 is the temporary api key
        enctype: 'multipart/form-data',
        url: "http://192.168.0.149:8091/api/user1/trainer/upload_train_model/hand_gestures/500",
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
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}