@extends('app')

@section('content')
    <div class="container">
        <div class="row">
            <div class="col-md-4 list-group">
                <ul class="profile-menu">
                    <a  href="{{ url('user/edit/profile', Auth::user()->id) }}" class="list-group-item">Edit profile</a>
                    <a href="{{ url('user/addlinks', Auth::user()->id) }}"class="list-group-item">Add links</a>
                    <a href="{{ url('user/statistics', Auth::user()->id) }}"class="list-group-item">Statistics</a>
                </ul>


            </div>
            <div class="col-md-8">
                <h1>
                    You are logged in! <br>{{Auth::user()->name}}
                </h1>
            </div>
        </div>
    </div>
@endsection
