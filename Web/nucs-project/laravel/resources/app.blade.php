
<?php
if (isset($_GET['execJAR'])){
    chdir('C:\Users\Ionut\Desktop\webHoseAPI\out\artifacts\webHoseAPI_jar');
    exec('java -jar webHoseAPI.jar');}
?>


<!doctype html>
<html lang ="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    @yield('content')
</div>
</body>
</html>