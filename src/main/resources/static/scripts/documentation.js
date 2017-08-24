function sidebarClose() {
    document.getElementById("sidebar").style.width = "0";
    document.getElementById("mainContent").style.width = "85vw";
}

function linksOpen() {
    document.getElementById("openLinksBtn").style.display = "none";
    document.getElementById("closeLinksBtn").style.display = "block";
    document.getElementById("scrollSpy").style.display = "block";
    document.getElementById("mainContent").style.marginLeft = "80%";
    document.getElementById("links").style.width = "80%";

    $("ul li").click(function () {
        document.getElementById("closeLinksBtn").style.display = "none";
        document.getElementById("openLinksBtn").style.display = "block";
        document.getElementById("scrollSpy").style.display = "none";
        document.getElementById("mainContent").style.marginLeft = "0";
    });
}

function linksClose() {
    document.getElementById("closeLinksBtn").style.display = "none";
    document.getElementById("openLinksBtn").style.display = "block";
    document.getElementById("scrollSpy").style.display = "none";
    document.getElementById("mainContent").style.marginLeft = "0";
}

$("blockquote").click(function () {
    var screenWidth = window.innerWidth
        || document.documentElement.clientWidth
        || document.body.clientWidth;

    if(screenWidth <= 992) {
        document.getElementById("sidebar").style.display = "block";
        document.getElementById("sidebar").style.width = "100vw";
    }
    else if(screenWidth > 992) {
        document.getElementById("sidebar").style.width = "38%";
        document.getElementById("mainContent").style.width = "47vw";
    }

    var noActionSelected = document.getElementById("noActionSelected");
    var blockquoteSelected = document.getElementById("blockquoteSelected");

    noActionSelected.style.display = noActionSelected.style.display.replace("block", "none");
    blockquoteSelected.style.display = blockquoteSelected.style.display.replace("none", "block");

    //all the data are retrieved from Cortex API server
    switch (this.id) {
        case "classify-from-url" :
            loadDocumentation("http://192.168.1.8:8091/api/docs/classifier/classify-from-url");
            break;
        case "upload-classify":
            loadDocumentation("http://192.168.1.8:8091/api/docs/classifier/upload-classify");
        case "upload-train":
            loadDocumentation("http://192.168.1.8:8091/api/docs/trainer/train");
        case "status":
            loadDocumentation("http://192.168.1.8:8091/api/docs/trainer/status");
        case "info":
            loadDocumentation("http://192.168.1.8:8091/api/docs/trainer/info");
        case "logs":
            loadDocumentation("http://192.168.1.8:8091/api/docs/trainer/logs");
        case "stop":
            loadDocumentation("http://192.168.1.8:8091/api/docs/trainer/stop");
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
