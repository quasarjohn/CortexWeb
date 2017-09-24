function json_fetch_session_data() {

    var xhr = new window.XMLHttpRequest();
    xhr.open('GET', getWebServerAddress() +'/session/user-info');
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send();

    return xhr;
}

function sync_json_fetch_session_data() {

    var xhr = new window.XMLHttpRequest();
    xhr.open('GET', getWebServerAddress() +'/session/user-info', true);
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
    return 'http://192.168.0.149:8091';
}

function getWebServerAddress() {
    return 'http://localhost:8090';
}