package com.mapbox.maps.extension.style.layers

import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.style.layers.properties.PropertyValue

/**
 * The persistent property allows to 'tag' layers that have to persist when style is reloaded.
 * Sources required by the persistent layers would be also kept whenever style is reloaded. This
 * feature allows to avoid unnecessary layer / source re-creation that might be rather expensive for
 * large geojson sources or layers that use distance or within expressions with complex geometries.
 *
 * Persistent layers and sources would be merged with reloaded style, in case of merge conflicts,
 * MapLoadingError would be emitted and style's sources, layers and images would be prioritized when
 * added to the style.
 */
@MapboxExperimental
fun Layer.persistent(persistent: Boolean) {
  val propertyValue = PropertyValue("persistent", persistent)
  setProperty(propertyValue)
}

/**
 * Get the persistent property as Boolean.
 *
 * If true, the layer will be persisted across style changes.
 */
val Layer.persistent: Boolean?
  /**
   * Get the persistent property
   *
   * @return Boolean
   */
  get() {
    return getPropertyValue("persistent")
  }