@extends('app')

@section('content')

    <div id="wrapper">
        <div id="main" class="container-fluid">
            @if(is_null($article))
                <div class="row">
                    <div class="container article-not-found">
                        <h1>Unfortunately we don't have this article anymore! Thank you for understanding.</h1>
                    </div>
                </div>

            @else
                <div class="row">
                    <div class="article-title-wrapper col-md-12">
                        <div class="article-title-container container">
                            <div class="col-md-6 col-md-offset-3">
                                <div class="article-category"><a href="{{url('/category',$article->categories->get(0)->name)}}">{{ $article->categories->get(0)->name }}</a></div>
                                <h1 class="article-title">{!! $article->title !!}</h1>

                                <div class="article-info">
                                    <div class="col-md-4 article-post-date"><span class="fa fa-clock-o"></span>{!!
                                        $article->published_at !!}
                                    </div>
                                    <div class="col-md-4 article-meta">
                                        <div class="col-md-6 article-likes">
                                            <span class="fa fa-heart"></span>{!! $article->views !!}
                                        </div>
                                        <div class="col-md-6 article-comments">
                                            <span class="fa fa-comment-o"></span>{{count($article->comments)}}
                                        </div>
                                    </div>
                                    <div class="col-md-4 article-shares">
                                        {!!  $article->views !!} shares<span class="fa fa-retweet"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="article-body col-md-12">
                        <div class="row">
                            <div class="article-container container">
                                <article class="article col-md-12">
                                    <div class="article-body-text">
                                        {!! $article->body !!}
                                    </div>
                                </article>
                            </div>
                        </div>
                    </div>

                    <div class="article-comments-wrapper container-fluid col-md-12">
                        <div class="row">
                            <div class="article-comments container">
                                <div class="comments col-md-8 col-md-offset-2">
                                    <div class="comment-heading col-md-12">
                                        <div class="comment-title">
                                            <h1 class="title">Comments<span class="badge">{{count($article->comments)}}</span></h1>
                                            @if(Auth::user())
                                                <button class="add-comment-button"><span class="glyphicon glyphicon-pencil"></span></button>
                                            @endif
                                        </div>
                                    </div>
                                    @foreach( $article->comments as $comment )
                                        <div class="article-comment col-md-12">
                                            <div class="col-md-2 user-image">
                                                <div class="user-img">
                                                    <img src="{{asset('img/user-img-comm.png')}}" alt="">
                                                </div>
                                            </div>
                                            <div class="col-md-10 comment-text">
                                                <div class="comment-top">
                                                    <div class="user-name">{{ \App\User::find($comment['user_id'])->name }}</div>
                                                    <div class="post-date"><span class="fa fa-clock-o"></span>{{ $comment['published_at'] }}
                                                    </div>
                                                </div>
                                                <div class="comment-body">
                                                    <p>{{ $comment['body'] }}</p>
                                                </div>
                                            </div>
                                        </div>
                                    @endforeach

                                    @if(Auth::user())
                                        <div class="article-comment-form col-md-12">
                                            <div class="article-comment-form-heading">
                                                <h1 class="article-comment-form-title">Add comment</h1>
                                            </div>
                                            <div class="form-wrapper col-md-12">
                                                <div class="col-md-2 user-image">
                                                    <div class="user-img">
                                                        <img src="{{ Auth::user()->images->path }}" alt="">
                                                    </div>
                                                </div>
                                                <div class="comment-form col-md-10">
                                                    {!! Form::open(['class'=>'form-horizontal']) !!}
                                                        {!! Form::hidden('article_id', $article['id']) !!}
                                                        <div class="form-group">
                                                            {!! Form::textarea('comment_body', 'Add Comment', ['class'=>'form-control', 'rows'=>'5'])!!}

                                                        </div>
                                                        <div class="form-group">
                                                            {!! Form::submit('Add Comment', ['class'=>'btn btn-default pull-right']) !!}
                                                        </div>
                                                    {!! Form::close() !!}
                                                </div>
                                            </div>
                                        </div>
                                    @endif
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            @endif
        </div>
        <div id="interesting-fact" class="container-fluid">
            <div class="row">
                <div class="container">
                    <div id="interesting-fact-carousel">
                        <div class="fact">
                            <div class="title">
                                <h1 class="fact-title">
                                    Spring in Crete Means a Feast of Wild Greens
                                </h1>
                            </div>
                            <div class="fact-text">
                                <p>A Nat Geo photographer shares the Mediterranean cuisine of Greece’s largest
                                    island.</p>
                            </div>
                        </div>
                        <div class="fact">
                            <div class="title">
                                <h1 class="fact-title">
                                    Spring in Crete Means a Feast of Wild Greens
                                </h1>
                            </div>
                            <div class="fact-text">
                                <p>A Nat Geo photographer shares the Mediterranean cuisine of Greece’s largest
                                    island.</p>
                            </div>
                        </div>
                        <div class="fact">
                            <div class="title">
                                <h1 class="fact-title">
                                    Spring in Crete Means a Feast of Wild Greens
                                </h1>
                            </div>
                            <div class="fact-text">
                                <p>A Nat Geo photographer shares the Mediterranean cuisine of Greece’s largest
                                    island.</p>
                            </div>
                        </div>
                    </div>
                    <div class="sect-overlay"></div>
                </div>
            </div>
        </div>
        <div id="most-popular" class="container-fluid">
            <div class="row">
                <div class="container most-popular-posts">
                    <h1 class="sect-title">Most popular</h1>
                    @foreach($bestNews as $news)
                        <div class="news-post post-size category-languages col-md-3">
                            <div class="news-post-wrapper">
                                <div class="post-excerpt-content">
                                    <div class="post-img-wrap">
                                        <div class="category">
                                            <a href="#">Languages</a>
                                        </div>
                                        <div class="post-img">
                                            <img src="{{asset('img/stire.jpg')}}" class="img-responsive" alt="">
                                        </div>
                                    </div>
                                    <div class="post-excerpt">
                                        <h1 class="post-title"><a href="url('/articles',$news['id'])}}">{!! $news['title'] !!}</a></h1>
                                        <div class="excerpt">
                                            <p>{!! $news['body']!!}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-overlay">
                                    <div class="category">
                                        <a href="#">Languages</a>
                                    </div>
                                    <h1 class="post-title">
                                        <a href="url('/articles',$news['id'])}}">{!! $news['title'] !!}</a>
                                    </h1>
                                    <div class="excerpt">
                                        <p>{!! $news['body']!!}</p>
                                    </div>
                                    <a href="{{url('/articles',$news['id'])}}" class="btn btn-default read-more-btn">Read More</a>
                                </div>
                            </div>
                        </div>
                    @endforeach
                </div>
            </div>
        </div>
    </div>
@stop