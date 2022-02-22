$(() => {
    const search_input = $("input[name='search']");
    const order_by_input = $("input[name='order_by']");
    const sort_by_input = $("input[name='sort_by']");
    const result_elem = $("#result");

    function reset_search() {
        search_input.val("");
        result_elem.empty().append("En attente de votre prochaine recherche...");
    }
    $("#reset").on("click", () => reset_search());
    reset_search();

    search_input.on("input",() => searchProducts());
    order_by_input.on("click",() => searchProducts());
    sort_by_input.on("click",() => searchProducts());

    function searchProducts() {
        const search = search_input.val();
        if (search === "") {
            reset_search();
            return;
        }

        const sort_by = $("input[name='sort_by']:checked").val();
        const order_by = order_by_input.is(":checked");

        const selected = [];
        $("input[name='console_choice']:checked").each(function() { selected.push($(this).val()); });

        $.ajax({
            url:"/products/search",
            type:"POST",
            data:JSON.stringify({
                input:search,
                sort_by:sort_by,
                sort_asc:order_by,
                consoles:selected
            }),
            contentType : 'application/json; charset=utf-8',

            success: function(resp) {
                const result = JSON.parse(resp);
                let html;
                if (!Array.isArray(result) || !result.length) {
                    html = "Pas de résultat pour votre recherche";
                }
                else {
                    html = result.reduce((total, item) => total +=
                            '<div>'+
                            '<hr>'+
                            '<img src="/image/'+item["img"]+(item.hasOwnProperty("consoles") ? "_"+item["consoles"][0] : "")+'" style="max-height: 400px; max-width: 400px;" alt="product_img"/>'+
                            '<div>'+
                            '<h3>'+item["name"]+'</h3>'+
                            '<p>note : '+item["rating"]+'/5</p>'+
                            '<p>'+item["price"]+'€</p>'+
                            '</div>'+
                            '<hr>'+
                            '</div>',
                        "");
                }
                result_elem.empty().append(html);
            },
            error: function(){
                console.log("failure");
                result_elem.empty().append("Pas de résultat");
            }
        });
    }
});