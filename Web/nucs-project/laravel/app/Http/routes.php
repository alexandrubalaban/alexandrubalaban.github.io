<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

use App\Article;
use App\Http\Requests\Request;

Route::get('/', 'PagesController@index');

Route::get('home', 'PagesController@index');



Route::get('popArticles', 'DatabaseController@putValuesInArticle');

Route::get('popUsers', 'DatabaseController@putValueInUsers');

Route::get('popArticleVisitor', 'DatabaseController@putValueInArticleVisitor');

Route::get('popAllowdUsers', 'DatabaseController@putDataOnAllowdUsers');

Route::get('popComments', 'DatabaseController@putValuesInComments');

Route::get('popRate', 'DatabaseController@putValueInRateValues');

Route::get('popCategories', 'DatabaseController@putDataInCategories');

Route::get('popDefinedLinks', 'DatabaseController@putDataInDefinedLinks');

Route::get('popFacts', 'DatabaseController@putDataInFacts');

Route::get('popPreference', 'DatabaseController@putDataOnPreferences');

Route::get('popLinks', 'DatabaseController@putDataOnLinks');

Route::get('webhose','WebhoseAPI@button');


Route::get('popTags','DatabaseController@putValuesInTag');

Route::get('popType','DatabaseController@putValuesInType');

Route::get('facts','FactsController@getAllFacts');
Route::get('facts/add','FactsController@addTest');
Route::get('facts/remove','FactsController@removeTest');
Route::get('facts/random','FactsController@randomTest');

Route::get('article/last5','ArticlesController@getLast5articlesAdded');

Route::get('user/','UserController@getAllUsers');
Route::get('user/add','UserController@addTest');
Route::get('user/remove','UserController@removeTest');
Route::get('user/edit','UserController@testAdd');
Route::get('user/block','UserController@testBlock');
Route::get('user/update','UserController@testUpdate');
Route::get('user/lastViewed','UserController@testViewed');
Route::get('user/lastComm','UserController@testCommented');
Route::get('user/lastRev','UserController@testReviewed');
Route::get('user/testCount','UserController@testNumberOfArticles');
Route::get('user/test','UserController@testNumberOfArticlesToView');


Route::get('link/add','LinkController@addTest');
Route::get('link/remove','LinkController@removeTest');
Route::get('link','LinkController@getAllLinks');

Route::get('linkPre/add','LinkPreController@addTest');
Route::get('linkPre/remove','LinkPreController@removeTest');
Route::get('linkPre','LinkPreController@getAllLinks');


Route::get('search','ArticlesController@search');



Route::get('articles','ArticlesController@index');

Route::get('articlesSS','ArticlesController@testFilter');
Route::get('popular','ArticlesController@popularNews');
Route::get('loadMore','ArticlesController@testLoadMore');
Route::get('loadMoreS','ArticlesController@testGetAnotherArticles');
Route::get('addComm','ArticlesController@testAddComm');
Route::get('bestNews','ArticlesController@bestNews');
Route::get('lastComm','ArticlesController@lastComm');
Route::get('articles/{id}','ArticlesController@show');
Route::post('articles/{id}','CommentsController@create');


Route::get('user/edit/profile/{id}',['middleware' => 'auth','uses' => 'UserProfileController@editProfile']);
Route::post('user/edit/profile/{id}',['middleware' => 'auth','uses' => 'UserProfileController@editUser']);
Route::get('user/addlinks/{id}',['middleware' => 'auth','uses' => 'UserProfileController@addLinks']);
Route::post('user/addlinks/{id}',['middleware' => 'auth','uses' => 'UserProfileController@addYourLink']);
Route::get('user/statistics/{id}',['middleware' => 'auth','uses' => 'UserProfileController@statistics']);
Route::get('user/profile/{id}',['middleware' => 'auth','uses' => 'UserProfileController@show']);

/*get('/',function()
{
    if(Auth::check()) return'Welcome back,' . Auth::user()->name;
    return 'Hi guest, ' . link_to('login','Login with facebook!');
});*/
Route::get('loginwithfacebook', 'LoginController@login');


Route::get('parse','ParserController@parse');

Route::get('category/{name}', 'ArticlesController@showCategory');
Route::controllers([
	'auth' => 'Auth\AuthController',
	'password' => 'Auth\PasswordController',
]);
