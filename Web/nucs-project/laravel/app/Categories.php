<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Categories extends Model {

	protected $table = 'categories';
    public $timestamps = false;

    protected $fillable = [
        'name',
        'parent'
    ];
    public function articles(){
        return $this->belongsToMany('App\Article');
    }

}
