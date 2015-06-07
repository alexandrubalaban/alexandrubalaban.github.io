@extends('app')

@section('content')
    <div id="wrapper">
        <div id="slider" class="container-fluid">
            <div class="row">
                <div id="news-slider" class="slider-pro sp-horizontal">
                    <div class="sp-slides-container">
                        <div class="sp-mask sp-grab">
                            <div class="sp-slides">
                                @foreach($sliderArticles as $sliderArticle)
                                    <div class="sp-slide">
                                        <div class="sp-image-container">
                                            <img class="sp-image" src="{{asset('img/slider1.jpg')}}">
                                        </div>
                                        <div class="sp-layer" data-position="centerCenter">
                                            <h1 class="slide-title"><a href="{{url('/articles',$sliderArticle->id)}}">{!! $sliderArticle->title !!}</a></h1>

                                            <p class="slide-title-description">{!! $sliderArticle->excerpt !!}</p>

                                        </div>
                                    </div>
                                @endforeach
                            </div>
                        </div>
                    </div>
                    <div class="sp-thumbnails-container sp-bottom-thumbnails sp-has-pointer sp-swiping">
                        <div class="sp-thumbnails sp-grab">
                            @foreach($sliderArticles as $sliderArticle)
                                <div class="sp-thumbnail">
                                    <div class="sp-thumbnail-title">{!! $sliderArticle->title !!}</div>
                                    <div class="sp-thumbnail-description">{!! $sliderArticle->excerpt!!}</div>
                                </div>
                            @endforeach
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="main" class="container-fluid">
            <div class="row">
                <section class="container" id="latest-news">
                    @foreach($latestTop as $latestTopArticle)
                        <div class="news-post post-size category-languages col-md-3">
                            <div class="news-post-wrapper">
                                <div class="post-excerpt-content">
                                    <div class="post-img-wrap">
                                        <div class="category">
                                            <a href="{{url('/category',$latestTopArticle->categories->get(0)->name)}}">{{ $latestTopArticle->categories->get(0)->name }}</a>
                                        </div>
                                        <div class="post-img">
                                            <img src="{{asset('img/stire.jpg')}}" class="img-responsive" alt="">
                                        </div>
                                    </div>
                                    <div class="post-excerpt">
                                        <h1 class="post-title"><a href="{{url('/articles',$latestTopArticle->id)}}">{!! $latestTopArticle->title !!}</a></h1>
                                        <div class="excerpt">
                                            <p>{!! $latestTopArticle->excerpt!!}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-overlay">
                                    <div class="category">
                                        <a href="{{url('/category',$latestTopArticle->categories->get(0)->name)}}">{{ $latestTopArticle->categories->get(0)->name }}</a>
                                    </div>
                                    <h1 class="post-title">
                                        <a href="{{url('/articles',$latestTopArticle->id)}}">{!! $latestTopArticle->title !!}</a>
                                    </h1>
                                    <div class="excerpt">
                                        <p>{!! $latestTopArticle->excerpt!!}</p>
                                    </div>
                                    <a href="{{url('/articles',$latestTopArticle->id)}}" class="btn btn-default read-more-btn">Read More</a>
                                </div>
                            </div>
                        </div>
                    @endforeach
                </section>
            </div>
            <div class="row">
                <section id="inspiring-quote" class="container-fluid">
                    <div class="container">
                        <div id="inspiring-quote-carousel">
                            <div class="quote">
                                <div class="title">
                                    <h1 class="quote-title">
                                        Spring in Crete Means a Feast of Wild Greens
                                    </h1>
                                </div>
                                <div class="quote-text">
                                    <p>A Nat Geo photographer shares the Mediterranean cuisine of Greece’s largest island.</p>
                                </div>
                            </div>
                            <div class="quote">
                                <div class="title">
                                    <h1 class="quote-title">
                                        Spring in Crete Means a Feast of Wild Greens
                                    </h1>
                                </div>
                                <div class="quote-text">
                                    <p>A Nat Geo photographer shares the Mediterranean cuisine of Greece’s largest island.</p>
                                </div>
                            </div>
                            <div class="quote">
                                <div class="title">
                                    <h1 class="quote-title">
                                        Spring in Crete Means a Feast of Wild Greens
                                    </h1>
                                </div>
                                <div class="quote-text">
                                    <p>A Nat Geo photographer shares the Mediterranean cuisine of Greece’s largest island.</p>
                                </div>
                            </div>
                        </div>
                        <div class="sect-overlay"></div>
                    </div>
                </section>
            </div>
            <div class="row">
                <section class="container" id="latest-news-2">
                    @foreach($latestSecond as $latestSecondArticle)
                        <div class="news-post post-size category-languages col-md-3">
                            <div class="news-post-wrapper">
                                <div class="post-excerpt-content">
                                    <div class="post-img-wrap">
                                        <div class="category">
                                            <a href="{{url('/category',$latestSecondArticle->categories->get(0)->name)}}">{{ $latestSecondArticle->categories->get(0)->name }}</a>
                                        </div>
                                        <div class="post-img">
                                            <img src="{{asset('img/stire.jpg')}}" class="img-responsive" alt="">
                                        </div>
                                    </div>
                                    <div class="post-excerpt">
                                        <h1 class="post-title"><a href="{{url('/articles',$latestSecondArticle->id)}}">{!! $latestSecondArticle->title !!}</a></h1>
                                        <div class="excerpt">
                                            <p>{!! $latestSecondArticle->excerpt!!}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-overlay">
                                    <div class="category">
                                        <a href="{{url('/category',$latestSecondArticle->categories->get(0)->name)}}">{{ $latestSecondArticle->categories->get(0)->name }}</a>
                                    </div>
                                    <h1 class="post-title">
                                        <a href="{{url('/articles',$latestSecondArticle->id)}}">{!! $latestSecondArticle->title !!}</a>
                                    </h1>
                                    <div class="excerpt">
                                        <p>{!! $latestSecondArticle->excerpt!!}</p>
                                    </div>
                                    <a href="{{url('/articles',$latestSecondArticle->id)}}" class="btn btn-default read-more-btn">Read More</a>
                                </div>
                            </div>
                        </div>
                    @endforeach
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