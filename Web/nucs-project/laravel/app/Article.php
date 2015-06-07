<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Article extends Model {
    protected $table = 'articles';

    protected $fillable = [
        'title',
        'user_id',
        'excerpt',
        'body',
        'sourceName',
        'linkURL',
        'views',
        'published_at',

    ];

    public function scopeDataDescending($query){
        return $query->orderBy('published_at','DESC')->withTimestamps();
    }

    public function tags(){
        return $this->belongsToMany('App\Tag')->withTimestamps();
    }
    public function categories(){
        return $this->belongsToMany('App\Categories')->withTimestamps();
    }
    public function user(){
        return $this->belongsTo('App\User')->withTimestamps();
    }
    public function likes(){
        return $this->hasMany('App\Like')->withTimestamps();
    }
    public function image(){
        return $this->hasOne('App\Article_Image')->withTimestamps();
    }
    public function type(){
        return $this->belongsTo('App\Article_Type');
    }
    public function comments(){
        return $this->hasMany('App\Comment');
    }
    public function hasType($type_name){
        var_dump($this->type);
        if ($this->type['name'] == $type_name){
            return true;
        }
        return false;
    }






}
