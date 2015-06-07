<?php
/**
 * Created by PhpStorm.
 * User: Ionut
 * Date: 02.06.2015
 * Time: 0:51
 */

namespace App\Http\Controllers;


class testSomething extends Controller{
public function  testSome(){
    DB::table('categories')->insert([
        'article_id'=>1,
        'category_id'=>$category_id,

    ]);

   /*
    $category_id = DB::table('categories')->where('name','=',1);
    return $category_id;    */
}

}