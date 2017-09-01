var stompClient = null;


function connect() {
    var socket = new SockJS('/cortex-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
    });
}

function sendQuestion() {
    stompClient.send("/new-question", {}, JSON.stringify({
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