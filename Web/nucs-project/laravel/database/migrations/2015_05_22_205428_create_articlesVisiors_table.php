<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateArticlesVisiorsTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */

    public function up()
    {
        Schema::create('articleVisitors', function(Blueprint $table)
        {



            $table->integer('articleId')->unsigned();
            $table->integer('userId')->unsigned();
            $table->timestamp('addDate');
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
		Schema::drop('articleVisitors');
	}

}
