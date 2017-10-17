//check if user is logged in
$(document).ready(function () {
    var xhr = json_fetch_session_data();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            var json_data = JSON.parse(xhr.responseText)

            if(json_data.email != null) {
                // $("#signin").html("SIGN OUT");
                $("#signin").hide();

                $("#signin").click(function (event) {
                    event.preventDefault();
                    window.location = 'http://localhost:8090/logout';
                })
            }
        }
    }
});
