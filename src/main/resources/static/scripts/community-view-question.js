$(document).ready(function() {
   $(".commentTextarea").hide();
});

$(".addComment p").click(function() {
   $(".commentTextarea").show();
});

$(".cancelComment").click(function() {
   $(".commentTextarea").hide();
});