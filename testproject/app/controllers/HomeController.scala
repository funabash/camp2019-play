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





}
