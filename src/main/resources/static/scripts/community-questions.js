var stompClient = null;

function connect() {
    var socket = new SockJS('/cortex-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/community/questions', function (question) {
            showQuestion(JSON.parse(question.body));
        });
    });
}

function showQuestion(question) {
    // $("#test").html(question.title + " : " + question.body + " : " + question.time_stamp + " : " + question.user_id);
    var x = question.question_NUMBER;
    console.log(x);

    $("#questionNumberCon h2").text(function () { return x; });

    //$("#questionNumberCon").prepend("<h2>" + x + "</h2>");

    $("#questions").prepend("<div class=\"question\">\n" +
        "            <hr/>\n" +
        "            <div class=\"row\">\n" +
        "                <div class=\"col-sm-2\">\n" +
        "                    <h3>0</h3>\n" +
        "                    <p>votes</p>\n" +
        "                    <h3>0</h3>\n" +
        "                    <p>answers</p>\n" +
        "                    <h5>10 views</h5>\n" +
        "                </div>\n" +
        "                <div class=\"col-sm-10\">\n" +
        "                    <a class=\"questionTitle\" href=\"#\" th:href=\"@{/question}\">" + question.title + "</a>\n" +
        "                    <p>" + question.body + "</p>\n" +
        "\n" +
        "                    <div class=\"askedBy\">\n" +
        "                        <p>asked 5 minutes ago</p>\n" +
        "                        <div class=\"row\">\n" +
        "                            <div class=\"col-sm-5\">\n" +
        "                                <img class=\"img-responsive\" src=\"images/dev1.png\"/>\n" +
        "                            </div>\n" +
        "                            <div class=\"col-sm-7\">\n" +
        "                                <a href=\"#\">"+question.user_id+"</a>\n" +
        "                                <p class=\"reputationTooltip\" data-toggle=\"tooltip\" data-placement=\"bottom\"\n" +
        "                                   title=\"Reputation score\">6</p>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <hr/>\n" +
        "        </div>");
}

$(function () {
    connect();
});