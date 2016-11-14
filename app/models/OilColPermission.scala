package models

import be.objectify.deadbolt.scala.models.{Role, Permission, Subject}

/**
  * This simple example doesn't support roles or permissions.
  *
  * @author Steve Chaloner (steve@objectify.be)
*/

case class OilColPermission(permValue: String) extends Permission {
    override def value: String = permValue
}
