<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <title>Home</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
    <section>
        <div id="filters">filtres latéraux</div>
        <div>
            <div>
                <label for="search">Votre recherche : </label>
                <input id="search" type="text" name="search">
                <button id="reset" onclick="reset_search()">Reset</button>
            </div>
            <div id="result"></div>
        </div>
    </section>

    <script>
        const search_elem = $("#search");
        const result_elem = $("#result");

        function reset_search() {
            search_elem.val("");
            result_elem.empty().append("En attente de votre prochaine recherche...");
        }
        reset_search();

        function searchProducts(search_input, target_elem) {
            $.ajax({
                url:"/products/search",
                type:"POST",
                data:JSON.stringify({
                    input:search_input
                }),
                contentType : 'application/json; charset=utf-8',

                success: function(resp) {
                    console.log(resp);
                    target_elem.empty().append(resp);
                },
                error: function(){
                    console.log("failure");
                    target_elem.empty().append("Pas de résultat");
                }
            });
        }

        search_elem.on("input",() => {
            const input = search_elem.val();
            if (input === "") {
                reset_search();
                return;
            }
            searchProducts(input, result_elem);
        });
    </script>
</body>
</html>
