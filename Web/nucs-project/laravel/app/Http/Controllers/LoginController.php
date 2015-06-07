<?php namespace App\Http\Controllers;

use App\createUser;
use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\LoginUserListner;
use Illuminate\Http\Request;
use Laravel\Socialite\Facades\Socialite;

class LoginController extends Controller implements LoginUserListner
{

    public function login(createUser $createUser, Request $request)
    {

        return $createUser->execute($request->has('code'), $this);
        //create user
       //return \Socialite::with('facebook')->redirect();
    }

    public function userHasLoggedIn($user)
    {
        return redirect('/');
    }

}
