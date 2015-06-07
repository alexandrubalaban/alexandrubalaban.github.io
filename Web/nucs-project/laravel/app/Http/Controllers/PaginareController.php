<?php
/**
 * Created by PhpStorm.
 * User: Ana
 * Date: 5/25/2015
 * Time: 8:27 PM
 */

namespace App\Http\Controllers;


use App\Article;
use Illuminate\Support\Facades\DB;

class PaginareController extends Controller
{
    public function paginareJoin()
    {
        $nr = DB::table('articles')->join('categories','categoryId','=','categories.id')->count();
        $rowsperpage = 2;
        $totalpages = ceil($nr / $rowsperpage);

        // Obtine pagina curenta sau seteaza default
        if (isset($_GET['currentpage']) && is_numeric($_GET['currentpage']))
            $currentpage = (int)$_GET['currentpage'];  // seteaza variabila ca int
        else
            $currentpage = 1; // pagina care este initial afisata (pagina default)

        // daca pagina curenta e mai mare decat total pagini...
        if ($currentpage > $totalpages) $currentpage = $totalpages; // seteaza pagina curenta la ultima pagina
        // daca pagina curenta e mai mica decat prima pagina...
        if ($currentpage < 1) $currentpage = 1; // seteaza pagina curenta la prima pagina


        $offset = ($currentpage - 1) * $rowsperpage; // lista cu pagini, in functie de pagina curenta
        //$articles = Article::take($rowsperpage)->skip($offset)->get();
        // for ($i = 0; $i < count($articles); $i++)
        //   echo('<pre>' . $articles[$i] . '\n' . '</pre>>');


        $articles2 = DB::table('articles')->join('categories','categoryId','=','categories.id')->take($rowsperpage)->skip($offset)->get();
        //var_dump($articles2);
        //return $articles2;
        foreach( $articles2 as $art )
        {
           // echo('<pre>' . 'title = ' . $art->title .  ' nota = ' .  $art->averagerate . "\n" . '</pre>');
            echo('<pre>' . implode(' -> ',get_object_vars($art)) . "\n" . '</pre>');

        }

        $range = 3;
        // Link-uri inapoi, daca pagina curenta nu e prima
        if ($currentpage > 1) {
            echo " <a href='{$_SERVER['PHP_SELF']}?currentpage=1'>&lt;&lt;</a> &nbsp; ";  // arata << pt. link la prima pagina
            $prevpage = $currentpage - 1;  // obtine nr. pagina din urma
            echo " <a href='{$_SERVER['PHP_SELF']}?currentpage=$prevpage'>&lt;</a> &nbsp;";// arata < pt. link la o pagina in urma
        }
        // definirea link-urilor din raza paginii curente
        for ($x = ($currentpage - $range); $x < (($currentpage + $range) + 1); $x++) {
            // daca e un nr. de pagina valid ...
            if (($x > 0) && ($x <= $totalpages)) {
                // daca nr. e pagina curenta ...
                if ($x == $currentpage) {
                    // afiseaza nr. pagina fara a fi link
                    echo " [<b>$x</b>] ";
                    // daca nr. nu e pagina curenta ...
                } else {
                    // il face link
                    echo " <a href='{$_SERVER['PHP_SELF']}?currentpage=$x'>$x</a> ";
                }
            }
        }

        // Daca pagina curenta nu e ultima, afiseaza link inainte si spre ultima pagina
        if ($currentpage != $totalpages) {
            // obtine pagina urmatoare
            $nextpage = $currentpage + 1;
            // arata > pt. urmatoarea pagina
            echo "&nbsp; <a href='{$_SERVER['PHP_SELF']}?currentpage=$nextpage'>&gt;</a> ";
            //  arata >> pt. ultima pagina
            echo " &nbsp; <a href='{$_SERVER['PHP_SELF']}?currentpage=$totalpages'>&gt;&gt;</a> ";
        }

    }

    public function paginareSelect()
    {
        $nr = DB::table('articles')->join('categories','categoryId','=','categories.id')
            ->where('articles.title' , '=', 'my title2')->count();
            // 'my title 2 ar putea fi citit din formular si plasat ca si variabila aici si mai jos
        echo 'there are ' .$nr .  'articles ';

        $rowsperpage = 10;
        $totalpages = ceil($nr / $rowsperpage);

        // Obtine pagina curenta sau seteaza default
        if (isset($_GET['currentpage']) && is_numeric($_GET['currentpage']))
            $currentpage = (int)$_GET['currentpage'];  // seteaza variabila ca int
        else
            $currentpage = 1; // pagina care este initial afisata (pagina default)

        // daca pagina curenta e mai mare decat total pagini...
        if ($currentpage > $totalpages) $currentpage = $totalpages; // seteaza pagina curenta la ultima pagina
        // daca pagina curenta e mai mica decat prima pagina...
        if ($currentpage < 1) $currentpage = 1; // seteaza pagina curenta la prima pagina


        $offset = ($currentpage - 1) * $rowsperpage; // lista cu pagini, in functie de pagina curenta
        //$articles = Article::take($rowsperpage)->skip($offset)->get();
        // for ($i = 0; $i < count($articles); $i++)
        //   echo('<pre>' . $articles[$i] . '\n' . '</pre>>');


        $articles2 = DB::table('articles')->join('categories','categoryId','=','categories.id')
            ->where('articles.title' , '=', 'my title2')->take($rowsperpage)->skip($offset)->get();

        //return $articles2;
        foreach( $articles2 as $art )
        {
            // echo('<pre>' . 'title = ' . $art->title .  ' nota = ' .  $art->averagerate . "\n" . '</pre>');
            echo('<pre>' . implode(' -> ',get_object_vars($art)) . "\n" . '</pre>');

        }

        $range = 3;
        // Link-uri inapoi, daca pagina curenta nu e prima
        if ($currentpage > 1) {
            echo " <a href='{$_SERVER['PHP_SELF']}?currentpage=1'>&lt;&lt;</a> &nbsp; ";  // arata << pt. link la prima pagina
            $prevpage = $currentpage - 1;  // obtine nr. pagina din urma
            echo " <a href='{$_SERVER['PHP_SELF']}?currentpage=$prevpage'>&lt;</a> &nbsp;";// arata < pt. link la o pagina in urma
        }
        // definirea link-urilor din raza paginii curente
        for ($x = ($currentpage - $range); $x < (($currentpage + $range) + 1); $x++) {
            // daca e un nr. de pagina valid ...
            if (($x > 0) && ($x <= $totalpages)) {
                // daca nr. e pagina curenta ...
                if ($x == $currentpage) {
                    // afiseaza nr. pagina fara a fi link
                    echo " [<b>$x</b>] ";
                    // daca nr. nu e pagina curenta ...
                } else {
                    // il face link
                    echo " <a href='{$_SERVER['PHP_SELF']}?currentpage=$x'>$x</a> ";
                }
            }
        }

        // Daca pagina curenta nu e ultima, afiseaza link inainte si spre ultima pagina
        if ($currentpage != $totalpages) {
            // obtine pagina urmatoare
            $nextpage = $currentpage + 1;
            // arata > pt. urmatoarea pagina
            echo "&nbsp; <a href='{$_SERVER['PHP_SELF']}?currentpage=$nextpage'>&gt;</a> ";
            //  arata >> pt. ultima pagina
            echo " &nbsp; <a href='{$_SERVER['PHP_SELF']}?currentpage=$totalpages'>&gt;&gt;</a> ";
        }

    }
}