function checkIfUserIsAuthenticated() {

    var referrer = window.location.href;
    localStorage.setItem("referrer", referrer);

    console.log("FETCHING DATA");

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
            if (data.email == null)
            //TODO display login modal instead of redirecting to sign in page
            // displayLoginModal();
                redirectToSignInPage();
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function redirectToSignInPage() {
    window.location = getWebServerAddress() + "/sign-in";
}


function displayLoginModal() {
    console.log("DISPLAY MODAL");
    $("#myModal").modal();
}


function startSecurityCheck() {
    checkIfUserIsAuthenticated();
}


startSecurityCheck();
