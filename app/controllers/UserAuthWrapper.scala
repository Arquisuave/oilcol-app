package controllers

import javax.inject.Inject

import be.objectify.deadbolt.scala.ActionBuilders
import play.api.mvc.Controller
import security.AuthSupport

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author jg.tamura10
  */
class UserAuthWrapper @Inject()(actionBuilder: ActionBuilders,
                                authSupport: AuthSupport, userC:UserControllerAuthNOOriginal) extends Controller {

    def getUser = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => userC.getUser(user.get))
    }




}