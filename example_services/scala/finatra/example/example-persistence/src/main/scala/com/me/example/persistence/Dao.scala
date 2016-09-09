package com.me.example.persistence

import com.twitter.util.Future

trait Dao[K, V] {
  def get(key: K): Future[Option[V]]

  def set(key: K, data: V): Future[Unit]

  def remove(key: K): Future[Unit]

  def update(key: K, value: V): Future[Unit]
}
