package com.knoldus.impl

import com.knoldus.UserInfo
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.{Format, Json}

import scala.collection.immutable

object LagomSerializerRegister extends JsonSerializerRegistry{
  implicit val format:Format[UserInfo] = Json.format
  override def serializers: immutable.Seq[JsonSerializer[_]] =  immutable.Seq(
    JsonSerializer.compressed(UserInfo.kafkaMessageFormat)
  )
}
