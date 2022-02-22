$(() => {
    $("#editAddress").on("click", () => {
        $("#labelAddress").addClass("d-none");
        $("#inputAddress").removeClass("d-none");
        $("#editAddress").addClass("d-none");
        $("#saveAddress").removeClass("d-none");
    });

    $("#saveAddress").on("click", () => {
        $("#labelAddress").removeClass("d-none");
        $("#inputAddress").addClass("d-none");
        $("#editAddress").removeClass("d-none");
        $("#saveAddress").addClass("d-none");
    });
});