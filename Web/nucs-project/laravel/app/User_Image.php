<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class User_Image extends Model {

	//
    protected $table = 'user_images';
    protected $fillable =[
        'path',
        'user_id'
    ];

    public function user(){
        return $this->belongsTo('App\User');
    }
}
