<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class DefinedLinks extends Model {
    protected $table = 'definedLinks';
    public $incrementing =false;
    public $timestamps = false;

    protected $fillable = [
        'url',
    ];

}
