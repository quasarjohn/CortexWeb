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
    $("#answers").prepend('<div class="row contentRow">\n' +
        '            <div class="col-sm-1">\n' +
        '            <span class="fa fa-thumbs-up" data-toggle="tooltip" data-placement="bottom" title="This question shows research effort;\n' +
        '            it is useful and clear"></span>\n' +
        '                <h3>0</h3>\n' +
        '                <p>votes</p>\n' +
        '                <span class="fa fa-thumbs-up fa-rotate-180" data-toggle="tooltip" data-placement="bottom" title="This question does not\n' +
        '            show any research effort; it is unclear or not useful"></span>\n' +
        '            </div>\n' +
        '            <div class="col-sm-11"> <!-- content of question -->\n' +
        '                <p class="content">' + answer.body + '</p>\n' +
        '\n' +
        '                <div class="by">\n' +
        '                    <p >' + answer.parsed_time + '</p>\n' +
        '                    <div class="row">\n' +
        '                        <div class="col-sm-5">\n' +
        '                            <img class="img-responsive" src="images/dev1.png"/>\n' +
        '                        </div>\n' +
        '                        <div class="col-sm-7">\n' +
        '                            <a href="#">' + answer.username + '</a>\n' +
        '                            <p class="reputationTooltip" data-toggle="tooltip" data-placement="bottom"\n' +
        '                               title="Reputation score">6</p>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '\n' +
        // '                <div class="comment clearRight"> <!-- comment -->\n' +
        // '                    <hr/>\n' +
        // '                    <p class="inline">Why aren\'t you using a onClickListener for the create lobby button? And why are you\n' +
        // '                        setting first the child\n' +
        // '                        game \'s value to be "CHANGED" and then overwrite it? – </p>\n' +
        // '                    <a href="#" class="username inline">Catalin Ghita</a>\n' +
        // '                    <p class="commentDateAndTime inline">Aug 26 at 21:39</p>\n' +
        // '                </div>\n' +
        // '\n' +
        // '                <div class="commentTextarea clearRight displayNone">\n' +
        // '                    <hr/>\n' +
        // '                    <form>\n' +
        // '                        <div class="form-group">\n' +
        // '                            <textarea class="form-control" rows="2" placeholder="Comment here..."></textarea>\n' +
        // '                        </div>\n' +
        // '\n' +
        // '                        <button type="submit" class="sendComment btn btn-default inline">Send</button>\n' +
        // '                        <button type="submit" class="cancelComment btn btn-default inline">Cancel</button>\n' +
        // '                    </form>\n' +
        // '                </div>\n' +
        '\n' +
        '                <div class="addComment clearRight" style="display: block;">\n' +
        '                    <hr/>\n' +
        '                    <p>Add a comment</p>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <hr class="endLine"/>')

}

$(document).ready(function () {
    connect();
});