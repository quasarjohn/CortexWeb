$(".unfollowBtn").click(function () {
    $(this).parentsUntil(".following_user").hide();
});

