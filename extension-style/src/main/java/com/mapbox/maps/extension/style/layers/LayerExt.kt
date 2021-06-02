package com.mapbox.maps.extension.style.layers

import com.mapbox.maps.MapboxExperimental

/**
 * Changes layer's persistent flag. The flag could be set only to a layer that was added at runtime using
 * addStyleLayer or addStyleCustomLayer methods.
 *
 * Note: This is an experimental API and intended for internal use only.
 *
 * Whenever a new style is being parsed and Map has persistent layers, engine will try to do following:
 *   - keep persisent layer at it's relative position
 *   - keep source used by a persistent layer
 *   - keep images added through addStyleImage method
 *
 * In cases when a new style has the same layer, source or image resource, style's resources would be
 * used instead and 'MapLoadingError' event will be emitted.
 *
 * @param persistent flag indicating whether layer should be persistent.
 * @return A string describing an error if the operation was not successful, empty otherwise.
 */
@MapboxExperimental
fun Layer.persistent(persistent: Boolean) {
  if (this.delegate == null) {
    throw RuntimeException("Persistent property must be set after the layer is added to the style.")
  }
  this.delegate?.setStyleLayerPersistent(layerId, persistent)
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
    return delegate?.isStyleLayerPersistent(layerId)?.value
  }