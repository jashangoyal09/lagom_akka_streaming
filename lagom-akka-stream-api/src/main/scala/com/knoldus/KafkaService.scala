package com.knoldus

import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service}

trait KafkaService extends Service {
  print("\n\n\n\n\nInside the KafkaService\n\n\n\n\n")
 def kafkaTopic: Topic[UserInfo]

  final override def descriptor: Descriptor = {
    import Service._

    named("kafka-service").withTopics(
      topic("Topic4", this.kafkaTopic)
    ).withAutoAcl(true)

  }
}
