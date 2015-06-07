<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class FactsModel extends Model {

	protected $table = 'facts';
    public $timestamps = false;

    protected $fillable = [
        'text'
    ];


}
