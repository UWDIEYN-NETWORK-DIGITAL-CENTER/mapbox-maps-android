package com.mapbox.maps.extension.style.layers

import com.mapbox.maps.extension.style.layers.properties.PropertyValue

fun Layer.persistent(persistent: Boolean) {
  val propertyValue = PropertyValue("persistent", persistent)
  setProperty(propertyValue)
}

val Layer.persistent: Boolean?
  get() {
    return getPropertyValue("persistent")
  }