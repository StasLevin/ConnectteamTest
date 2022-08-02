<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" >
    <title>Test Details</title>
    <link href="../../css/status_colors.css" rel="stylesheet">
    <link href="../../css/general_page.css" rel="stylesheet">
    <link href="../../css/test_page.css" rel="stylesheet">
    <link href="../../css/lightbox.css" rel="stylesheet">

</head>
<body id="page">
<div id="container">
    <div id="name" class="full_width big"></div>
    <h1>Results for date <span id="timestamp"></span></h1>

    <div>
        <h2 id="description"></h2>

        <h3 id="propTblH" class="pointer" onclick="javascript:$('#propTbl').toggle(200);">Test Properties (click)</h3>
        <table id='propTbl' class='smallTbl' style="display: none">
            <tbody/>
        </table>

        <h3 id="paramTblH" class="pointer" onclick="javascript:$('#paramTbl').toggle(200);">Test Parameters (click)</h3>
        <table id='paramTbl' class='smallTbl' style="display: none">
            <tbody/>
        </table>

    </div>

    <br/><br/>
    <div>
        <button type="button" id="detailsDivExpandAll">+ Expand All</button>
        <button type="button" id="detailsDivCollapseAll">- Collapse All</button>
        <div id="detailsDiv" style="border: 1px solid #D1D1D1;padding:5px;"></div>
    </div>

</div>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="test.js"></script>
<script type="text/javascript" src="../../execution.js"></script>
<script type="text/javascript" src="../../controllers/controllerUtils.js"></script>
<script type="text/javascript" src="../../controllers/testController.js"></script>
<script type="text/javascript" src="../../js/lightbox-2.6.min.js"></script>
<script type="text/javascript">
                function populateTestDetails() {
                    testController($("#container"));
                }
                function hideUneededHeaders() {
                    if ($("#paramTbl tbody").children().length === 0) {
                        $("#paramTblH").hide();
                    }
                    if ($("#propTbl tbody").children().length === 0) {
                        $("#propTblH").hide();
                    }
                }

                $(document).ready(function() {
                    populateTestDetails();
                    hideUneededHeaders();
                });

    </script>
</body>
</html>