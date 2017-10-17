$(document).ready(function () {
    $(".commentTextarea").hide();
});

$(".addComment p").click(function () {
    $(".commentTextarea").show();
});

$(".cancelComment").click(function () {
    $(".commentTextarea").hide();
});

var stompClient = null;

function connect() {
    var socket = new SockJS('/cortex-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/community' + window.location.pathname, function (answer) {
            // showQuestion(JSON.parse(question.body));
            showAnswer(JSON.parse(answer.body))
        });
    });
}

function sendAnswer() {
    // alert('sending');
    stompClient.send("/new-answer" + window.location.pathname, {}, JSON.stringify({
        'question_number': 1,
        'body': $("#user_input").val(),
    }));
}


$(function () {
    $("#myForm").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function () {
        sendAnswer();
    });
});

function showAnswer(answer) {
    // alert("prepending");
    console.log(answer)

    $("#body").html(answer.body);

    $("#answers").prepend('<div class="row contentRow">\n' +
        '            <div class="col-sm-1">\n' +
        '            <span class="fa fa-thumbs-up" data-toggle="tooltip" data-placement="bottom" title="This question shows research effort;\n' +
        '            it is useful and clear"></span>\n' +
        '                <h3>0</h3>\n' +
        '                <p>votes</p>\n' +
        '                <span class="fa fa-thumbs-up fa-rotate-180" data-toggle="tooltip" data-placement="bottom" title="This question does not ' +
        '            show any research effort; it is unclear or not useful"></span>\n' +
        '            </div>\n' +
        '            <div class="col-sm-11"> <!-- content of question -->\n' +
        '                <p class="content">' + answer.body + '</p>\n' +
        '\n' +
        '                <div class="by">\n' +
        '                    <p >' + "answered " + answer.parsed_time + '</p>\n' +
        '                    <div class="row">\n' +
        '                        <div class="col-sm-5">\n' +
        '                            <img class="img-responsive" src="images/dev1.png"/>\n' +
        '                        </div>\n' +
        '                        <div class="col-sm-7">\n' +
        '                            <a href="#">' + answer.username + '</a>\n' +
        '                            <p class="reputationTooltip" data-toggle="tooltip" data-placement="bottom"\n' +
        '                               title="Reputation score">' + answer.reputation_score + '</p>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <hr class="endLine"/>')

    window.location.reload();
}

$(document).ready(function () {
    connect();
});