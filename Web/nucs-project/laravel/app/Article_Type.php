<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Article_Type extends Model {

    protected $table = 'article_types';
    protected $fillable =[
        'name',
        'article_id'
    ];

    public function articles(){
        return $this->belongsToMany('App\Article');
    }

}
