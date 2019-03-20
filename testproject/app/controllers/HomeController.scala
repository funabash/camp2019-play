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
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

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


  def result() = Action { implicit request: Request[AnyContent] =>

    if (request.body.asFormUrlEncoded.get("name") == null) Ok(views.html.index())
    val paramName = request.body.asFormUrlEncoded.get("name")(0)


    System.out.print(paramName)

    /*
    辞書読み込み実行
     */
    //TODO: 名前のパラメータ埋め込みまだ
    val cmd = "/usr/bin/cd  /Users/kenichi_funabashi/Desktop/camp2018/mikuriya/RecommendEngine4J/sh | /usr/bin/java -jar /Users/kenichi_funabashi/Desktop/camp2018/mikuriya/RecommendEngine4J/sh/recommender.jar hiroto_mikuriya /Users/kenichi_funabashi/Desktop/camp2018/mikuriya/RecommendEngine4J/sh/ranking_result.csv"


    //val result: List[String] = exec(Seq(cmd, "/c", "dir")).out



    val result = Process(cmd).lines.toList
  /*
    val retList = List[AishoData]()
  */


    System.out.println(result)

    /*
    for (i <- 0 until result.length) {
      println(i, result(i))

      val row = result(i).split(",")
      val data =  AishoData(i+"","",row(0),row(1))

      retList :+ data
    }
   */

    val retJson = new StringBuilder

    retJson.append( "[")

    for (i <- 0 until result.length) {
      val row = result(i).split(",")
      retJson.append( "{")
      retJson.append( "\"id\":"+ (i+1) + ",")
      retJson.append( "\"icon_url\":"+ "dummy" + ",")
      retJson.append( "\"name\":"+ row(0) + ",")
      retJson.append( "\"tag\":"+ row(1) )

      retJson.append("}")

    }
    retJson.append( "]")

    Ok(retJson.toString)

    //Ok(views.html.index())
  }

  case class ExecResult(result: Int, out: List[String], err: List[String])

  def exec(cmd: Seq[String]) = {
    import scala.collection.mutable._
    import scala.sys.process._

    val out = ArrayBuffer[String]()
    val err = ArrayBuffer[String]()

    val logger = ProcessLogger(
      (o: String) => out += o,
      (e: String) => err += e)

    val r = Process(cmd) ! logger

    ExecResult(r, out.toList, err.toList)
  }

  /*
  case class AishoData(
                        id: String,
                        icon_url: String,
                        name: String,
                        tag: String
                      ){}
  object AishoData {
    implicit lazy val jsonWrites = new Writes[AishoData]{
      def writes(n: AishoData) =
        Json.obj(
          "id" -> n.id,
          "icon_url" -> n.icon_url,
          "name" -> n.name,
          "tag" -> n.tag
        )
    }
  }
  */

}
