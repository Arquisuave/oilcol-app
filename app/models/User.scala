package models

import be.objectify.deadbolt.scala.models.{Role, Permission, Subject}

/**
  * This simple example doesn't support roles or permissions.
  *
  * @author Steve Chaloner (steve@objectify.be)
  */
case class User(userId: String, name: String, avatarUrl: String, role: Role, permission:Permission) extends Subject {
  override def identifier: String = userId

  override def roles: List[_ <: Role] = List(role)

  override def permissions: List[_ <: Permission] = List(permission)

  def getPermissions: List[Permission] = {
        permissions
  }

  def getName: String = {
    name
  }
}
