
<?php
if (isset($_GET['execJAR'])){
    chdir('C:\Users\Ionut\Desktop\webbb\out\artifacts\webHoseAPI_jar');
    exec('java -jar webHoseAPI.jar 1 1 1 yahoo.com');}
?>



<html>
<body>
<div id = "page">
    <div id="execut-mod-2" class="col-md-12">
        <button id="execBTN" type="button"  onclick="executeJAR()" class="btn btn-primary btn-lg">Executa</button>
    </div>
</div>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>