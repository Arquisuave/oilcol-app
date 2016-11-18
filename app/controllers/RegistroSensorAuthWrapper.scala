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
class RegistroSensorAuthWrapper @Inject()(actionBuilder: ActionBuilders,
                           authSupport: AuthSupport, registroC: RegistroSensorController) extends Controller {

    def getLastRegistroSensorTemp(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            registroC.getLastRegistroSensorTemp(id)
    }    

    def getLastRegistroSensorBarr(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            registroC.getLastRegistroSensorBarr(id)
    }

    def getLastRegistroSensorEner(id: Long) = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            registroC.getLastRegistroSensorEner(id)
    }

}