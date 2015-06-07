<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;
use Illuminate\Support\Facades\DB;

class CreateArticlesTable extends Migration {
    protected $timestamps = false;

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
    public function up()
    {
        Schema::create('articles', function(Blueprint $table){

            $table->increments('id');
            $table->longText('title');
            $table->integer('user_id')->unsigned();
            $table->string('excerpt',255);
            $table->string('body',4000)->unique();
            $table->longText('sourceName');
            $table->longText('linkURL');
            $table->integer('views')->default(0);
            $table->timestamp('published_at');
            $table->timestamps();



        });


    }



	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{

		Schema::drop('articles');
	}

}
