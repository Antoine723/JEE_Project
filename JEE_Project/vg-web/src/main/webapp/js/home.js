$(() => {
    const search_input = $("input[name='search']");
    const result_elem = $("#result");

    $("#reset").on("click", () => {
        search_input.val("");
        searchProducts();
    }).click();

    search_input.on("input",() => searchProducts());
    $(".search_btn").on("click",() => searchProducts());

    function searchProducts() {
        const search = search_input.val();

        const sort_by = $("input[name='sort_by']:checked").val();
        const order_by = $("input[name='order_by']").is(":checked");

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