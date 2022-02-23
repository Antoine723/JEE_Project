$(() => {
    function checkUser() {
        if ($("#gotUser").val() !== 'true') {
            const prefix = $("#urlPrefix").val();
            window.location = `${prefix}/user/connect`;
        }
    }
});