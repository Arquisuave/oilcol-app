package controllers

import javax.inject.Inject

import be.objectify.deadbolt.scala.ActionBuilders
import security.AuthSupport
import play.api.mvc.Controller
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.java8.FuturesConvertersImpl._
import scala.concurrent.{ Future, Promise, ExecutionContext, ExecutionContextExecutorService, ExecutionContextExecutor, impl }
import java.util.concurrent.{ CompletionStage, Executor, ExecutorService, CompletableFuture }

object Utilities
{
    def toScala[T](cs: CompletionStage[T]): Future[T] = {
        cs match {
          case cf: CF[T] => cf.wrapped
          case _ =>
            val p = new P[T](cs)
            cs whenComplete p
            p.future
        }
    }
}