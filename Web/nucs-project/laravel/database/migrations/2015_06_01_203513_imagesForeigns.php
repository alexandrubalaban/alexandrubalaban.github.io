<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class ImagesForeigns extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{

        Schema::table('article_images', function (Blueprint $table) {
            $table->foreign('article_id')->references('id')->on('articles')->onDelete('cascade');
        });
        Schema::table('user_images', function (Blueprint $table) {
            $table->foreign('user_id')->references('id')->on('users')->onDelete('cascade');
        });
        Schema::table('article_types', function (Blueprint $table) {
            $table->foreign('article_id')->references('id')->on('articles')->onDelete('cascade');
        });

    }

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{

        Schema::table('article_images', function (Blueprint $table) {
            $table->dropForeign('article_images_article_id_foreign');
        });
        Schema::table('user_images', function (Blueprint $table) {
            $table->dropForeign('user_images_user_id_foreign');
        });
        Schema::table('article_types', function (Blueprint $table) {
            $table->dropForeign('article_types_article_id_foreign');
        });
	}

}
