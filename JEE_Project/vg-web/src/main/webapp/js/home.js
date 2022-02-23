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

        const min_price = $("#price_min").attr("max")-$("#price_min").val()+parseFloat($("#price_min_abs").val());
        const max_price = $("#price_max").val();

        const stars_input = $("input[name='stars']:checked");
        const min_score = stars_input.length === 0 ? 0 : parseInt(stars_input.val());

        $.ajax({
            url:"/products/search",
            type:"POST",
            data:JSON.stringify({
                input:search,
                sort_by:sort_by,
                sort_asc:order_by,
                consoles:selected,
                min_price:min_price,
                max_price:max_price,
                min_score: min_score,
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
                            '<article id="'+item["id"]+'">'+
                                (item.hasOwnProperty("consoles") ? '<div class="carrousel"></div>'
                                : '<a href="/product/'+item["id"]+'"><img src="/image/'+item["img"]+'" alt="product_img"/></a>')+
                                '<h3>'+item["name"]+'</h3>'+
                                '<p>Note utilisateurs : '+(item["rating"] === null ? 'N.A.' : item["rating"]+'/5')+'</p>'+
                                '<p>Prix : '+item["price"]+'€</p>'+
                            '</article>',
                        "");
                }
                result_elem.empty().append(html);
                $.each(result, function(index, item) {
                    if (!item.hasOwnProperty("consoles")) {
                        return;
                    }
                    const consoles = item["consoles"].filter(function (elem) {
                        return selected.length === 0 ? true : $.inArray(elem, selected) !== -1;
                    });
                    $(`#result article#${item["id"]} .carrousel`).append(
                        '<div class="image-holder" style="width: '+200*consoles.length+'px;">'+
                            consoles.reduce((subtotal, subitem) => subtotal +=
                                '<a href="/product/'+item["id"]+"/"+subitem+'">'+
                                    '<img src="/image/'+item["img"]+"_"+subitem+'" alt="product_'+subitem+'_img"/>'+
                                '</a>'
                            , "")+
                        '</div>'+
                        '<div class="button-holder" style="width: '+3*consoles.length+'em;">'+
                            consoles.reduce((subtotal, subitem, i) => subtotal +=
                                '<div id="carousel-btn'+item["id"]+'_'+i+'">'+subitem+'</div>'
                            , "")+
                        '</div>'
                    );

                    for(let i = 0; i < consoles.length; ++i) {
                        $(`.carrousel #carousel-btn${item["id"]}_${i}`).on("click", () => {
                            $(`article#${item["id"]} .image-holder`).css({"left": -200*i+"px"});
                        });
                    }
                });
            },
            error: function(){
                console.log("failure");
                result_elem.empty().append("Pas de résultat");
            }
        });
    }
});