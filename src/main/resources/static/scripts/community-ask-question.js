var stompClient = null;
//var sender = document.querySelector("#sender").tex;
$(document).ready(function () {
    $("#alert").hide();
});


function connect() {
    var socket = new SockJS('/cortex-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log($("#sender").text());
        console.log('Connected: ' + frame);
    });
}

function sendQuestion() {
    stompClient.send("/new-question", {}, JSON.stringify({
        'username': $("#sender").text(),
        'title': $("#title").val(),
        'body': $("#body").val(),
        'QUESTION_NUMBER': $("#questionNumber").text()
    }));
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $("#send").click(function () {
        sendQuestion();

        //delay for 1 second para na insert na ung data sa db. XD
        setTimeout(function () {
            window.location = getWebServerAddress() + "/community-questions";
        }, 1000);
    });
});