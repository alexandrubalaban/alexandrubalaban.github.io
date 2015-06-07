<?php
/**
 * Created by PhpStorm.
 * User: Sunny
 * Date: 01.06.2015
 * Time: 3:38
 */
namespace App;

interface LoginUserListner
{
    public function userHasLoggedIn($user);
}