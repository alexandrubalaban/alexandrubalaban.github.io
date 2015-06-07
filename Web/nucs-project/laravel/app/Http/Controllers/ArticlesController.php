<?php namespace App\Http\Controllers;

use App\Article;
use App\Categories;
use App\Comment;
use App\Http\Requests;
use App\Http\Controllers\Controller;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class ArticlesController extends Controller {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */





	public function index()
	{
        //$articles = Article::all();
        //return $articles;
        // return view('articles.index')->with('articles',$articles);


       // $articles = Article::all()->take(9)->toArray();

        $articles = Article::all()->take(9);

        $bestNews = $this->bestNews();
        //var_dump($bestNews);
        var_dump($articles);




       return view('articles.index')->with('articles',$articles)->with('bestNews', $bestNews);
    }


    /**
     * @return \Illuminate\View\View
     */
    public function popularNews(){
        $aricles = Article::latest('views')->get();

       // return $aricles;
        return view('articles.index')->with('articles',$aricles);
    }

    public function bestNews(){
        $aricles = Article::latest('views')->take(4)->get();

        // return $aricles;
        return $aricles;
    }


    public function lastComm(){

        $comments = Comment::latest('publishedDate')->get();


        $variable = DB::table('users')
                        ->join('comments','users.id','=','comments.userId')
                        ->select('users.name','comments.body')
                        ->get();




        return view('comments.index')->with('comments',$variable);


    }

    public function testLoadMore(){
        return ArticlesController::getArticlesForLoadMoreButton(1,10);
    }


    public function getArticlesForLoadMoreButton($idUserLogat,$nrArticles){
        $variable =   DB::table('articles')
            ->whereIn('id', function($query) use ($idUserLogat,$nrArticles)
            {
                $query->select('articleId')
                    ->from('allowed_articles')
                    ->whereIn('userId',array( $idUserLogat,-1));


            })->orderBy('id','DESC')->take($nrArticles)->get();
        $i=0;
        foreach($variable as $var)
        {    $articles[$i] = get_object_vars($var);
            $i++;
        }
        return view('articles.index')->with('articles',$articles);


    }


    public function testGetAnotherArticles(){
        return ArticlesController::getArticlesFromIdToID(1,30);
    }


    public function getArticlesFromIdToID($idUserLogat,$from){
        $variable =   DB::table('articles')
            ->whereIn('id', function($query) use ($idUserLogat,$from)
            {
                $query->select('articleId')
                    ->from('allowed_articles')
                    ->whereIn('userId',array( $idUserLogat,-1))
                ->where('articleId','<',$from);

            })->orderBy('id','DESC')->take(10)->get();
        //var_dump( $variable );
        $i=0;
        foreach($variable as $var)
        {    $articles[$i] = get_object_vars($var);
            $i++;
        }
        return view('articles.index')->with('articles',$articles);
    }

    public function testAddComm(){
        try{
            if(ArticlesController::addComm('ana e tare',2,2))
                return  ArticlesController::addComm('ana e tare',4,2);
            else return "nu e bine boss";
        }
        catch(\Exception $e){
            echo "Nu e bine boss2";
        }

    }



    private function addComm($text,$articleId,$userId){


        $comm = \App\Comment::create([
            'body'=>$text,
            'articleId'=>$articleId,
            'PUBLISHEDDATE'=>\Carbon\Carbon::now(),
            'userId'=>$userId,
        ]);


        $comm ->save();

        return $comm;

    }

	/**
	 * Show the form for creating a new resource.
	 *
	 * @return Response
	 */
	public function create()
	{
		//
	}

	/**
	 * Store a newly created resource in storage.
	 *
	 * @return Response
	 */
	public function store()
	{
		//
	}

	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function show($id)
	{
		$article = Article::find($id);
        $bestNews = $this->bestNews();
        return view('articles.article')->with('article',$article)->with('bestNews',$bestNews);
	}

	/**
	 * Show the form for editing the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function edit($id)
	{
		//
	}

	/**
	 * Update the specified resource in storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function update($id)
	{
		//
	}
    public function showCategory($name){
        if($name == 'java'){
            $category = Categories::where('name','=',$name)->get();
        }else{
            $category = Categories::where('name','=',strtoupper($name))->get();
        }

        //var_dump($category[0]->articles);
        $bestNews = $this->bestNews();
        $articles = $category[0]->articles;
        //var_dump($bestNews[0]->categories->toArray());
        return view('articles.index')->with('articles',$articles)->with('bestNews', $bestNews)->with('name',$name);
    }
	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
		//
	}
    public function search(Request $request)
    {
        //var_dump($request['q']);

        $query = $request['q'];
        if ($query){
            $articles = DB::table('articles')->where('title', 'LIKE', "%$query%")
                ->orWhere('body', 'LIKE', "%$query%")
                ->get();


        }
        else{
            $articles = Article::all();
        }
        $bestNews = $this->bestNews();
        return view('articles.searchresult')->with('articles',$articles)->with('bestNews', $bestNews);

    }

    public function testFilter(){
        ArticlesController::filter('java','text');

    }

    public function filter($category,$type){

        $id = DB::table('categories')->where('name','=',$category);

//        var_dump($id);

        $variable =   DB::table('articles')
            ->whereIn('id', function($query) use ($type)
            {
                $query->select('article_id')
                    ->from('article_types')
                    ->where('name','=',$type);

            }) ->whereIn('id', function($query) use ($id)
            {
                $query->select('article_id')
                    ->from('article_categories')
                    ->where('categories_id','=',$id);

            });
            var_dump($variable);
    }

}
