package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import lib._

import scala.collection.mutable
import scala.sys.process._
import play.api.libs.json.Json
import scala.collection.mutable.Map
import play.api.libs.json._
import play.api.libs.functional.syntax._



/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class FactionController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def get() = Action { implicit request: Request[AnyContent] =>

    if (request.body.asFormUrlEncoded.get("name") == null) Ok("{\"OK\":false}")
    val paramName = request.body.asFormUrlEncoded.get("name")(0)


    System.out.println(paramName)


    val json = Json.parse(FactionJson)

    val factioJson = (json \ "factions" )

    System.out.println(factioJson)

    /*
    val factionResult: JsResult[Faction] = factioJson.validate[Faction]

    System.out.println(factionResult)
   */

    val factionResult: JsResult[Factions] = json.validate[Factions]

    System.out.println(factionResult)

    //val mappass = factionResult.get()

    val fPass = factionResult.get
    println(fPass)

    val facList :List[Faction] = fPass.factions

    facList.foreach( f =>

      if(f.name == paramName){

        val retJson = new StringBuilder

          retJson.append( "{")
          retJson.append( "\"name\":"+ f.name + ",")
          retJson.append( "\"miya\":"+ f.miya + ",")
          retJson.append( "\"mi2\":"+ f.mi2 + ",")
          retJson.append( "\"mi2\":"+ f.mi2 + ",")
          retJson.append( "\"moz\":"+ f.moz + ",")
          retJson.append( "\"numa\":"+ f.numa + ",")
          retJson.append( "\"u\":"+ f.u )

          retJson.append("}")

        Ok(retJson.toString)
      }

    )

    Ok("{\"OK\":false}")

  }

  case class Faction(name: String, miya: Int, mi2: Int, moz: Int, numa: Int,u: Int)

  case class Factions(factions: List[Faction])

  implicit val factionFormat:Format[Faction] = Json.format[Faction]

  implicit val factionsFormat:Format[Factions] = Json.format[Factions]

  /*
  implicit val factionReads: Reads[Faction] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "miya").read[Int] and
      (JsPath \ "mi2").read[Int] and
      (JsPath \ "moz").read[Int] and
      (JsPath \ "numa").read[Int] and
      (JsPath \ "u").read[Int]
    )(Faction.apply _)

  implicit val factionsReads: Reads[Factions] = (
    (JsPath \ "factions").read[String] and
      (JsPath \ "Faction").read[Faction]
    )(Factions.apply _)
*/

  val FactionJson = """{
                      	"factions": [
                      		{
                      			"name": "akirasannomiya",
                      			"miya": 4,
                      			"mi2": 2,
                      			"moz": 3,
                      			"numa": 5,
                      			"u": 1
                      		},
                      		{
                      			"name": "haruyama",
                      			"miya": 4,
                      			"mi2": 2,
                      			"moz": 3,
                      			"numa": 5,
                      			"u": 1
                      		},
                      		{
                      			"name": "atsushi.coke.k",
                      			"miya": 5,
                      			"mi2": 2,
                      			"moz": 3,
                      			"numa": 4,
                      			"u": 1
                      		},
                          {
                            "name": "mayuki_miyata",
                          	"miya": 4,
                          	"mi2": 2,
                          	"moz": 3,
                          	"numa": 5,
                          	"u": 1
                            },
                            {
                           	"name": "minoru_matsushima",
                           	"miya": 4,
                           	"mi2": 3,
                           	"moz": 2,
                           	"numa": 5,
                           	"u": 1
                           	},
                           	{
                           	"name": "miryon_kim",
                           	"miya": 4,
                           	"mi2": 2,
                           	"moz": 3,
                           	"numa": 5,
                           	"u": 1
                           	}
                           ]
                        }
                        """

}


