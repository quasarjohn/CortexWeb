function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.

    sendTokenToServer(googleUser);
}

function sendTokenToServer(googleUser) {
    var profile = googleUser.getBasicProfile();

    var id_token = googleUser.getAuthResponse().id_token;
    var xhr = new XMLHttpRequest();
    xhr.open('POST', getWebServerAddress() + "/test/login");
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.onload = function () {

        $("#title").html("Welcome to Cortex");
        $("#sas").html("Signed in as " + profile.getName());

        setTimeout(function () {
            $("#myModal").modal('hide');
            window.location = localStorage.getItem("referrer")
        }, 1000);
    };
    xhr.send('token=' + id_token);
}
