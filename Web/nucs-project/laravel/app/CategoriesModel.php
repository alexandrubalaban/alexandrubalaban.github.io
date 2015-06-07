<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class CategoriesModel extends Model {

	protected $table = 'categories';
    public $timestamps = false;

    protected $fillable = [
        'nameOfCategory',
        'type'
    ];

}
