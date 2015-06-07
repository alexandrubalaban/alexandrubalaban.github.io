<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class PreferencesModel extends Model {
    protected $table = 'preferences';
    public $incrementing =false;
    public $timestamps = false;

    protected $fillable = [
        'userId',
        'categoryId'
    ];

	//

}
