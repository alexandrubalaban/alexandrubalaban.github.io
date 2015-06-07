<?php
/**
 * Created by PhpStorm.
 * User: Ana
 * Date: 6/1/2015
 * Time: 3:57 PM
 */

namespace App\Http\Controllers;


use App\Article;
use App\Article_Type;
use App\Categories;
use App\Tag;
use Carbon\Carbon;
use Illuminate\Support\Facades\DB;
use League\Flysystem\Exception;

class ParserController extends Controller {

    public function parse_reddit($user_id,$category_token,$tag_token)
    {
        echo "token = " . $category_token;
        $xml = simplexml_load_file('http://www.reddit.com/search.xml?q='. $category_token .'+'.$tag_token.'&sort=new'); // ar putea fi folosit un URL real
        ParserController::parse_xml($xml,$user_id,$category_token,$tag_token,'reddit');
    }

    public function parse_infoq($user_id,$category_token,$tag_token)
    {
       // return "<img src=\"http://www.infoq.com/styles/i/logo_bigger.jpg\"/><p>After more than a year on the drawing board, Reactive Streams has released version 1.0 of their API for several different platforms, Java among them. This library provides a common framework to standardise reactive patterns.</p> <i>By Abraham MarÃ­n PÃ©rez</i>";
        echo "token = " . $category_token;
        $xml = simplexml_load_file("http://www.infoq.com/feed?token=". $category_token.'+'.$tag_token);
        ParserController::parse_xml($xml,$user_id,$category_token,$tag_token,'infoQ');

    }

    public function parse()
    {
        //echo "reddit";
        //ParserController::parse_reddit(1,'java','android');
        //echo "infoq";
        //ParserController::parse_infoq(1,'java','android');

        $categories = Categories::all();
        $tags =  Tag::all();

        foreach ($categories as $categ)
        {
            foreach ($tags as $tag )
            {
                ParserController::parse_infoq(1,$categ->name, $tag->name);
                ParserController::parse_reddit(1,$categ->name, $tag->name);
            }
        }



    }

    public function parse_xml($xml,$user_id,$category_token,$tag_token,$source)
    {
        foreach ($xml->xpath('//item') as $item) {
            //var_dump($item);

            $text = (string)$item->description;
            $title = (string)$item->title;
            $url = (string)$item->link;
            $published = (string) $item-> pubDate;

            $temp = strstr($text,'<p>');
            $excerpt = substr($temp, 3, 34);
            $excerpt = $excerpt.'.....';

            $xml_text = simplexml_load_string('<p> ' . $text . ' </p>');
            $is_img = 0;
            foreach ($xml_text->xpath('//img/@src') as $img) {
                $img_url = (string )$img;
                $img_urls = $img_url;
                $is_img++;
            }

            $is_video = 0;
            foreach ($xml_text->xpath('//video/@src') as $video) {
                $video_url = (string )$video;
                //var_dump($video_url);
                $video_urls[$is_video] = $video_url;
                $is_video++;
            }

            $is_code = 0;
            foreach ($xml_text->xpath('//code') as $code) {
                $is_code++;
            }



            try {

                $id = DB::table('articles')->insertGetId([
                    'title' => $title,
                    'user_id' => $user_id,
                    'excerpt' => $excerpt,
                    'body' => $text,
                    'sourceName' => $source,
                    'linkURL' => $url,
                    'views' => 0,
                    'published_at' => Carbon::now(),
                    'created_at' => Carbon::now(),
                    'updated_at' => Carbon::now()
                ]);

                $current_article = Article::find($id);

                $current_categirie = Categories::where('name', '=', $category_token)->get();

                $current_article->categories()->attach($current_categirie[0]);

                $current_tag = \App\Tag::where('name', '=', $tag_token)->get();
                $current_article->tags()->attach($current_tag[0]);

                //var_dump($is_img);
                if ($is_img != 0) {
                    $type = \App\Article_Type::create([
                        'article_id' => $id,
                        'name' => 'img'
                    ]);
                    $article_iamge = \App\Article_Image::create([
                        'article_id'=>$id,
                        'path' =>  $img_urls
                    ]);
                    //var_dump($is_img);
                    //var_dump($article_iamge);
                    $current_article->image()->attach($article_iamge);
                }
                if ($is_video != 0) {
                    $type = \App\Article_Type::create([
                        'article_id' => $id,
                        'name' => 'video'
                    ]);

                }
                if ($is_code != 0) {
                    $type = \App\Article_Type::create([
                        'article_id' => $id,
                        'name' => 'code'
                    ]);
                }
                if ($is_code == 0 && $is_img == 0 && $is_video == 0) {
                    // dd($id);

                    $type = \App\Article_Type::create([
                        'article_id' => $id,
                        'name' => 'text'
                    ]);
                }
            }catch (\Exception $e){

            }
        }

    }

}