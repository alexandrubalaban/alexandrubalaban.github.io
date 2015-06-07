<?php
/**
 * Created by PhpStorm.
 * User: Ionut
 * Date: 25.05.2015
 * Time: 12:54
 */

namespace App\Http\Controllers;




class WebhoseAPI  extends Controller{
    public function button(){

    return view('pages.api');
}
        public function getApiResult(){
        if (isset($_GET['execJAR'])){
            chdir('C:\Users\Ionut\Desktop\webHoseAPI\out\artifacts\webHoseAPI_jar');
            exec('java -jar webHoseAPI.jar  ');

        }

    }



}