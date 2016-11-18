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
class PozoAuthWrapper @Inject()(actionBuilder: ActionBuilders,
                           authSupport: AuthSupport, pozoC:PozoController) extends Controller {

    def getPozos = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            authSupport.currentUser(authRequest).map(user => pozoC.getPozos(user.get))
    }

    def createPozo = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            pozoC.createPozo()
    }    

    def updatePozo(id: Long, estado: String) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            pozoC.updatePozo(id, estado)
    }

    def getPozo(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            pozoC.getPozo(id)
    }

    def deletePozo(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            pozoC.deletePozo(id)
    }


}