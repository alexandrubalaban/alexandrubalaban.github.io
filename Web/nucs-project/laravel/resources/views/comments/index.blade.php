@extends('app')

@section('content')

    <h1>Comments</h1>

    @foreach($comments as $comment)
        <h2>Text of comm:{{ $comment->body }}  </h2>
        <h2>Name of user: {{$comment->name}}</h2>
    @endforeach


@stop