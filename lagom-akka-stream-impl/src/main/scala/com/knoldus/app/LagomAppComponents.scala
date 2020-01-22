package com.knoldus.app

import akka.stream.Materializer
import com.knoldus.LagomService
import com.knoldus.entity.KafkaEntity
import com.lightbend.lagom.scaladsl.server.{LagomServer, LagomServerComponents}
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.playjson.JsonSerializerRegistry
import com.softwaremill.macwire.wire
import com.knoldus.impl._
import play.api.Environment

import scala.concurrent.ExecutionContext

trait LagomAppComponents  extends LagomServerComponents
  with CassandraPersistenceComponents {
  print("\n\n\n\nInside the lagomAppComponent\n\n\n\n\n\n")
  implicit def materializer: Materializer

  implicit def executionContext: ExecutionContext

  override lazy val lagomServer: LagomServer = serverFor[LagomService](wire[LagomServiceImpl])
  override lazy val jsonSerializerRegistry: JsonSerializerRegistry = LagomSerializerRegister


  def environment: Environment

  persistentEntityRegistry.register(wire[KafkaEntity])
}
