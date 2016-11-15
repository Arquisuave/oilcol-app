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
class ReportAuthWrapper @Inject()(actionBuilder: ActionBuilders,
                           authSupport: AuthSupport, reportC: ReportController) extends Controller {

    def generateReportTemp() = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            reportC.generateReportTemp()
    }    

    def generateReportBarriles() = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            reportC.generateReportBarriles()
    }

    def generateReportEner() = actionBuilder.SubjectPresentAction().defaultHandler() { authRequest =>
            reportC.generateReportEner()
    }

}