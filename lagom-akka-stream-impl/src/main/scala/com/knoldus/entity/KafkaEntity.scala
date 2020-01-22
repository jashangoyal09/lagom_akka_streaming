package com.knoldus.entity

import akka.actor.ActorSystem
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity

class KafkaEntity(actorSystem: ActorSystem) extends PersistentEntity{
  override type Command = this.type
  override type Event = this.type
  override type State = this.type

  override def initialState: KafkaEntity.this.type = ???

  override def behavior: Behavior = ???
}
