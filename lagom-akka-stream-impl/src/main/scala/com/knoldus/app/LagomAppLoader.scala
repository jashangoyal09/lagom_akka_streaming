package com.knoldus.app

import com.knoldus.LagomService
import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader}
import com.lightbend.rp.servicediscovery.lagom.scaladsl.LagomServiceLocatorComponents
import play.api.LoggerConfigurator

class LagomAppLoader extends LagomApplicationLoader{
  print("\n\n\n\nInside the LagomAppLoader\n\n\n\n\n\n")
  override def load(context: LagomApplicationContext): LagomApplication =
    new LagomApp(context) with LagomServiceLocatorComponents

  override def loadDevMode(context: LagomApplicationContext): LagomApplication = {
    initLogging(context)
    new LagomApp(context) with LagomDevModeComponents
  }

  private def initLogging(context: LagomApplicationContext): Unit = {
    val environment = context.playContext.environment
    LoggerConfigurator(environment.classLoader).foreach {
      _.configure(environment)
    }
  }
  override def describeService: Option[Descriptor] = Some(
    readDescriptor[LagomService]
  )
}
