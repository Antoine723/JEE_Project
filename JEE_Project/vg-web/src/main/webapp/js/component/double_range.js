$(() => {
    function price_min_input_change() {
        const val = $("#price_min").attr("max")-$("#price_min").val()+parseFloat($("#price_min_abs").val());
        $("#price_max").attr("min", val);
        $("#price_min_val").text(val+"€");
    }
    function price_max_input_change() {
        const val = $("#price_max").val();
        $("#price_min").attr("max", val);
        $("#price_max_val").text(val+"€");
    }
    price_min_input_change();
    price_max_input_change();
    $("#price_min").on('change input', () => price_min_input_change());
    $("#price_max").on('change input', () => price_max_input_change());
});