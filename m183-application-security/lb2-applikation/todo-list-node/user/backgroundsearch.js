function getHtml(req) {
    // userid is serialised with JSON.stringify so it cannot break out of the JS literal
    const userid = JSON.stringify(req.session?.userid ?? null);
    const csrf = JSON.stringify(req.session?.csrfToken ?? '');

    return `
<section id="search">
    <h2>Search</h2>
    <form id="form" method="post" action="">
        <input type="hidden" id="searchurl" name="searchurl" value="/search/v2/">
        <div class="form-group">
            <label for="terms">terms</label>
            <input type="text" class="form-control size-medium" name="terms" id="terms">
        </div>
        <div class="form-group">
            <label for="submit"></label>
            <input id="submit" type="submit" class="btn size-auto" value="Submit">
        </div>
    </form>
    <div id="messages">
        <div id="msg" class="hidden">The search is running. Results will be visible soon.</div>
        <div id="result" class="hidden"></div>
    </div>
    <script>
        $(document).ready(function () {
            var userid = ${userid};
            var csrfToken = ${csrf};
            $('#form').validate({
                rules: { terms: { required: true } },
                messages: { title: 'Please enter search terms.' },
                submitHandler: function (form) {
                    var provider = $("#searchurl").val();
                    var terms = $("#terms").val();
                    $("#msg").show();
                    $("#result").html("");
                    $.post("search", { provider: provider, terms: terms, userid: userid, _csrf: csrfToken }, function(data){
                        $("#result").html(data);
                        $("#msg").hide(500);
                        $("#result").show(500);
                    });
                    return false;
                }
            });
        });
    </script>
</section>`;
}

module.exports = { html: getHtml };
