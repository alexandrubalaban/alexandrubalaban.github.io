<?php namespace App\Http\Controllers;

use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\Http\Requests\UpdateUserRequest;
use App\User;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class UserProfileController extends Controller {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		//
	}

	/**
	 * Show the form for creating a new resource.
	 *
	 * @return Response
	 */
	public function create()
	{
		//
	}

	/**
	 * Store a newly created resource in storage.
	 *
	 * @return Response
	 */
	public function store()
	{
		//
	}

	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function show($id)
	{
        if ($id == Auth::user()->id){
            return view('userdashboard');
        }
        else{
            return redirect('home');
        }
	}

    public function editProfile($id)
    {
        if ($id == Auth::user()->id){
            return view('editProfile');
        }
        else{
            return redirect('home');
        }

    }

    public function editUser(UpdateUserRequest $request)
    {
        $user = User::find(Auth::user()->id);
        if ($request['fname'] != '') {
            $user->name = $request['fname'];
        }
        if ($request['newpassword'] != '') {
            $user->password = Hash::make($request['newpassword']);
        }

        $user->save();
        return redirect('home');
    }

    public function addLinks($id)
    {
        if ($id == Auth::user()->id){
            return view('addLinks');
        }
        else{
            return redirect('home');
        }
    }

    public function addYourLink(Request $request){

        DB::table('links')->insert(array('user_id' => Auth::user()->id, 'urlAdress' => $request['addlink'],
            'keyWords'=>$request['keyword'], 'accepted'=>'no','published_at' => Carbon::now())) ;
        chdir('C:\Users\Ionut\Desktop\webbb\out\artifacts\webHoseAPI_jar');
        $execString = 'java -jar webHoseAPI.jar '.Auth::user()->id.' 1 1 '.$request['addlink'];
        exec($execString);
        return redirect('user/addlinks/'.Auth::user()->id);

    }

    public function statistics($id)
    {
        if ($id == Auth::user()->id){
            return view('statistics');
        }
        else{
            return redirect('home');
        }
    }
	/**
	 * Show the form for editing the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function edit($id)
	{
		//
	}

	/**
	 * Update the specified resource in storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function update($id)
	{
		//
	}

	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
		//
	}

}
