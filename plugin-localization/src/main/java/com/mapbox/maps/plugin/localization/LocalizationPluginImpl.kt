package com.mapbox.maps.plugin.localization

import com.mapbox.common.Logger
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.StyleObjectInfo
import com.mapbox.maps.extension.style.StyleInterface
import com.mapbox.maps.extension.style.expressions.dsl.generated.get
import com.mapbox.maps.extension.style.expressions.generated.Expression
import com.mapbox.maps.extension.style.layers.generated.SymbolLayer
import com.mapbox.maps.extension.style.layers.getLayerAs
import com.mapbox.maps.extension.style.sources.generated.VectorSource
import com.mapbox.maps.extension.style.sources.getSourceAs
import com.mapbox.maps.plugin.delegates.MapPluginProviderDelegate
import com.mapbox.maps.toJson
import java.util.*

/**
 * Impl class for LocalizationPlugin
 */
class LocalizationPluginImpl : LocalizationPlugin {
  private var mapLocale: MapLocale? = null
  private var styleDelegate: StyleInterface? = null

  /**
   * Initializing this plugin and then calling this method oftentimes will be the only thing you'll
   * need to quickly adjust the map language to the devices specified language.
   *
   * @param acceptFallback whether the locale should fallback to the first declared that matches the language,
   * the fallback locale can be added with [MapLocale#addMapLocale(Locale, MapLocale)], default is false.
   */
  override fun matchMapLanguageWithDeviceDefault(acceptFallback: Boolean) {
    setMapLanguage(Locale.getDefault(), acceptFallback)
  }

  /**
   * Set the map language directly by using one of the supported map languages found in
   * [Languages].
   *
   * @param language one of the support languages Mapbox uses
   */
  override fun setMapLanguage(language: Languages) {
    setMapLanguage(MapLocale(language))
  }

  /**
   * If you'd like to set the map language to a specific locale, you can pass it in as a parameter
   * and MapLocale will try matching the information with one of the MapLocales found in its map.
   * If one isn't found, a null point exception will be thrown. To prevent this, ensure that the
   * locale you are trying to use, has a complementary [MapLocale] for it.
   *
   * @param locale a [Locale] which has a complementary [MapLocale] for it
   * @param acceptFallback whether the locale should fallback to the first declared that matches the language,
   * the fallback locale can be added with [MapLocale.addMapLocale], default is false.
   */
  override fun setMapLanguage(locale: Locale, acceptFallback: Boolean) {
    MapLocale.getMapLocale(locale, acceptFallback)?.let {
      setMapLanguage(it)
      return
    }
    Logger.w(TAG, "Couldn't match Locale $locale ${locale.displayName} to a MapLocale")
  }

  /**
   * You can pass in a []MapLocale] directly into this method which uses the language defined
   * in it to represent the language found on the map.
   *
   * @param mapLocale the [MapLocale] object which contains the desired map language
   */
  override fun setMapLanguage(mapLocale: MapLocale) {
    this.mapLocale = mapLocale
    styleDelegate?.let { style ->
      style.styleSources
        .filter { sourceIsFromMapbox(style, it) }
        .forEach { source ->
          style.styleLayers
            .filter { it.type == LAYER_TYPE_SYMBOL }
            .forEach { layer ->
              val symbolLayer = style.getLayerAs<SymbolLayer>(layer.id)
              symbolLayer.textFieldAsExpression?.let { textFieldExpression ->
                if (sourceIsStreetsV8(style, source)) {
                  convertExpressionV8(mapLocale, symbolLayer, textFieldExpression)
                } else {
                  convertExpression(
                    mapLocale,
                    symbolLayer,
                    textFieldExpression,
                    sourceIsStreetsV7(style, source)
                  )
                }
              }
            }
        }
    }
  }

  override fun onStyleChanged(styleDelegate: StyleInterface) {
    this.styleDelegate = styleDelegate
    mapLocale?.let {
      setMapLanguage(it)
    }
  }

  private fun convertExpression(
    mapLocale: MapLocale, layer: SymbolLayer,
    textFieldExpression: Expression?, isStreetsV7: Boolean
  ) {
    textFieldExpression?.let {
      var newMapLocale = mapLocale
      val mapLanguage = mapLocale.mapLanguage
      if (mapLanguage == Languages.CHINESE) {
        // need to re-get mapLocale, since the default is for street-v8
        newMapLocale = getChineseMapLocale(mapLocale, isStreetsV7)
      }
      var text: String = it.toJson().replace(EXPRESSION_REGEX, newMapLocale.mapLanguage.value)
      //todo: find a way to resolve textFieldExpression.toArray()
//      if (text.startsWith("[\"step") && textFieldExpression.toArray().length % 2 === 0) {
//        // got an invalid step expression from core, we need to add an additional name_x into step
//        text =
//          text.replace(STEP_REGEX.toRegex(), STEP_TEMPLATE)
//      }
      layer.textField(text)
    }
  }

  private fun getChineseMapLocale(mapLocale: MapLocale, isStreetsV7: Boolean): MapLocale {
    return if (isStreetsV7) {
      // streets v7 supports name_zh(MapLocale.CHINA) and name_zh-Hans(MapLocale.CHINESE_HANS)
      // https://docs.mapbox.com/vector-tiles/reference/mapbox-streets-v7/#name-fields
      if (mapLocale == MapLocale.CHINESE_HANS) {
        mapLocale
      } else {
        MapLocale.CHINA
      }
    } else {
      // streets V6 only supports name_zh(MapLocale.CHINA)
      // https://docs.mapbox.com/vector-tiles/reference/mapbox-streets-v6/#name-fields
      MapLocale.CHINA
    }
  }

  private fun convertExpressionV8(
    mapLocale: MapLocale, layer: SymbolLayer,
    textFieldExpression: Expression?
  ) {
    textFieldExpression?.let {
      var stringExpression: String = textFieldExpression.toJson().replace(
        EXPRESSION_V8_REGEX_LOCALIZED,
        EXPRESSION_V8_TEMPLATE_BASE
      )
      var mapLanguage = mapLocale.mapLanguage
      if (mapLanguage != Languages.ENGLISH) {
        if (mapLanguage == Languages.CHINESE) {
          mapLanguage = Languages.SIMPLIFIED_CHINESE
        }
        stringExpression = stringExpression.replace(
          EXPRESSION_V8_TEMPLATE_BASE, String.format(
            Locale.US,
            EXPRESSION_V8_TEMPLATE_LOCALIZED,
            mapLanguage.value,
            mapLanguage.value
          )
        )
      }
      layer.textField(Expression.fromRaw(stringExpression))
    }
  }

  private fun sourceIsFromMapbox(style: StyleInterface, source: StyleObjectInfo): Boolean {
    for (supportedSource in SUPPORTED_SOURCES) {
      if (sourceIsType(style, source, supportedSource)) {
        return true
      }
    }
    Logger.w(
      TAG,
      "The source ${source.id} is not based on Mapbox Vector Tiles. Supported sources:\n $SUPPORTED_SOURCES"
    )
    return false
  }

  private fun sourceIsStreetsV7(style: StyleInterface, source: StyleObjectInfo): Boolean =
    sourceIsType(style, source, STREET_V7)

  private fun sourceIsStreetsV8(style: StyleInterface, source: StyleObjectInfo): Boolean =
    sourceIsType(style, source, STREET_V8)

  private fun sourceIsType(style: StyleInterface, source: StyleObjectInfo, type: String): Boolean {
    if (source.type == SOURCE_TYPE_VECTOR) {
      style.getSourceAs<VectorSource>(source.id)?.url?.let{
        return it.contains(type)
      }
    }
    return false
  }

  companion object {
    private const val TAG = "LocalizationPluginImpl"
    private const val SOURCE_TYPE_VECTOR = "vector"
    private const val LAYER_TYPE_SYMBOL = "symbol"
    private const val STREET_V6 = "mapbox.mapbox-streets-v6"
    private const val STREET_V7 = "mapbox.mapbox-streets-v7"
    private const val STREET_V8 = "mapbox.mapbox-streets-v8"
    private val SUPPORTED_SOURCES = listOf(STREET_V6, STREET_V7, STREET_V8)
    private const val EXPRESSION_REGEX = "\\b(name|name_.{2,7})\\b"

    private val EXPRESSION_V8_TEMPLATE_BASE = "${get("name_en").toJson()},${get("name").toJson()}"
    private val EXPRESSION_V8_REGEX_LOCALIZED = Regex(
      "\\[\"match\",\"(name|name_.{2,7})\","
        + "\"name_zh-Hant\",\\[\"coalesce\","
        + "\\[\"get\",\"name_zh-Hant\"],"
        + "\\[\"get\",\"name_zh-Hans\"],"
        + "\\[\"match\",\\[\"get\",\"name_script\"],\"Latin\",\\[\"get\",\"name\"],\\[\"get\",\"name_en\"]],"
        + "\\[\"get\",\"name\"]],"
        + "\\[\"coalesce\","
        + "\\[\"get\",\"(name|name_.{2,7})\"],"
        + "\\[\"match\",\\[\"get\",\"name_script\"],\"Latin\",\\[\"get\",\"name\"],\\[\"get\",\"name_en\"]],"
        + "\\[\"get\",\"name\"]]"
        + "]"
    )
    private const val EXPRESSION_V8_TEMPLATE_LOCALIZED = ("[\"match\",\"%s\","
      + "\"name_zh-Hant\",[\"coalesce\","
      + "[\"get\",\"name_zh-Hant\"],"
      + "[\"get\",\"name_zh-Hans\"],"
      + "[\"match\",[\"get\",\"name_script\"],\"Latin\",[\"get\",\"name\"],[\"get\",\"name_en\"]],"
      + "[\"get\",\"name\"]],"
      + "[\"coalesce\","
      + "[\"get\",\"%s\"],"
      + "[\"match\",[\"get\",\"name_script\"],\"Latin\",[\"get\",\"name\"],[\"get\",\"name_en\"]],"
      + "[\"get\",\"name\"]]"
      + "]")

    // faulty step expression workaround
    private const val STEP_REGEX = "\\[\"zoom\"],"
    private const val STEP_TEMPLATE = "[\"zoom\"],\"\","
  }
}

/**
 * Extension function for MapView to get the localization plugin instance.
 *
 * @return Localization plugin instance
 */
@MapboxExperimental
fun MapPluginProviderDelegate.localization(): LocalizationPlugin {
  return this.getPlugin(LocalizationPluginImpl::class.java)!!
}