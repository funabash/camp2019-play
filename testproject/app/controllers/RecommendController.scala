package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import scala.collection.mutable
import scala.sys.process._
import play.api.libs.json.Json
import scala.collection.mutable.Map
import play.api.libs.json.JsValue
import play.api.libs.json._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class RecommendController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }


  def get() = Action { implicit request: Request[AnyContent] =>

    if (request.body.asFormUrlEncoded.get("name") == null) Ok(views.html.index())
    val paramName = request.body.asFormUrlEncoded.get("name")(0)


    System.out.println(paramName)

    /*
    辞書読み込み実行
     */
    //TODO: 名前のパラメータ埋め込みまだ
    val cmd = "/usr/bin/java -jar /Users/kenichi_funabashi/Desktop/camp2018/mikuriya/RecommendEngine4J/sh/recommender.jar " + paramName + " /Users/kenichi_funabashi/Desktop/camp2018/mikuriya/RecommendEngine4J/sh/ranking_result.csv"



  System.out.println("cmd==" + cmd)

    //val result: List[String] = exec(Seq(cmd, "/c", "dir")).out

    val result = Process(cmd).lines.toList


    val retJson = new StringBuilder

    retJson.append( "[")

    for (i <- 0 until result.length) {
      val row = result(i).split(",")
      retJson.append( "{")
      retJson.append( "\"id\":"+ (i+1) + ",")
      retJson.append( "\"icon_url\":"+ "dummy" + ",")
      retJson.append( "\"name\":"+ row(0) + ",")

      //3項目からワードが続くので取れるまで取得
      var n = 2
      var tags = new StringBuilder

      while(row.length > n){
        tags.append( row(n)).append(",")
        n += 1
      }
      retJson.append( "\"tag\":"+ tags.toString() )

      retJson.append("}")

    }
    retJson.append( "]")

    Ok(retJson.toString)

    //Ok(views.html.index())
  }




}
