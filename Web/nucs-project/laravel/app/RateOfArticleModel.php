<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class RateOfArticleModel extends Model {

    protected $table = 'rateOfArticle';
    public $timestamps = false;
    public $incrementing= false;
    protected $fillable=[
        'articleId',
        'userId',
        'value',
        'published_at'
    ];
    public function scopeByUsers($query, $userid){
        return $query->where('userid','=',$userid);
    }
}
