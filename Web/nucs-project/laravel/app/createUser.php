<?php
/**
 * Created by PhpStorm.
 * User: Sunny
 * Date: 01.06.2015
 * Time: 2:36
 */

namespace App;
use Illuminate\Contracts\Auth\Authenticatable;
use Illuminate\Support\Facades\App;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Laravel\Socialite\Contracts\Factory as Socialite;
use UserRepository;


class createUser {

    /**
     * @param UserRepository $users
     * @param Socialite $Socialite
     * @param Authenticatable $auth
     */
    private $users;
    private $socialite;
    private $auth;
    public function _construct(UserRepository $users, Socialite $socialite, Authenticatable $auth)
    {
        $this->users = $users;
        $this->socialite = $socialite;
        $this->auth = $auth;
    }

    public function execute($hasCode, LoginUserListner $listner)
    {

        if (! $hasCode) return $this->getCreateUserFirst();
        //$user=\Socialite::with('facebook')->user();
        $user=$this->findByUserNameOrCreate($this->getFacebookUser());
        dd($user);
        $this->auth->login($user, true);
        //dd($user);
        return $listner->userHasLoggedIn($user);
    }

    private function getCreateUserFirst()
    {
        //dd($this);
        return \Socialite::with('facebook')->redirect();
    }

    private function getFacebookUser()
    {
        return \Socialite::with('facebook')->user();
    }

    public function findByUserNameOrCreate($userData)
    {
        return User::firstOrCreate([
            'name' => $userData->name,
            'email' => $userData->email,
            'password' =>Hash::make($userData->id),
        ]);
    }
}