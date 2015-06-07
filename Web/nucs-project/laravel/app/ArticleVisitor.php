<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class ArticleVisitor extends Model {

    protected $table = 'articleVisitors';
    public $timestamps = false;
    public $incrementing =false;

    protected $fillable = [
        'articleId',
        'userId',
        'addDate'
    ];

    public function scopeByUsers($query, $userid){
        return $query->where('userid','=',$userid);
    }
}
