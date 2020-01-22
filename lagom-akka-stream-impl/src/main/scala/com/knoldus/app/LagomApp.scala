package com.knoldus.app

import com.knoldus.{KafkaService, LagomService}
import com.knoldus.impl.{KafkaInfoIngestStream}
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext}
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import org.slf4j.LoggerFactory
import com.softwaremill.macwire.wire
import play.api.libs.ws.ahc.AhcWSComponents

abstract class LagomApp(context: LagomApplicationContext) extends
  LagomApplication(context) with LagomAppComponents with AhcWSComponents with LagomKafkaComponents {
  print("\n\n\n\nInside the LagomApp\n\n\n\n\n\n")
  lazy val lagomService: LagomService = serviceClient.implement[LagomService]

  protected val log = LoggerFactory.getLogger(getClass())
  lazy val kafkaService = serviceClient.implement[KafkaService]
  private def onClusterMemberUp(): Unit = {

    wire[KafkaInfoIngestStream]
  }


  cluster.registerOnMemberUp(onClusterMemberUp)
}
