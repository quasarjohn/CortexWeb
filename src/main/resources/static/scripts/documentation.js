$("blockquote").click(function () {
    var noActionSelected = document.getElementById("noActionSelected");
    var blockquoteSelected = document.getElementById("blockquoteSelected");

    noActionSelected.style.display = noActionSelected.style.display.replace("block", "none");
    blockquoteSelected.style.display = blockquoteSelected.style.display.replace("none", "block");

    //all the data are retrieved from Cortex API server
    switch (this.id) {
        case "classify-from-url" :
            loadDocumentation("http://192.168.0.149:8091/api/docs/classifier/classify-from-url");
            break;
        case "upload-classify":
            loadDocumentation("http://192.168.0.149:8091/api/docs/classifier/upload-classify");
        case "upload-train":
            loadDocumentation("http://192.168.0.149:8091/api/docs/trainer/train");
        case "status":
            loadDocumentation("http://192.168.0.149:8091/api/docs/trainer/status");
        case "info":
            loadDocumentation("http://192.168.0.149:8091/api/docs/trainer/info");
        case "logs":
            loadDocumentation("http://192.168.0.149:8091/api/docs/trainer/logs");
        case "stop":
            loadDocumentation("http://192.168.0.149:8091/api/docs/trainer/stop");
        default:
            break;
    }

    $("#request").html("");

});

//ajax request for the data of the documentation
function loadDocumentation(doc_url) {
    $.ajax({
        type: "GET",
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            'Access-Control-Allow-Credentials': true,
        },
        //user1 is the temporary api key
        enctype: 'multipart/form-data',
        url: doc_url,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json_str = JSON.stringify(data);
            var json = JSON.parse(json_str);

            $("#json-result").html(json.content["json_result"]);
            $("#url").html(json.content["url"]);
            $("#request").html(json.content["request"]);

            var params = json.content["parameters"];

            $("#params").empty();

            params.forEach(function (param) {
                $("#params").append("<div class=\"row\">\n" +
                    "                <div class=\"col-sm-4\">\n" +
                    "                    <h5>" + param["title"] + "</h5>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-sm-5\">\n" +
                    "                    <ul>\n" +
                    "                        <li>" + param["description"] + "</li>\n" +
                    "                    </ul>\n" +
                    "                </div>\n" +
                    "                <div class=\"col-sm-3\">\n" +
                    "                    <h5>" + param["paramType"] + "</h5>\n" +
                    "                </div>\n" +
                    "            </div><hr class=\"subHr\" />");
            });
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
