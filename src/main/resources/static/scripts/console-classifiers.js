$(document).ready(function () {
    // $('[data-toggle="tooltip"]').tooltip();

    //get session's active user

    var xhr = json_fetch_session_data();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var jsonData = JSON.parse(xhr.responseText);
            var user = jsonData.email;

            var xhr1 = requestJson(getServerAddress() + "/api/users/" + user + "/classifiers");
            xhr1.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var jsonData1 = JSON.parse(xhr1.responseText);

                    var classifiers = jsonData1.content;

                    $("#rows").empty();
                    //create first row
                    $("#con").append("<div id=\"row\" class=\"row\"></div>")

                    var counter = 0;
                    classifiers.forEach(function (classifier) {

                        //create new row
                        if (counter == 3) {
                            appendClassifier(classifier);
                            $("#row").removeAttr("id");
                            $("#con").append("<div id=\"row\" class=\"row\"></div>")
                            counter = 0;
                        }

                        //simply append to current row
                        else {
                            appendClassifier(classifier);
                        }

                        counter++;
                    });
                }
            }
        }
    }
});

//TODO right now, I am using the title of the classifier as the param for the path of the image, a unique random key must be generated instead to avoid conflicts in case two classifiers are given the same name
function appendClassifier(classifier) {
    $("#row").append("<div class=\"col-sm-4\">\n" +
        "                <div class=\"w3-card-4\">\n" +
        "                    <img class=\"img-responsive\" " +
        "src=\"http://192.168.99.1:8091/api/files/" + classifier.title + "/thumbnail\" />\n" +
        "                    <div class=\"w3-container\">\n" +
        "                        <h3>" + classifier.title + "</h3>\n" +
        "                        <p>Accuracy : 92.23%</p>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>")
}