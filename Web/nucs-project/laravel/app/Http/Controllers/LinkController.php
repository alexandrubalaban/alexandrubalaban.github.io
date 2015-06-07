<?php
/**
 * Created by PhpStorm.
 * User: Ana
 * Date: 5/24/2015
 * Time: 5:00 PM
 */

namespace App\Http\Controllers;


use App\LinkModel;
use Carbon\Carbon;

class LinkController extends Controller
{
    public function addLink($userId,$Url,$Key)
    {
        $link = \App\LinkModel::create([
            'userId'=>$userId,
            'urlAdress'=>$Url,
            'keywords'=>$Key,
            'accepteddd'=>'no',
            'published_at'=>Carbon::now()
        ]);


        return $link->save();
    }

    public function addTest()
    {
        try
        {
            echo LinkController::addLink(1,'2','1212');
        }
        catch (\Exception $e)
        {
            echo 'date invalide';
        }
    }

    public function getAllLinks()
    {
        $links = LinkModel::all()->toArray();
        var_dump($links);
        return $links;
    }

    public function deleteLink($url)
    {
        $rez=false;
        $result = LinkModel::where('urladress', '=', $url);
        if($result!=null) $rez = $result->delete();
        return $rez;
    }

    public function removeTest()
    {
        return LinkController::deleteLink('124242352');
    }
}