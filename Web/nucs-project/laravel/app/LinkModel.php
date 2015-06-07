<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class LinkModel extends Model {

    protected $table = 'links';
    public $timestamps = false;
    public $incrementing =false;

    protected $fillable =[
        'userId',
        'urlAdress',
        'keywords',
        'accepted',
        'published_at'
    ];

}
