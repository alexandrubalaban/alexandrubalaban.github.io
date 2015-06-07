<?php namespace App;

use Illuminate\Auth\Authenticatable;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Auth\Passwords\CanResetPassword;
use Illuminate\Contracts\Auth\Authenticatable as AuthenticatableContract;
use Illuminate\Contracts\Auth\CanResetPassword as CanResetPasswordContract;

class User extends Model implements AuthenticatableContract, CanResetPasswordContract {

	use Authenticatable, CanResetPassword;

	/**
	 * The database table used by the model.
	 *
	 * @var string
	 */
	protected $table = 'users';

	/**
	 * The attributes that are mass assignable.
	 *
	 * @var array
	 */
	protected $fillable = [
        'name',
        'email',
        'password',
        'links',
        'registryDate',
        'lastLogin',
        'type',
        'facebookUserName',
        'twitterUserName',
        'deliciousUserName',
        'photoURL'
    ];

	/**
	 * The attributes excluded from the model's JSON form.
	 *
	 * @var array
	 */
	protected $hidden = ['password', 'remember_token'];

    public function articles(){
        return $this->hasMany('App\Article');
    }
    public function comments(){
        return $this->hasMany('App\Comment');
    }
    public function preferences(){
        return $this->hasMany('App\Preferences');
    }
    public function likes(){
        return $this->hasMany('App\Likes');
    }
    public function links(){
        return $this->hasMany('App\Link');
    }
    public function images(){
        return $this->hasOne('App\User_Image');
    }

    public function roles(){
        return $this->belongsToMany('App\Role')->withTimestamps();
    }
    public function hasRole($role_name){
       foreach ($this->roles as $role){
           if ($role->name == $role_name){
               return true;
           }
       }
        return false;
    }
    public function assignRole($role){
        return $this->roles()->attach($role);
    }
    public function removeRole($role){
        return $this->roles()->detach($role);
    }

}
