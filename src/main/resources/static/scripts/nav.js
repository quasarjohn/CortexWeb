function logout() {
    var wnd = window.open(getWebServerAddress() + "/logout");
    wnd.close();
}

$(document).ready(function () {
    console.log("DOCUMENT READY");
    $("#logoutLi").click(function () {
        logout();
    })
});