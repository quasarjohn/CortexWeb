$(".unfollowBtn").click(function () {
    $(this).parentsUntil(".following_user").hide();
});

$(".userProfilePicture").click(function () {
    $(".file1").click();
});

$(".centerPos").click(function () {
    $(".file1").click();
});

$(".file1").change(function () {
    $(".btnSubmit").click();
});

$(".btnSubmit").click(function (event) {
    event.preventDefault();
    uploadImage();
});

function uploadImage() {
    var form = $(".fileUploadForm")[0];
    var data = new FormData(form);
    console.log(data);

    $.ajax({
        type: "POST",
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            'Access-Control-Allow-Credentials': true,
        },
        enctype: 'multipart/form-data',
        url: "/upload/image",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            console.log("Success bitch, i love ajax <3");
            window.location.reload();
        },
        error: function (e) {
            console.log("Error : " + e);
        }
    });
}