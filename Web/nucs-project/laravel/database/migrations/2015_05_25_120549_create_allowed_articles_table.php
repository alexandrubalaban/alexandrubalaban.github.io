<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAllowedArticlesTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('allowed_articles', function(Blueprint $table)
		{
            $table->integer('articleId')->unsigned();
            $table->integer('userId')->unsigned();

            $table->primary(array('userId','articleId'));


            $table->foreign('userId')
                ->references('id')-> on('users')->onDelete('cascade');
            $table->foreign('articleId')
                ->references('id')-> on('articles')->onDelete('cascade');
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('allowed_articles');
	}

}
