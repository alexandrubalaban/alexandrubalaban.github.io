<?php
/**
 * Created by PhpStorm.
 * User: Ana
 * Date: 5/24/2015
 * Time: 2:04 PM
 */

namespace App\Http\Controllers;


use App\FactsModel;
use Psy\Util\String;

class FactsController extends Controller
{

    public function getAllFacts()
    {
        // returneaza un array cu toate datele din tabel
        $all = FactsModel::all();
        return $all->toArray();
    }

    public function addFact($factText)
    {
        $fact = FactsModel::create([
            'text'=>$factText
        ]);
        $result = $fact->save();
        return $result;
    }

    public function removeFactById($factId)
    {
        $rez=false;
        $result = FactsModel::find($factId);
        if($result!=null) $rez = $result->delete();
        return $rez;
    }

    public function removeFactByText($text)
    {
        $rez=false;
        $result = FactsModel::where('text', '=', $text);
        if($result!=null) $rez = $result->delete();
        return $rez;

    }

    public function getRandomFact()
    {
        $numberOfRows = FactsModel::all()->count();
        $rand = rand ( 1 , $numberOfRows ) ;
        $randFact = FactsModel::take(1)->skip($rand)->get()->toArray();
        var_dump($randFact);
        return $randFact[0]['text'];
    }

    public function addTest()
    {
        if(FactsController::addFact('my text1') == true) echo "inserted";
        else echo " not inserted";
        if(FactsController::addFact('my text2') == true) echo "inserted";
        else echo " not inserted";
        if(FactsController::addFact('my text3') == true) echo "inserted";
        else echo " not inserted";
        if(FactsController::addFact('my text5') == true) echo "inserted";
        else echo " not inserted";
        if(FactsController::addFact('my text4') == true) echo "inserted";
        else echo " not inserted";
        if(FactsController::addFact('my text6') == true) echo "inserted";
        else echo " not inserted";
    }

    public function removeTest()
    {
        if(FactsController::removeFactById(1)==true) echo "fact cu id 1 removed";
        else echo "fact cu id 1 not  removed";
        if(FactsController::removeFactById(1111)==true) echo "fact cu id 1111  removed";
        else  echo "fact cu id 1111 not removed";
        if(FactsController::removeFactByText('my text1')==true) echo "fact cu text my text1 removed";
        else echo "fact cu text my text1 not removed";
        if(FactsController::removeFactByText('my text111')==true) echo "fact cu text my text111 removed";
        else echo "fact cu text my text111 removed";
    }

    public function randomTest()
    {
        echo FactsController::getRandomFact();
    }
}