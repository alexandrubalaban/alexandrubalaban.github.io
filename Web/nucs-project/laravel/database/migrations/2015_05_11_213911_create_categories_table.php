<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateCategoriesTable extends Migration {
    public $timestamps = false;
	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
    public function up()
    {
        Schema::create('categories', function(Blueprint $table)
        {
            $table->increments('id');
            $table->string('name');
            $table->integer('parent')->nullable();
        });
        Schema::create('article_categories', function(Blueprint $table){
            $table->integer('article_id')->unsigned()->index();
            $table->integer('categories_id')->unsigned()->index();

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

        Schema::drop('article_categories');
		Schema::drop('categories');

	}

}
