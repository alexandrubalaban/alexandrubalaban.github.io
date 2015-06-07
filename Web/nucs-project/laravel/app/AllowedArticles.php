<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class AllowedArticles extends Model {


    protected $table = 'allowed_articles';
    public $timestamps = false;
    public $incrementing =false;
    protected $fillable = [
        'articleId',
        'userId'
    ];

    public  function functie($query,$idUserLogat)
    {
        return  $query->select('articleId')
            ->from('allowed_articles')
            ->where('userId','=', $idUserLogat);
    }

}
