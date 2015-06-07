@extends('app')

@section('content')
    <div class="container user-profile-page">
        <div class="row">
            <div class="col-md-4 list-group">
                <ul class="profile-menu">
                    <a  href="{{ url('user/edit/profile', Auth::user()->id) }}" class="list-group-item">Edit profile</a>
                    <a href="{{ url('user/addlinks', Auth::user()->id) }}"class="list-group-item">Add links</a>
                    <a href="{{ url('user/statistics', Auth::user()->id) }}"class="list-group-item active">Statistics</a>
                </ul>


            </div>
            <div class="col-md-8">
                <h1>
                    Statistics:
                </h1>
                <?php
                    $user = Auth::user();
                    echo 'Comments: '.count($user->comments).'<br/>';
                    echo 'Articles: '.count($user->articles).'<br/>';
                    echo 'Links: '.count($user->links).'<br/>';
                ?>
            </div>
        </div>
    </div>
@endsection
