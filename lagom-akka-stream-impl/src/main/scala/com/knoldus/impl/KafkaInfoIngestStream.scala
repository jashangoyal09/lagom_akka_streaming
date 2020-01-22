package com.knoldus.impl

import akka.Done
import com.knoldus.{KafkaService, LagomService, UserInfo}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import akka.stream.scaladsl.Flow
import com.knoldus.entity.KafkaEntity
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{ExecutionContext, Future}

class KafkaInfoIngestStream(registry: PersistentEntityRegistry,kafkaService:KafkaService)(implicit exec: ExecutionContext){
  lazy val subscriptionFlow = kafkaInfoToEntitySubscriptionFlow

  kafkaService.kafkaTopic.subscribe.withGroupId("kafka-service").atLeastOnce(
    subscriptionFlow
  )
  print("\n\n\n\ninside KafkaInfoIngestStream\n\n\n\n\n\n")
  private val log: Logger = LoggerFactory.getLogger(getClass)

  val sendKafkaInfoToEntities: Flow[UserInfo, Done, _] =
    Flow[UserInfo].mapAsync(8) { data =>
      log.debug(s"Got tenant info from Kafka for ")
//      registry.refFor[KafkaEntity](data).ask(HandleIncomingTenant(data))
      print(s"\n\n\ngot data \n\n\n${data}\n")
      Future(Done)
    }

  val kafkaInfoToEntitySubscriptionFlow: Flow[UserInfo, Done, _] = Flow[UserInfo]
    .via(sendKafkaInfoToEntities)
}
//class IngestStream(kafkaInfoIngestStream: KafkaInfoIngestStream,kafkaService:KafkaService)(implicit exec: ExecutionContext) {
//
//    lazy val subscriptionFlow = kafkaInfoIngestStream.kafkaInfoToEntitySubscriptionFlow
//  print("\n\n\n\n\ninside the IngestStream\n\n\n\n\n")
//  kafkaService.kafkaTopic.subscribe.withGroupId("kafka-service").atLeastOnce(
//    subscriptionFlow
//  )
//}
