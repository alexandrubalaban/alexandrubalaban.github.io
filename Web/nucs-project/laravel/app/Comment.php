<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Comment extends Model {
    protected $table = 'comments';
    public $incrementing =false;
    public $timestamps = false;

	//
    protected $fillable = [
        'body',
        'published_at',
    ];
    public function scopeByUsers($query, $userid){
        return $query->where('userid','=',$userid);
    }
    public function user(){
        return $this->belongsTo('App\User');
    }
    public function article(){
        return $this->belongsTo('App\Article');
    }
}
