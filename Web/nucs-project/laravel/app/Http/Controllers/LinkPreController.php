<?php
/**
 * Created by PhpStorm.
 * User: Ana
 * Date: 5/24/2015
 * Time: 5:03 PM
 */

namespace App\Http\Controllers;


use App\DefinedLinksModel;
use Carbon\Carbon;

class LinkPreController extends Controller
{
    public function addLink($Url,$Key,$categId)
    {
        $link = DefinedLinksModel::create([
            'URL'=>$Url,
            'keyWords'=>$Key,
            'published_at'=>Carbon::now(),
            'categoryId'=>$categId
        ]);
        return $link->save();
    }

    public function addTest()
    {
        try
        {
            echo LinkPreController::addLink('linwk','1212key',1);
        }
        catch (\Exception $e)
        {
            echo 'date invalide';
        }
    }

    public function getAllLinks()
    {
        $links = DefinedLinksModel::all()->toArray();
        var_dump($links);
        return $links;
    }

    public function deleteLink($url)
    {
        $rez=false;
        $result = DefinedLinksModel::where('URL', '=', $url);
        if($result!=null) $rez = $result->delete();
        return $rez;
    }

    public function removeTest()
    {
        return LinkPreController::deleteLink('link');
    }
}