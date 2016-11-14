package models

import be.objectify.deadbolt.scala.models.{Role, Permission, Subject}

/**
  * This simple example doesn't support roles or permissions.
  *
  * @author Steve Chaloner (steve@objectify.be)
*/

case class OilColRole(roleName: String) extends Role {
    override def name: String = roleName
}
