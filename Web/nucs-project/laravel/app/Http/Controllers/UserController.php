<?php
/**
 * Created by PhpStorm.
 * User: Ana
 * Date: 5/24/2015
 * Time: 3:57 PM
 */

namespace App\Http\Controllers;


use App\AllowedArticles;
use App\Article;
use App\ArticleVisitor;
use App\Comment;
use App\RateOfArticleModel;
use App\User;
use Carbon\Carbon;
use League\Flysystem\Exception;

class UserController extends Controller{
    public function getAllUsers()
    {
        $users = User::all()->toArray();
        var_dump($users);
        return $users;
    }

    public function addUser($username,$password,$email)
    {
        $user =  User::create(
            [
                'name'=>$username,
                'email'=>$email,
                'password'=>$password,
                'links'=>0,
                'registryDate'=>Carbon::now(),
                'lastLogin'=>Carbon::now(),
                'type'=>'user',
                'facebookUserName'=>' ',
                'twitterUserName'=>' ',
                'deliciousUserName'=>' ',
                'photoURL'=>' '
            ]
        );

    }

    public function addTest()
    {
        try
        {
            UserController::addUser("asas","asadad","dawfwwdfg");
            echo ' insert ok';
            UserController::addUser("asas","asadad","dawfwg");
            echo " insert ok ";
        }
        catch (\Exception $e )
        {
            echo "eroare ";
        }
    }

    public function deleteUser($email)
    {
        $rez=false;
        $result = User::where('email', '=', $email);
        if($result!=null) $rez = $result->delete();
        return $rez;
    }

    public function removeTest()
    {
        if(UserController::deleteUser('email utilizator2')==true) echo 'user deleted';
        else echo 'email not found';
    }

    public function blockUser($email)
    {
        $update = User::where('email', '=', $email)->update(array('blocked' =>'yes'));
        var_dump($update);
        return $update;
    }

    public function testBlock()
    {
        echo UserController::blockUser('email utilizat124or2');
    }

    public function editUser($camp,$valoare,$email)
    {
        $update = User::where('email', '=', $email)->update(array($camp =>$valoare));
        var_dump($update);
        return $update;
    }

    public function testUpdate()
    {
        try{
            echo UserController::editUser('name','hahahhahahahahhahah','email utilizat124or2');
            echo UserController::editUser('nawerme','hahahhahahahahhahah','email utilizat124or2');
        }
       catch (\Exception $e)
       {
           echo 'camp invalid';
       }
    }

    public function getLast5viewedArticle($userid)
    {
        $art = ArticleVisitor::ByUser($userid)->orderBy('adddate','DESC')->take(5)->get()->toArray();
       // var_dump($art);

        $i =0;
        foreach($art as $visited)
        {
            $id = $visited['articleid'];
            $ids[$i] = Article::find($id)->toArray();
            $i = $i +1;
        }

        var_dump($ids);
        return $ids;
    }

    public function testViewed()
    {
        UserController::getLast5viewedArticle(1);
    }

    public function getLast5commentedArticle($userid)
    {
        $art = Comment::ByUser($userid)->orderBy('publishedDate','DESC')->take(5)->get()->toArray();
        //var_dump($art);
        $i =0;
        foreach($art as $visited)
        {
            $id = $visited['articleid'];
            $ids[$i] = Article::find($id)->toArray();
            $i = $i +1;
        }

        var_dump($ids);
        return $ids;
    }

    public function testCommented()
    {
        UserController::getLast5commentedArticle(1);
    }


    public function getLast5reviewedArticle($userid)
    {
        $art = RateOfArticleModel::ByUser($userid)->orderBy('published_at','DESC')->take(5)->get()->toArray();
        //var_dump($art);
        $i =0;
        foreach($art as $visited)
        {
            $id = $visited['articleid'];
            $ids[$i] = Article::find($id)->toArray();
            $i = $i +1;
        }

        var_dump($ids);
        return $ids;
    }

    public function testReviewed()
    {
        UserController::getLast5reviewedArticle(2);
    }

    public function numberOfArticlesViewed($userId){

        $result = ArticleVisitor::whereIn('userId',array( $userId,-1))->count();

        return $result;


    }

    public function testNumberOfArticles(){
        echo UserController::numberOfArticlesViewed(1);
    }

    public function numberOfArticlesToView($userId){
        $result1 = ArticleVisitor::whereIn('userId',array( $userId,-1))->count();
        $result =AllowedArticles::whereIn('userId',array( $userId,-1))->count() - $result1;
        return $result;


    }

    public function testNumberOfArticlesToView(){
        echo UserController::numberOfArticlesToView(2);
    }
}