<?php namespace App\Http\Controllers;

use App\ArticleVisitor;
use Carbon\Carbon;

class DatabaseController  extends Controller
{
    public function putValuesInArticle()
    {
        $article = new \App\Article();

        for($i=1;$i<=50;$i++)
        {
            $article = new \App\Article();
            $article->title = 'my title' . $i%5;
            $article->bodyText=' my text ' . $i;
            $article->excerpt="excerpt".$i;
            $article->likes=6;
            $article->sourceName='my name ';
            $article->linkURL=' url ';
            $article->views=3;
            $article->published_at=Carbon::now();
            $article->categoryId=$i%9+1;

            $done = $article->save();
        }






        $all = \App\article::all();
        return $all->toArray();
    }

    public function putValueInUsers(){
        $user =  \App\User::create(
            [
                'name'=>'ionuț',
                'email'=>'vakriu_ionutzy@yahoo.com',
                'password'=>'1234'
            ]
        );


        $user->save();
        $all = \App\User::all();
        return $all->toArray();
    }
    public function putValuesInTag(){
        $tag = \App\Tag::create([
            'name'=>'android'
        ]);
    }
public function putValuesInType(){




}

    public function putValueInArticleVisitor(){
        $articleVisitor = ArticleVisitor::create([
            'articleId'=>1,
            'userId'=>1,
            'addDate'=>Carbon::now()
        ]);

        $articleVisitor = ArticleVisitor::create([
            'articleId'=>2,
            'userId'=>1,
            'addDate'=>Carbon::now()
        ]);
        $articleVisitor = ArticleVisitor::create([
            'articleId'=>3,
            'userId'=>1,
            'addDate'=>Carbon::now()
        ]);
        $articleVisitor = ArticleVisitor::create([
            'articleId'=>4,
            'userId'=>1,
            'addDate'=>Carbon::now()
        ]);

        $articleVisitor = ArticleVisitor::create([
            'articleId'=>1,
            'userId'=>2,
            'addDate'=>Carbon::now()
        ]);

        $articleVisitor = ArticleVisitor::create([
            'articleId'=>2,
            'userId'=>2,
            'addDate'=>Carbon::now()
        ]);



        $all = ArticleVisitor::all();
        return $all->toArray();
    }

    public function putValuesInComments(){
        $comm = \App\Comment::create([
            'body'=>'ionut e jmecher:))',
            'articleId'=>1,
            'PUBLISHEDDATE'=>Carbon::now(),
            'userId'=>1,
        ]);
        $comm ->save();
        $all = \App\Comment::all();
        return $all->toArray();
    }

    public function putValueInRateValues(){

        $rateValue  = \App\RateOfArticleModel::create(
            [
                'articleId' =>1,
                'userId'=>1,
                'value'=>10,
                'published_at'=>Carbon::now()
            ]
        );
        $rateValue->save();
        $all = \App\RateOfArticleModel::all();
        return $all->toArray();

    }

    public function putDataInCategories(){


        $categorie = \App\Categories::create([
            'name' => 'LANGUAGES' ,
        ]);
        $categorie2 = \App\Categories::create([
            'name' => 'TECHNOLOGY' ,
        ]);
        $categorie3 = \App\Categories::create([
            'name' => 'INGINEERING' ,
        ]);
        $categorie4 = \App\Categories::create([
            'name' => 'INDUSTRY' ,
        ]);
        $categorie4 = \App\Categories::create([
            'name' => 'OS' ,
        ]);


        //$categorie->save();
        $all = \App\CategoriesModel::all();
        return $all->toArray();
    }

    public function putDataInDefinedLinks(){

        $defindedLinks = \App\DefinedLinksModel::create(
            [
                'URL'=>'URLas ',
                'keyWords'=>'civinte',
                'categoryId'=>1
            ]);
        $defindedLinks -> save();
        $all = \App\DefinedLinksModel::all();
        return $all->toArray();

    }

    public function putDataInFacts(){
        $facts = \App\FactsModel::create([
            'text'=>'text interesant'
        ]);
        $facts->save();
        $all =\App\FactsModel::all();
        return $all->toArray();
    }

    public function putDataOnPreferences(){
        $preference = \App\PreferencesModel::create(
            [
                'userId'=>1,
        'categoryId'=>1
            ]);
        $preference->save();
        $all = \App\PreferencesModel::all();
        return $all->toArray();

    }

    public function putDataOnAllowdUsers(){
        $allowdLink = \App\AllowedArticles::create([
            'articleId' => 4,
            'userId' =>1
        ]);
        $allowdLink2 =\App\AllowedArticles::create([
            'articleId'=> 5,
            'userId'=>1
        ]);
        $allowdLink3 =\App\AllowedArticles::create([
            'articleId'=> 5,
            'userId'=>2
        ]);

        $all = \App\AllowedArticles::all();
        return $all->toArray();
    }
    public function putDataOnLinks(){
        $link = \App\LinkModel::create([
                'userId'=>1,
                'urlAdress'=>'adresa',
                'keywords'=>'cvu',
                'accepted'=>'ce',
                'published_at'=>Carbon::now()
        ]);
        $link->save();
        $all = \App\LinkModel::all();
        return $all->toArray();
    }

}