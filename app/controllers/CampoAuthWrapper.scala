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
  * @author Steve Chaloner (steve@objectify.be)
  */
class CampoAuthWrapper @Inject()(actionBuilder: ActionBuilders,
                           authSupport: AuthSupport, campoC: CampoController) extends Controller {

    def getCampoJefe(idJefe: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            campoC.getCampoJefe(idJefe)
    }

    def getCampos() = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => campoC.getCampos(user.get))
    }

    def getCampo(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            campoC.getCampo(id)
    }

    def createCampo() = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            campoC.createCampo()
    }

    def deleteCampo(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            campoC.deleteCampo(id)
    }

    def getCampoReg(reg: String) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            campoC.getCampoReg(reg)
    }

    def getPozosAllCamposRegion(reg: String) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            campoC.getPozosAllCamposRegion(reg)
    }    




}