<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDefinedLinkTable extends Migration {

    public $timestamps = false;

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('definedLinks', function(Blueprint $table)
		{


            $table->string('URL')->unique();
            $table->increments('id');

		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('definedLinks');
	}

}
