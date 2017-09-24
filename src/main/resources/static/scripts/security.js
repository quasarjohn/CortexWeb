function pageRequiresAuthentication() {
    /*
    TODO send the url of the requested page to the server and check if it requires authentication
     */

    var referrer = window.location.href;
    localStorage.setItem("referrer", referrer);

    //save the link of the caught page so we can return to it after successful login
    // localStorage.setItem("referrer", referrer);

    return true;
}

function checkIfUserIsAuthenticated() {

    console.log("FETCHING DATA");

    var xhr = sync_json_fetch_session_data();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var jsonData = JSON.parse(xhr.responseText);
            var user = jsonData.email;
            console.log(user);

            if (user == null)
                redirectToSignInPage();

        }
    };
}

function redirectToSignInPage() {
    window.location = getWebServerAddress() + "/sign-in";
}

function startSecurityCheck() {
    var p = pageRequiresAuthentication();

    if (p) {
        checkIfUserIsAuthenticated();
    }
}

startSecurityCheck();
