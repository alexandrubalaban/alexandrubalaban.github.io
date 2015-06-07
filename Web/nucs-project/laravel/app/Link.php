<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Link extends Model {

    protected $table = 'links';

    protected $fillable =[
        'url',
        'published_at',
    ];
    public function user(){
        return $this->belongsTo('App\User');
    }

}
