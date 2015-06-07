<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Article_Image extends Model {

    protected $table = 'article_images';
    protected $fillable =[
        'path',
        'article_id',
    ];
    public function article(){
        return $this->belongsTo('App\Article');
    }

}
