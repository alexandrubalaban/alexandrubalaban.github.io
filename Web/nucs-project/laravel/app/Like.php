<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Like extends Model {

    protected $table = 'rateOfArticle';
    protected $fillable=[
        'value',
        'published_at'
    ];
    public function scopeByUsers($query, $userid){
        return $query->where('userid','=',$userid);
    }
    public function user(){
        return $this->belongsTo('App\User');
    }
    public function article(){
        return $this->belongsTo('App\User');
    }


}
