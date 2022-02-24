function checkUser() {
    if ($("#gotUser").val() !== 'true') {
        window.location = '/user/connect';
    }
}
