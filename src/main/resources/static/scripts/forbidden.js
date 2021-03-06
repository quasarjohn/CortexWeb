function checkIfUserIsAuthenticated() {

    $.ajax({
        type: "GET",
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            'Content-Type': 'application/json'
        },
        url: getWebServerAddress() + "/session/user-info",
        cache: false,
        timeout: 600000,
        async: false,
        success: function (data) {
            if (data.email == null) {
                displayLoginModal();
            }
            else {
                console.log("USER IS AUTHENTICATED");
                //prevents loop of google login
                $("#sb").removeClass("g-signin2");
            }
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function displayLoginModal() {
    console.log("DISPLAY MODAL");

    $('#myModal').modal({
        backdrop: 'static',
        keyboard: false
    });

    $("#myModal").modal();
}

function startSecurityCheck() {
    checkIfUserIsAuthenticated();
}

startSecurityCheck();
