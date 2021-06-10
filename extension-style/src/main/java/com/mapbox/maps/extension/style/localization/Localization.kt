package com.mapbox.maps.extension.style.localization

import com.mapbox.common.Logger
import com.mapbox.maps.StyleObjectInfo
import com.mapbox.maps.extension.style.StyleInterface
import com.mapbox.maps.extension.style.expressions.dsl.generated.get
import com.mapbox.maps.extension.style.expressions.generated.Expression
import com.mapbox.maps.extension.style.layers.generated.SymbolLayer
import com.mapbox.maps.extension.style.layers.getLayerAs
import com.mapbox.maps.extension.style.sources.generated.VectorSource
import com.mapbox.maps.extension.style.sources.getSourceAs
import com.mapbox.maps.toJson
import java.util.*

/**
 * Apply languages to style by locale. It will replace the `["get","name_en"]` expression with a new expression `["get","name_xx"]`,
 * where name_xx is the supported local name related with locale.
 * For example if locale is [Locale.GERMAN], the original expression
 * `["format",["coalesce",["get","name_en"],["get","name"]],{}]` will be replaced by
 * `["format",["coalesce",["get","name_zh-Hans"],["get","name"]],{}]`
 */
internal fun setMapLanguage(locale: Locale, style: StyleInterface) {
  style.styleSources
    .filter { sourceIsFromMapbox(style, it) }
    .forEach { source ->
      style.styleLayers
        .filter { it.type == LAYER_TYPE_SYMBOL }
        .forEach { layer ->
          val symbolLayer = style.getLayerAs<SymbolLayer>(layer.id)
          symbolLayer.textFieldAsExpression?.let { textFieldExpression ->
            val language = if (sourceIsStreetsV8(style, source)) getLanguageNameV8(locale)
            else {
              if (sourceIsStreetsV7(style, source)) getLanguageNameV7(locale)
              else getLanguageNameV6(locale)
            }
            convertExpression(language, symbolLayer, textFieldExpression)
          }
        }
    }
}

private fun convertExpression(language: String, layer: SymbolLayer, textField: Expression?) {
  textField?.let {
    val stringExpression: String = it.toJson().replace(
      EXPRESSION_REGEX,
      get(language).toJson()
    )

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
    style.getSourceAs<VectorSource>(source.id)?.url?.let {
      return it.contains(type)
    }
  }
  return false
}

private const val TAG = "LocalizationPluginImpl"
private const val SOURCE_TYPE_VECTOR = "vector"
private const val LAYER_TYPE_SYMBOL = "symbol"
private const val STREET_V6 = "mapbox.mapbox-streets-v6"
private const val STREET_V7 = "mapbox.mapbox-streets-v7"
private const val STREET_V8 = "mapbox.mapbox-streets-v8"
private val SUPPORTED_SOURCES = listOf(STREET_V6, STREET_V7, STREET_V8)
private val EXPRESSION_REGEX = Regex("\\[\"get\",\\s*\"(name|name_.{2,7})\"\\]")