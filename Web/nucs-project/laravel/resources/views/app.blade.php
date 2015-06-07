<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>NuCS</title>

	<link href="{{ asset('css/app.css') }}" rel="stylesheet">
    <link rel="stylesheet" href="{{ asset('css/bootstrap.css') }}">
    <link rel="stylesheet" href="{{ asset('css/bootstrap-theme.min.css') }}">
    <link rel="stylesheet" href="{{ asset('css/slider-pro.min.css') }}"/>
    <link rel="stylesheet" href="{{ asset('/css/font-awesome.min.css') }}">
    <link rel="stylesheet" href="{{ asset('css/simple-line-icons.css') }}">
    <link rel="stylesheet" href="{{ asset('css/owl.carousel.css') }}">
    <link rel="stylesheet" href="{{ asset('css/owl.theme.css') }}">
    <link rel="stylesheet" href="{{ asset('css/owl.transitions.css') }}">
    <link rel="stylesheet" href="{{ asset('css/style.css') }}" type="text/css"/>


	<!-- Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
    <div id="page">
        <div id="header" class="container-fluid dark">
            <div class="row">
                <div id="top-bar">
                    <div class="container">
                        <div class="row col-md-12">
                            <div id="social" class="col-md-4">
                                <ul class="list-inline">
                                    <li>
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-rss"></i></a>
                                    </li>
                                </ul>

                            </div>
                            <div class="logo col-md-4">
                                <a href="{{ url('/home') }}"><h1>NuCS <span>Magazine</span></h1></a>
                            </div>
                            <?php
                                $categories = \App\Categories::where('parent', '=', null)->get();
                                //$categories = $categories->toArray();
                                //var_dump($categories);
                            ?>
                            <div id="login" class="col-md-4">
                                @if (Auth::guest())
                                    <a href="{{ url('/auth/login') }}" type="button" class="pull-right">Login</a>
                                @else
                                    <li class="dropdown pull-right">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">{{ Auth::user()->name }} <span class="caret"></span></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="{{ url('/auth/logout') }}">Logout</a></li>
                                            <li><a href="{{ url('/user/profile',Auth::user()->id)}}">View Profile</a></li>
                                        </ul>
                                    </li>
                                @endif

                            </div>
                        </div>
                    </div>
                </div>
                <div id="bot-wrapper">
                    <div id="bot-bar" class="container">
                        <div class="row col-md-12">
                            <div id="sec-menu" class="col-md-1">
                                <a href="#"><i class="glyphicon glyphicon-menu-hamburger"></i></a>
                            </div>
                            <div id="main-menu" class="col-md-10">
                                <div class="nav">
                                    <ul class="list-inline">
                                        @foreach($categories as $categorie)
                                            <li><a href="{{ url('/category',strtolower($categorie->name)) }}">{!! $categorie->name !!}</a>
                                        @endforeach

                                    </ul>
                                </div>

                            </div>
                            <div id="search" class="col-md-1 text-right">
                                {!! Form::open(['url' => 'search','method'=>'GET']) !!}
                                {!!Form::input('search','q', null, ['placeholder' => 'Search...']) !!}
                                {!! Form::close() !!}
                                <a href="#" class="search-icon"><i class="fa fa-search"></i></a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>


	    @yield('content')
        <div id="footer" class="container-fluid">
            <div class="row">
                <div class="container" id="footer-wrap">
                    <div class="col-md-3 footer-block" id="copyright">
                        <div class="logo col-md-4">
                            <a href="#"><h1>NuCS <span>Magazine</span></h1></a>
                        </div>
                        <div class="copyright col-md-12">
                            <p><i class="fa fa-copyright"></i> 2015 All rights reserved</p>
                        </div>
                    </div>
                    <div class="col-md-3 footer-block" id="usefull-links">
                        <ul class="col-md-7">
                            <li><a href="#">LANGUAGES</a>
                            <li><a href="#">TECHNOLOGY</a></li>
                            <li><a href="#">INGINEERING</a></li>
                            <li><a href="#">INDUSTRY</a></li>
                            <li><a href="#">OS</a></li>
                        </ul>
                        <ul class="col-md-5 about">
                            <li><a href="#">About us</a>
                            <li><a href="#">Contact</a></li>
                            <li><a href="#">Terms & Cond.</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3 footer-block" id="social-links">
                        <ul>
                            <li><a href="#"><i class="fa fa-facebook-f"></i>Facebook</a>
                            <li><a href="#"><i class="fa fa-twitter"></i>Twitter</a></li>
                            <li><a href="#"><i class="fa fa-instagram"></i>Instagram</a></li>
                            <li><a href="#"><i class="fa fa-rss"></i>RSS</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3 footer-block" id="subscribe">

                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- Scripts -->
    <script type="text/javascript" src="{{ asset('/js/jquery-1.11.2.min.js') }}"></script>
    <script type="text/javascript" src="{{ asset('/js/jquery.sliderPro.js') }}"></script>
    <script type="text/javascript" src="{{ asset('/js/bootstrap.min.js') }}"></script>
    <script type="text/javascript" src="{{ asset('/js/masonry.pkgd.min.js') }}"></script>
    <script type="text/javascript" src="{{ asset('/js/owl.carousel.js') }}"></script>
    <script type="text/javascript" src="{{ asset('/js/main.js') }}"></script>
</body>
</html>
