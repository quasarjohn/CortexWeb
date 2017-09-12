var stompClient = null;
//var sender = document.querySelector("#sender").tex;

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
        'user_id': $("#sender").text(),
        'title': $("#title").val(),
        'body': $("#body").val()
    }));
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $( "#send" ).click(function() { sendQuestion(); });
});