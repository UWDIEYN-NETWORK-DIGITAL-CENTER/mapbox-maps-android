// This file is generated.

package com.mapbox.maps.extension.compose.style.lights.generated

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.mapbox.bindgen.Value
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.MapboxStyleManager
import com.mapbox.maps.extension.compose.style.BooleanValue
import com.mapbox.maps.extension.compose.style.ColorValue
import com.mapbox.maps.extension.compose.style.DoubleListValue
import com.mapbox.maps.extension.compose.style.DoubleValue
import com.mapbox.maps.extension.compose.style.IdGenerator.generateRandomLightId
import com.mapbox.maps.extension.compose.style.Transition
import com.mapbox.maps.extension.compose.style.internal.ValueParceler
import com.mapbox.maps.logD
import com.mapbox.maps.logE
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import java.util.Objects

/**
 * Create and [rememberSaveable] a [DirectionalLightState] using [Saver].
 * [init] will be called when the [DirectionalLightState] is first created to configure its
 * initial state.
 *
 * @param key An optional key to be used as a key for the saved value. If not provided we use the
 * automatically generated by the Compose runtime which is unique for the every exact code location
 * in the composition tree.
 * @param init A function initialise this [DirectionalLightState].
 */
@Composable
public inline fun rememberDirectionalLightState(
  id: String = remember {
    generateRandomLightId("directional")
  },
  key: String? = null,
  crossinline init: DirectionalLightState.() -> Unit = {}
): DirectionalLightState = rememberSaveable(key = key, saver = DirectionalLightState.Saver) {
  DirectionalLightState(id).apply(init)
}

/**
 * A light that has a direction and is located at infinite distance, so its rays are parallel. It simulates the sun light and can cast shadows.
 *
 * @see [The online documentation](https://docs.mapbox.com/style-spec/reference/light)
 */
@Stable
public class DirectionalLightState internal constructor(
  /**
   * The id of the [DirectionalLightState].
   */
  public val id: String,
  initialCastShadows: BooleanValue = BooleanValue.INITIAL,
  initialColor: ColorValue = ColorValue.INITIAL,
  initialColorTransition: Transition = Transition.INITIAL,
  initialDirection: DoubleListValue = DoubleListValue.INITIAL,
  initialDirectionTransition: Transition = Transition.INITIAL,
  initialIntensity: DoubleValue = DoubleValue.INITIAL,
  initialIntensityTransition: Transition = Transition.INITIAL,
  initialShadowIntensity: DoubleValue = DoubleValue.INITIAL,
  initialShadowIntensityTransition: Transition = Transition.INITIAL,
  initialShadowQuality: DoubleValue = DoubleValue.INITIAL,
) {
  public constructor(id: String = generateRandomLightId("directional")) : this(
    id = id,
    initialCastShadows = BooleanValue.INITIAL,
    initialColor = ColorValue.INITIAL,
    initialColorTransition = Transition.INITIAL,
    initialDirection = DoubleListValue.INITIAL,
    initialDirectionTransition = Transition.INITIAL,
    initialIntensity = DoubleValue.INITIAL,
    initialIntensityTransition = Transition.INITIAL,
    initialShadowIntensity = DoubleValue.INITIAL,
    initialShadowIntensityTransition = Transition.INITIAL,
    initialShadowQuality = DoubleValue.INITIAL,
  )

  private val castShadowsState: MutableState<BooleanValue> = mutableStateOf(initialCastShadows)
  private val colorState: MutableState<ColorValue> = mutableStateOf(initialColor)
  private val colorTransitionState: MutableState<Transition> = mutableStateOf(initialColorTransition)
  private val directionState: MutableState<DoubleListValue> = mutableStateOf(initialDirection)
  private val directionTransitionState: MutableState<Transition> = mutableStateOf(initialDirectionTransition)
  private val intensityState: MutableState<DoubleValue> = mutableStateOf(initialIntensity)
  private val intensityTransitionState: MutableState<Transition> = mutableStateOf(initialIntensityTransition)
  private val shadowIntensityState: MutableState<DoubleValue> = mutableStateOf(initialShadowIntensity)
  private val shadowIntensityTransitionState: MutableState<Transition> = mutableStateOf(initialShadowIntensityTransition)
  private val shadowQualityState: MutableState<DoubleValue> = mutableStateOf(initialShadowQuality)

  /**
   * Enable/Disable shadow casting for this light
   * Default value: false.
   */
  public var castShadows: BooleanValue by castShadowsState

  /**
   * Color of the directional light.
   * Default value: "#ffffff".
   */
  public var color: ColorValue by colorState

  /**
   * Defines the transition of [color].
   * Default value: "#ffffff".
   */
  public var colorTransition: Transition by colorTransitionState

  /**
   * Direction of the light source specified as [a azimuthal angle, p polar angle] where a indicates
   * the azimuthal angle of the light relative to north (in degrees and proceeding clockwise), and p
   * indicates polar angle of the light (from 0 degree, directly above, to 180 degree, directly below).
   * Default value: [210,30]. Minimum value: [0,0]. Maximum value: [360,90].
   */
  public var direction: DoubleListValue by directionState

  /**
   * Defines the transition of [direction].
   * Default value: [210,30]. Minimum value: [0,0]. Maximum value: [360,90].
   */
  public var directionTransition: Transition by directionTransitionState

  /**
   * A multiplier for the color of the directional light.
   * Default value: 0.5. Value range: [0, 1]
   */
  public var intensity: DoubleValue by intensityState

  /**
   * Defines the transition of [intensity].
   * Default value: 0.5. Value range: [0, 1]
   */
  public var intensityTransition: Transition by intensityTransitionState

  /**
   * Determines the shadow strength, affecting the shadow receiver surfaces final color. Values near 0.0 reduce the
   * shadow contribution to the final color. Values near to 1.0 make occluded surfaces receive almost no
   * directional light. Designed to be used mostly for transitioning between values 0 and 1.
   * Default value: 1. Value range: [0, 1]
   */
  public var shadowIntensity: DoubleValue by shadowIntensityState

  /**
   * Defines the transition of [shadowIntensity].
   * Default value: 1. Value range: [0, 1]
   */
  public var shadowIntensityTransition: Transition by shadowIntensityTransitionState

  /**
   * Determines the quality of the shadows on the map. A value of 1 ensures the highest
   * quality and is the default value.
   * Default value: 1. Value range: [0, 1]
   */
  @MapboxExperimental
  public var shadowQuality: DoubleValue by shadowQualityState

  @Composable
  private fun UpdateCastShadows(mapboxMap: MapboxStyleManager) {
    if (castShadows.notInitial) {
      mapboxMap.updateLightProperty("cast-shadows", castShadows.value)
    }
  }

  @Composable
  private fun UpdateColor(mapboxMap: MapboxStyleManager) {
    if (color.notInitial) {
      mapboxMap.updateLightProperty("color", color.value)
    }
  }

  @Composable
  private fun UpdateColorTransition(mapboxMap: MapboxStyleManager) {
    if (colorTransition.notInitial) {
      mapboxMap.updateLightProperty("color-transition", colorTransition.value)
    }
  }

  @Composable
  private fun UpdateDirection(mapboxMap: MapboxStyleManager) {
    if (direction.notInitial) {
      mapboxMap.updateLightProperty("direction", direction.value)
    }
  }

  @Composable
  private fun UpdateDirectionTransition(mapboxMap: MapboxStyleManager) {
    if (directionTransition.notInitial) {
      mapboxMap.updateLightProperty("direction-transition", directionTransition.value)
    }
  }

  @Composable
  private fun UpdateIntensity(mapboxMap: MapboxStyleManager) {
    if (intensity.notInitial) {
      mapboxMap.updateLightProperty("intensity", intensity.value)
    }
  }

  @Composable
  private fun UpdateIntensityTransition(mapboxMap: MapboxStyleManager) {
    if (intensityTransition.notInitial) {
      mapboxMap.updateLightProperty("intensity-transition", intensityTransition.value)
    }
  }

  @Composable
  private fun UpdateShadowIntensity(mapboxMap: MapboxStyleManager) {
    if (shadowIntensity.notInitial) {
      mapboxMap.updateLightProperty("shadow-intensity", shadowIntensity.value)
    }
  }

  @Composable
  private fun UpdateShadowIntensityTransition(mapboxMap: MapboxStyleManager) {
    if (shadowIntensityTransition.notInitial) {
      mapboxMap.updateLightProperty("shadow-intensity-transition", shadowIntensityTransition.value)
    }
  }

  @Composable
  private fun UpdateShadowQuality(mapboxMap: MapboxStyleManager) {
    if (shadowQuality.notInitial) {
      mapboxMap.updateLightProperty("shadow-quality", shadowQuality.value)
    }
  }

  private fun MapboxStyleManager.updateLightProperty(name: String, value: Value) {
    logD(TAG, "update light property: $id, $name, $value")
    setStyleLightProperty(id, name, value)
      .onError {
        logE(TAG, it)
      }
  }

  internal fun getProperties(): HashMap<String, Value> {
    return hashMapOf(
      "id" to Value(id),
      "type" to Value("directional"),
      "properties" to Value(
        hashMapOf<String, Value>().apply {
          if (castShadows.notInitial) {
            this["cast-shadows"] = castShadows.value
          }
          if (color.notInitial) {
            this["color"] = color.value
          }
          if (colorTransition.notInitial) {
            this["color-transition"] = colorTransition.value
          }
          if (direction.notInitial) {
            this["direction"] = direction.value
          }
          if (directionTransition.notInitial) {
            this["direction-transition"] = directionTransition.value
          }
          if (intensity.notInitial) {
            this["intensity"] = intensity.value
          }
          if (intensityTransition.notInitial) {
            this["intensity-transition"] = intensityTransition.value
          }
          if (shadowIntensity.notInitial) {
            this["shadow-intensity"] = shadowIntensity.value
          }
          if (shadowIntensityTransition.notInitial) {
            this["shadow-intensity-transition"] = shadowIntensityTransition.value
          }
          if (shadowQuality.notInitial) {
            this["shadow-quality"] = shadowQuality.value
          }
        }
      )
    )
  }

  @Composable
  internal fun UpdateProperties(mapboxMap: MapboxMap) {
    UpdateCastShadows(mapboxMap)
    UpdateColor(mapboxMap)
    UpdateColorTransition(mapboxMap)
    UpdateDirection(mapboxMap)
    UpdateDirectionTransition(mapboxMap)
    UpdateIntensity(mapboxMap)
    UpdateIntensityTransition(mapboxMap)
    UpdateShadowIntensity(mapboxMap)
    UpdateShadowIntensityTransition(mapboxMap)
    UpdateShadowQuality(mapboxMap)
  }

  /**
   * Overwrite the hashcode for [DirectionalLightState].
   */
  override fun hashCode(): Int {
    return Objects.hash(
      castShadows,
      color,
      colorTransition,
      direction,
      directionTransition,
      intensity,
      intensityTransition,
      shadowIntensity,
      shadowIntensityTransition,
      shadowQuality,
    )
  }

  /**
   * Overwrite the equals for [DirectionalLightState].
   */
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as DirectionalLightState
    if (id != other.id) return false
    if (castShadows != other.castShadows) return false
    if (color != other.color) return false
    if (colorTransition != other.colorTransition) return false
    if (direction != other.direction) return false
    if (directionTransition != other.directionTransition) return false
    if (intensity != other.intensity) return false
    if (intensityTransition != other.intensityTransition) return false
    if (shadowIntensity != other.shadowIntensity) return false
    if (shadowIntensityTransition != other.shadowIntensityTransition) return false
    if (shadowQuality != other.shadowQuality) return false
    return true
  }

  /**
   * Overwrite the toString for [DirectionalLightState].
   */
  override fun toString(): String {
    return "DirectionalLightState(castShadows=$castShadows, color=$color, colorTransition=$colorTransition, direction=$direction, directionTransition=$directionTransition, intensity=$intensity, intensityTransition=$intensityTransition, shadowIntensity=$shadowIntensity, shadowIntensityTransition=$shadowIntensityTransition, shadowQuality=$shadowQuality)"
  }

  /**
   * [DirectionalLightState] Holder class to be used within [Saver].
   *
   * @param savedProperties properties to be saved.
   */
  @Parcelize
  @TypeParceler<Value, ValueParceler>
  public data class Holder(
    val savedProperties: Map<String, Value>,
  ) : Parcelable

  /**
   * Public companion object.
   */
  public companion object {
    private const val TAG = "DirectionalLightState"

    /**
     * The default saver implementation for [DirectionalLightState].
     */
    public val Saver: Saver<DirectionalLightState, Holder> = Saver(
      save = { directionalLightState ->
        Holder(directionalLightState.getProperties()).also {
          logD(TAG, "save: $it")
        }
      },
      restore = { holder ->
        logD(TAG, "restore: $holder")
        val id: String = holder.savedProperties["id"]!!.contents as String
        @Suppress("UNCHECKED_CAST")
        val properties: Map<String, Value> = holder.savedProperties["properties"]!!.contents as HashMap<String, Value>
        DirectionalLightState(
          id = id,
          initialCastShadows = properties["cast-shadows"]?.let { BooleanValue(it) } ?: BooleanValue.INITIAL,
          initialColor = properties["color"]?.let { ColorValue(it) } ?: ColorValue.INITIAL,
          initialColorTransition = properties["color-transition"]?.let { Transition(it) } ?: Transition.INITIAL,
          initialDirection = properties["direction"]?.let { DoubleListValue(it) } ?: DoubleListValue.INITIAL,
          initialDirectionTransition = properties["direction-transition"]?.let { Transition(it) } ?: Transition.INITIAL,
          initialIntensity = properties["intensity"]?.let { DoubleValue(it) } ?: DoubleValue.INITIAL,
          initialIntensityTransition = properties["intensity-transition"]?.let { Transition(it) } ?: Transition.INITIAL,
          initialShadowIntensity = properties["shadow-intensity"]?.let { DoubleValue(it) } ?: DoubleValue.INITIAL,
          initialShadowIntensityTransition = properties["shadow-intensity-transition"]?.let { Transition(it) } ?: Transition.INITIAL,
          initialShadowQuality = properties["shadow-quality"]?.let { DoubleValue(it) } ?: DoubleValue.INITIAL,
        )
      }
    )
  }
}
// End of generated file.