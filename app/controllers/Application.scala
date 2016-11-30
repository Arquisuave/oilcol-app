package controllers

import javax.inject.Inject

import be.objectify.deadbolt.scala.ActionBuilders
import security.AuthSupport
import play.api.mvc.Controller
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author Steve Chaloner (steve@objectify.be)
  */
class Application @Inject()(actionBuilder: ActionBuilders,
                           authSupport: AuthSupport) extends Controller {

  def index = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
    authSupport.currentUser(authRequest).map(maybeUser =>
      Redirect("/home")) 
  }

  def home = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
        val result = controllers.Assets.at(path="/public",file="index.html")(authRequest)
        result
  }

  def pozos = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
        val result = controllers.Assets.at(path="/public",file="pozos.html")(authRequest)
        result
  }

  def campos = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
        val result = controllers.Assets.at(path="/public",file="campos.html")(authRequest)
        result
  }

  def graphs = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
        val result = controllers.Assets.at(path="/public",file="graphs.html")(authRequest)
        result
  }

  def emergencies = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
        val result = controllers.Assets.at(path="/public",file="emergency.html")(authRequest)
        result
  }

  def register = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
        val result = controllers.Assets.at(path="/public",file="register.html")(authRequest)
        result
  }

}
