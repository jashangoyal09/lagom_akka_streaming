package com.knoldus
import play.api.libs.json.{ Format, Json }
case class UserInfo(userId:Int,source:String)

object UserInfo {

  implicit val kafkaMessageFormat: Format[UserInfo] = Json.format

}