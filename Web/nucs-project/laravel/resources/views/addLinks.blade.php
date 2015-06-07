@extends('app')

@section('content')
    <div class="container user-profile-page">
        <div class="row">
            <div class="col-md-4 list-group">
                <ul class="profile-menu">
                    <a  href="{{ url('user/edit/profile', Auth::user()->id) }}" class="list-group-item">Edit profile</a>
                    <a href="{{ url('user/addlinks', Auth::user()->id) }}"class="list-group-item active">Add links</a>
                    <a href="{{ url('user/statistics', Auth::user()->id) }}"class="list-group-item">Statistics</a>
                </ul>


            </div>
            <div class="col-md-8">
                {!! Form::open(['method' => 'POST', 'url' => 'user/addlinks/'. Auth::user()->id ]) !!}
                {!! Form::text('addlink', null, ['placeholder' => 'your link']) !!}
                {!! Form::text('keyword', null, ['placeholder' => 'keyword']) !!}
                {!! Form::submit('add link') !!}


                {!! Form::close() !!}

                <?php
                    $links = Auth::user()->links->toArray();
                    //var_dump($links);

                    echo " <ul class=\"list-group\">";
                    foreach ($links as $result)
                    {
                        echo "<li class=\"list-group-item list-group-item-\">".$result['urladress']."</li>";
                    }
                    echo "</ul>";

                ?>

            </div>
        </div>
    </div>
@endsection
