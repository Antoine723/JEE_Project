$(() => {
    $("buy").on("click", () => {
        const prefix = $("#urlPrefix").val();
        window.location = `${prefix}/payment`;
    });
});