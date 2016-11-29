package controllers

import javax.inject.Inject

import be.objectify.deadbolt.scala.ActionBuilders
import security.AuthSupport
import play.api.mvc.Controller
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.java8.FuturesConvertersImpl._
import scala.concurrent.{ Future, Promise, ExecutionContext, ExecutionContextExecutorService, ExecutionContextExecutor, impl }
import java.util.concurrent.{ CompletionStage, Executor, ExecutorService, CompletableFuture }

/**
  * @author edgar ft tamu
  */
class CampoAuthWrapper @Inject()(actionBuilder: ActionBuilders,
                           authSupport: AuthSupport, campoC: CampoController) extends Controller {

    def getCampoJefe(idJefe: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user =>  campoC.getCampoJefe(idJefe, user.get))
    }

    def getCampos() = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => campoC.getCampos(user.get))
    }

    def getCampo(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => campoC.getCampo(id,user.get))
    }

    def createCampo() = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => campoC.createCampo(user.get,authRequest.body.asJson.get.toString))
    }

    def deleteCampo(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => campoC.deleteCampo(id, user.get))
    }

    def getCampoReg(reg: String) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => campoC.getCampoReg(reg, user.get))
    }

    def getPozosAllCamposRegion(reg: String) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => campoC.getPozosAllCamposRegion(reg, user.get))
    }    




}