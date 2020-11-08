function showQr() {
    document.getElementById('qrcode').style.visibility = "visible";
}

$("#copyButton").click(function () {
    $("#shortenedLink").select();
    document.execCommand('copy');
});