function json_fetch_session_data() {

    /*
    fetch the email or username(tba) of the logged in user.
    this param will be used to determine the path of the classifier in the cortex api
     */

    var xhr = new window.XMLHttpRequest();
    xhr.open('GET', 'http://192.168.0.150:8090/session/user-info');
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send();

    return xhr;
}

function requestJson(url) {
    var xhr = new window.XMLHttpRequest();
    xhr.open('GET', url);
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send();

    return xhr;
}

function getServerAddress() {
    return 'http://192.168.0.150:8091';
}