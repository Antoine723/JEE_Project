$(() => {
    $("#quantity").on("change", function () {
        const quantity = parseInt($("#productQuantity").val());
        if (this.val() < 1) {
            this.val(1);
        } else if (this.val() > quantity) {
            this.val(quantity);
        }
    })

    $("#decreaseQuantity").on("click", () => {
        const val = parseInt($("#quantity").val());
        if (val > 1) {
            $("#quantity").val(val - 1);
        }
    });

    $("#increaseQuantity").on("click", () => {
        const maxQuantity = parseInt($("#productQuantity").val());
        const val = parseInt($("#quantity").val());
        if (val < maxQuantity) {
            $("#quantity").val(val + 1);
        }
    });

    $("#addToBasket").on("click", () => {
        checkUser();

        const quantity = $("#quantity").val();
        const productId = parseInt($("#productId").val());

        $.ajax({
            url: "/addToBasket",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                "quantity": quantity,
                "productId": productId
            }),
            success: function () {
                //TODO
            },
            error: function () {
                //TODO
            }
        });
    });
});