<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class DefinedLinksModel extends Model {
    protected $table = 'definedLinks';
    public $incrementing =false;
    public $timestamps = false;

    protected $fillable = [
        'linkId',
        'URL',
        'keyWords',
        'categoryId'
    ];

	//

}
