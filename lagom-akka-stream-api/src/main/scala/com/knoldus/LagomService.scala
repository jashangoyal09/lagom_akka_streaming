package com.knoldus

import com.lightbend.lagom.scaladsl.api.{ Descriptor, Service }

trait LagomService extends Service {
  print("\n\n\n\n\nInside the LagomService\n\n\n\n\n")
  final override def descriptor: Descriptor = {
    import Service._
    named("lagom-streaming")
  }
}