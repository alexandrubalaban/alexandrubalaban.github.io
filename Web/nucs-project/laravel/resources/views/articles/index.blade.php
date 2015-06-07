@extends('app')

@section('content')

    <div id="wrapper">
        <div id="main" class="container-fluid">
            <div class="row">
                <div class="category-title-wrapper">
                    <div class="category-title-container container">
                        <h1 class="category-title">{{ strtoupper($name) }}</h1>
                        <div class="category-description">

                        </div>
                    </div>
                </div>
                <section class="container" id="latest-news">
                    <aside class="col-md-3 sidebar">

                    </aside>
                    <div class="col-md-9 content-post">
                        @foreach($articles as $article)
                            <div class="news-post post-size category-languages col-md-4">
                                <div class="news-post-wrapper">
                                    <div class="post-excerpt-content">
                                        <div class="post-img-wrap">
                                            <div class="category">
                                                <a href="{{url('/category',$article->categories->get(0)->name)}}">{{ $article->categories->get(0)->name }}</a>
                                            </div>
                                            <div class="post-img">
                                                <img src="{{asset('img/stire.jpg')}}" class="img-responsive" alt="">
                                            </div>
                                        </div>
                                        <div class="post-excerpt">
                                            <h1 class="post-title"><a href="{{url('/articles',$article->id)}}">{!! $article->title !!}</a></h1>
                                            <div class="excerpt">
                                                <p>{!! $article->excerpt!!}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="post-overlay">
                                        <div class="category">
                                            <a href="{{url('/category',$article->categories->get(0)->name)}}">{{ $article->categories->get(0)->name }}</a>
                                        </div>
                                        <h1 class="post-title">
                                            <a href="{{url('/articles',$article->id)}}">{!! $article->title !!}</a>
                                        </h1>
                                        <div class="excerpt">
                                            <p>{!! $article->excerpt!!}</p>
                                        </div>
                                        <a href="{{url('/articles',$article->id)}}" class="btn btn-default read-more-btn">Read More</a>
                                    </div>
                                </div>
                            </div>
                        @endforeach
                    </div>
                </section>
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
                                            <a href="{{url('/category',$news->categories->get(0)->name)}}">{{ $news->categories->get(0)->name }}</a>
                                        </div>
                                        <div class="post-img">
                                            <img src="{{asset('img/stire.jpg')}}" class="img-responsive" alt="">
                                        </div>
                                    </div>
                                    <div class="post-excerpt">
                                        <h1 class="post-title"><a href="{{url('/articles',$news->id)}}">{!! $news['title'] !!}</a></h1>
                                        <div class="excerpt">
                                            <p>{!! $news->excerpt!!}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-overlay">
                                    <div class="category">
                                        <a href="{{url('/category',$news->categories->get(0)->name)}}">{{ $news->categories->get(0)->name }}</a>
                                    </div>
                                    <h1 class="post-title">
                                        <a href="{{url('/articles',$news->id)}}">{!! $news->title !!}</a>
                                    </h1>
                                    <div class="excerpt">
                                        <p>{!! $news->excerpt!!}</p>
                                    </div>
                                    <a href="{{url('/articles',$news->id)}}" class="btn btn-default read-more-btn">Read More</a>
                                </div>
                            </div>
                        </div>
                    @endforeach
                </div>
            </div>
        </div>
    </div>
    @stop