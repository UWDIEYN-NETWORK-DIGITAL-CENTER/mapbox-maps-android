// This file is generated.

package com.mapbox.maps.extension.compose.style.sources.generated

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
import com.mapbox.maps.extension.compose.style.BooleanValue
import com.mapbox.maps.extension.compose.style.DoubleListValue
import com.mapbox.maps.extension.compose.style.DoubleValue
import com.mapbox.maps.extension.compose.style.IdGenerator.generateRandomSourceId
import com.mapbox.maps.extension.compose.style.LongValue
import com.mapbox.maps.extension.compose.style.StringListValue
import com.mapbox.maps.extension.compose.style.StringValue
import com.mapbox.maps.extension.compose.style.sources.SourceState
import com.mapbox.maps.extension.compose.style.sources.TileCacheBudget
import java.util.Objects

/**
 * Create and [rememberSaveable] a [RasterSourceState] using [RasterSourceState.Saver].
 * [init] will be called when the [RasterSourceState] is first created to configure its
 * initial state.
 *
 * @param key An optional key to be used as a key for the saved value. If not provided we use the
 * automatically generated by the Compose runtime which is unique for the every exact code location
 * in the composition tree.
 * @param sourceId The optional sourceId for the source state, by default, a random source ID will be used.
 * @param init A function initialise this [RasterSourceState].
 */
@Composable
public inline fun rememberRasterSourceState(
  key: String? = null,
  sourceId: String = remember {
    generateRandomSourceId("raster")
  },
  crossinline init: RasterSourceState.() -> Unit = {}
): RasterSourceState = rememberSaveable(key = key, saver = RasterSourceState.Saver) {
  RasterSourceState(sourceId).apply(init)
}

/**
 * A raster tile source.
 *
 * @see [The online documentation](https://docs.mapbox.com/style-spec/reference/sources#raster)
 *
 * @param sourceId The id of the source state, by default a random generated ID will be used.
 * @param initialProperties The initial mutable properties of the source.
 */
@Stable
public class RasterSourceState private constructor(
  sourceId: String,
  sourceType: String,
  initialProperties: Map<String, Pair<Boolean, Value>>,
  url: StringValue,
  tiles: StringListValue,
  bounds: DoubleListValue,
  minZoom: LongValue,
  maxZoom: LongValue,
  tileSize: LongValue,
  scheme: SchemeValue,
  attribution: StringValue,
  volatile: BooleanValue,
  prefetchZoomDelta: LongValue,
  tileCacheBudget: TileCacheBudget,
  minimumTileUpdateInterval: DoubleValue,
  maxOverscaleFactorForParentTiles: LongValue,
  tileRequestsDelay: DoubleValue,
  tileNetworkRequestsDelay: DoubleValue,
) : SourceState(
  sourceId = sourceId,
  sourceType = sourceType,
  initialProperties = initialProperties,
) {
  public constructor(
    sourceId: String = generateRandomSourceId("raster"),
  ) : this(
    sourceId = sourceId,
    sourceType = "raster",
    initialProperties = emptyMap(),
    url = StringValue.INITIAL,
    tiles = StringListValue.INITIAL,
    bounds = DoubleListValue.INITIAL,
    minZoom = LongValue.INITIAL,
    maxZoom = LongValue.INITIAL,
    tileSize = LongValue.INITIAL,
    scheme = SchemeValue.INITIAL,
    attribution = StringValue.INITIAL,
    volatile = BooleanValue.INITIAL,
    prefetchZoomDelta = LongValue.INITIAL,
    tileCacheBudget = TileCacheBudget.INITIAL,
    minimumTileUpdateInterval = DoubleValue.INITIAL,
    maxOverscaleFactorForParentTiles = LongValue.INITIAL,
    tileRequestsDelay = DoubleValue.INITIAL,
    tileNetworkRequestsDelay = DoubleValue.INITIAL,
  )

  private val urlState: MutableState<StringValue> = mutableStateOf(url)

  /**
   * A URL to a TileJSON resource. Supported protocols are `http:`, `https:`, and `mapbox://<Tileset ID>`. Required if
   * `tiles` is not provided.
   */
  public var url: StringValue by urlState

  @Composable
  private fun UpdateUrl() {
    urlState.value.apply {
      if (notInitial) {
        setBuilderProperty("url", value)
      }
    }
  }
  private val tilesState: MutableState<StringListValue> = mutableStateOf(tiles)

  /**
   * An array of one or more tile source URLs, as in the TileJSON spec. Required if
   * `url` is not provided.
   */
  public var tiles: StringListValue by tilesState

  @Composable
  private fun UpdateTiles() {
    tilesState.value.apply {
      if (notInitial) {
        setBuilderProperty("tiles", value)
      }
    }
  }
  private val boundsState: MutableState<DoubleListValue> = mutableStateOf(bounds)

  /**
   * An array containing the longitude and latitude of the southwest and northeast corners of the source's
   * bounding box in the following order: `[sw.lng, sw.lat, ne.lng, ne.lat]`. When this property is included in
   * a source, no tiles outside of the given bounds are requested by Mapbox GL.
   * Default value: [-180,-85.051129,180,85.051129].
   */
  public var bounds: DoubleListValue by boundsState

  @Composable
  private fun UpdateBounds() {
    boundsState.value.apply {
      if (notInitial) {
        setBuilderProperty("bounds", value)
      }
    }
  }
  private val minZoomState: MutableState<LongValue> = mutableStateOf(minZoom)

  /**
   * Minimum zoom level for which tiles are available, as in the TileJSON spec.
   * Default value: 0.
   */
  public var minZoom: LongValue by minZoomState

  @Composable
  private fun UpdateMinZoom() {
    minZoomState.value.apply {
      if (notInitial) {
        setBuilderProperty("minzoom", value)
      }
    }
  }
  private val maxZoomState: MutableState<LongValue> = mutableStateOf(maxZoom)

  /**
   * Maximum zoom level for which tiles are available, as in the TileJSON spec. Data from tiles
   * at the maxzoom are used when displaying the map at higher zoom levels.
   * Default value: 22.
   */
  public var maxZoom: LongValue by maxZoomState

  @Composable
  private fun UpdateMaxZoom() {
    maxZoomState.value.apply {
      if (notInitial) {
        setBuilderProperty("maxzoom", value)
      }
    }
  }
  private val tileSizeState: MutableState<LongValue> = mutableStateOf(tileSize)

  /**
   * The minimum visual size to display tiles for this layer. Only configurable for raster layers.
   * Default value: 512. The unit of tileSize is in pixels.
   */
  public var tileSize: LongValue by tileSizeState

  @Composable
  private fun UpdateTileSize() {
    tileSizeState.value.apply {
      if (notInitial) {
        setBuilderProperty("tileSize", value)
      }
    }
  }
  private val schemeState: MutableState<SchemeValue> = mutableStateOf(scheme)

  /**
   * Influences the y direction of the tile coordinates. The global-mercator (aka Spherical Mercator) profile is assumed.
   * Default value: "xyz".
   */
  public var scheme: SchemeValue by schemeState

  @Composable
  private fun UpdateScheme() {
    schemeState.value.apply {
      if (notInitial) {
        setBuilderProperty("scheme", value)
      }
    }
  }
  private val attributionState: MutableState<StringValue> = mutableStateOf(attribution)

  /**
   * Contains an attribution to be displayed when the map is shown to a user.
   */
  public var attribution: StringValue by attributionState

  @Composable
  private fun UpdateAttribution() {
    attributionState.value.apply {
      if (notInitial) {
        setBuilderProperty("attribution", value)
      }
    }
  }
  private val volatileState: MutableState<BooleanValue> = mutableStateOf(volatile)

  /**
   * A setting to determine whether a source's tiles are cached locally.
   * Default value: false.
   */
  public var volatile: BooleanValue by volatileState

  @Composable
  private fun UpdateVolatile() {
    volatileState.value.apply {
      if (notInitial) {
        setBuilderProperty("volatile", value)
      }
    }
  }
  private val prefetchZoomDeltaState: MutableState<LongValue> = mutableStateOf(prefetchZoomDelta)

  /**
   * When loading a map, if PrefetchZoomDelta is set to any number greater than 0, the map
   * will first request a tile at zoom level lower than zoom - delta, but so that
   * the zoom level is multiple of delta, in an attempt to display a full map at
   * lower resolution as quick as possible. It will get clamped at the tile source minimum zoom.
   * Default value: 4.
   */
  public var prefetchZoomDelta: LongValue by prefetchZoomDeltaState

  @Composable
  private fun UpdatePrefetchZoomDelta() {
    prefetchZoomDeltaState.value.apply {
      if (notInitial) {
        setProperty("prefetch-zoom-delta", value)
      }
    }
  }
  private val tileCacheBudgetState: MutableState<TileCacheBudget> = mutableStateOf(tileCacheBudget)

  /**
   * This property defines a source-specific resource budget, either in tile units or in megabytes. Whenever the
   * tile cache goes over the defined limit, the least recently used tile will be evicted from
   * the in-memory cache. Note that the current implementation does not take into account resources allocated by
   * the visible tiles.
   */
  public var tileCacheBudget: TileCacheBudget by tileCacheBudgetState

  @Composable
  private fun UpdateTileCacheBudget() {
    tileCacheBudgetState.value.apply {
      if (notInitial) {
        setProperty("tile-cache-budget", value)
      }
    }
  }
  private val minimumTileUpdateIntervalState: MutableState<DoubleValue> = mutableStateOf(minimumTileUpdateInterval)

  /**
   * Minimum tile update interval in seconds, which is used to throttle the tile update network requests.
   * If the given source supports loading tiles from a server, sets the minimum tile update interval.
   * Update network requests that are more frequent than the minimum tile update interval are suppressed.
   * Default value: 0.
   */
  public var minimumTileUpdateInterval: DoubleValue by minimumTileUpdateIntervalState

  @Composable
  private fun UpdateMinimumTileUpdateInterval() {
    minimumTileUpdateIntervalState.value.apply {
      if (notInitial) {
        setProperty("minimum-tile-update-interval", value)
      }
    }
  }
  private val maxOverscaleFactorForParentTilesState: MutableState<LongValue> = mutableStateOf(maxOverscaleFactorForParentTiles)

  /**
   * When a set of tiles for a current zoom level is being rendered and some of
   * the ideal tiles that cover the screen are not yet loaded, parent tile could be used
   * instead. This might introduce unwanted rendering side-effects, especially for raster tiles that are overscaled multiple times.
   * This property sets the maximum limit for how much a parent tile can be overscaled.
   */
  public var maxOverscaleFactorForParentTiles: LongValue by maxOverscaleFactorForParentTilesState

  @Composable
  private fun UpdateMaxOverscaleFactorForParentTiles() {
    maxOverscaleFactorForParentTilesState.value.apply {
      if (notInitial) {
        setProperty("max-overscale-factor-for-parent-tiles", value)
      }
    }
  }
  private val tileRequestsDelayState: MutableState<DoubleValue> = mutableStateOf(tileRequestsDelay)

  /**
   * For the tiled sources, this property sets the tile requests delay. The given delay comes in
   * action only during an ongoing animation or gestures. It helps to avoid loading, parsing and rendering
   * of the transient tiles and thus to improve the rendering performance, especially on low-end devices.
   * Default value: 0.
   */
  public var tileRequestsDelay: DoubleValue by tileRequestsDelayState

  @Composable
  private fun UpdateTileRequestsDelay() {
    tileRequestsDelayState.value.apply {
      if (notInitial) {
        setProperty("tile-requests-delay", value)
      }
    }
  }
  private val tileNetworkRequestsDelayState: MutableState<DoubleValue> = mutableStateOf(tileNetworkRequestsDelay)

  /**
   * For the tiled sources, this property sets the tile network requests delay. The given delay comes
   * in action only during an ongoing animation or gestures. It helps to avoid loading the transient
   * tiles from the network and thus to avoid redundant network requests. Note that tile-network-requests-delay value is
   * superseded with tile-requests-delay property value, if both are provided.
   * Default value: 0.
   */
  public var tileNetworkRequestsDelay: DoubleValue by tileNetworkRequestsDelayState

  @Composable
  private fun UpdateTileNetworkRequestsDelay() {
    tileNetworkRequestsDelayState.value.apply {
      if (notInitial) {
        setProperty("tile-network-requests-delay", value)
      }
    }
  }

  @Composable
  override fun UpdateProperties() {
    UpdateUrl()
    UpdateTiles()
    UpdateBounds()
    UpdateMinZoom()
    UpdateMaxZoom()
    UpdateTileSize()
    UpdateScheme()
    UpdateAttribution()
    UpdateVolatile()
    UpdatePrefetchZoomDelta()
    UpdateTileCacheBudget()
    UpdateMinimumTileUpdateInterval()
    UpdateMaxOverscaleFactorForParentTiles()
    UpdateTileRequestsDelay()
    UpdateTileNetworkRequestsDelay()
  }

  private fun getProperties(): Map<String, Value> =
    listOfNotNull(
      ("url" to url.value).takeIf { url.notInitial },
      ("tiles" to tiles.value).takeIf { tiles.notInitial },
      ("bounds" to bounds.value).takeIf { bounds.notInitial },
      ("minzoom" to minZoom.value).takeIf { minZoom.notInitial },
      ("maxzoom" to maxZoom.value).takeIf { maxZoom.notInitial },
      ("tileSize" to tileSize.value).takeIf { tileSize.notInitial },
      ("scheme" to scheme.value).takeIf { scheme.notInitial },
      ("attribution" to attribution.value).takeIf { attribution.notInitial },
      ("volatile" to volatile.value).takeIf { volatile.notInitial },
      ("prefetch-zoom-delta" to prefetchZoomDelta.value).takeIf { prefetchZoomDelta.notInitial },
      ("tile-cache-budget" to tileCacheBudget.value).takeIf { tileCacheBudget.notInitial },
      ("minimum-tile-update-interval" to minimumTileUpdateInterval.value).takeIf { minimumTileUpdateInterval.notInitial },
      ("max-overscale-factor-for-parent-tiles" to maxOverscaleFactorForParentTiles.value).takeIf { maxOverscaleFactorForParentTiles.notInitial },
      ("tile-requests-delay" to tileRequestsDelay.value).takeIf { tileRequestsDelay.notInitial },
      ("tile-network-requests-delay" to tileNetworkRequestsDelay.value).takeIf { tileNetworkRequestsDelay.notInitial },
    ).toMap()

  /**
   * See [Any.equals]
   */
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as RasterSourceState

    if (sourceId != other.sourceId) return false
    if (url != other.url) return false
    if (tiles != other.tiles) return false
    if (bounds != other.bounds) return false
    if (minZoom != other.minZoom) return false
    if (maxZoom != other.maxZoom) return false
    if (tileSize != other.tileSize) return false
    if (scheme != other.scheme) return false
    if (attribution != other.attribution) return false
    if (volatile != other.volatile) return false
    if (prefetchZoomDelta != other.prefetchZoomDelta) return false
    if (tileCacheBudget != other.tileCacheBudget) return false
    if (minimumTileUpdateInterval != other.minimumTileUpdateInterval) return false
    if (maxOverscaleFactorForParentTiles != other.maxOverscaleFactorForParentTiles) return false
    if (tileRequestsDelay != other.tileRequestsDelay) return false
    if (tileNetworkRequestsDelay != other.tileNetworkRequestsDelay) return false

    return true
  }

  /**
   * See [Any.hashCode]
   */
  override fun hashCode(): Int {
    return Objects.hash(
      sourceId,
      url,
      tiles,
      bounds,
      minZoom,
      maxZoom,
      tileSize,
      scheme,
      attribution,
      volatile,
      prefetchZoomDelta,
      tileCacheBudget,
      minimumTileUpdateInterval,
      maxOverscaleFactorForParentTiles,
      tileRequestsDelay,
      tileNetworkRequestsDelay,
    )
  }

  /**
   * Returns a string representation of the object.
   */
  override fun toString(): String =
    "RasterSourceState(sourceId=$sourceId, url=$url, tiles=$tiles, bounds=$bounds, minZoom=$minZoom, maxZoom=$maxZoom, tileSize=$tileSize, scheme=$scheme, attribution=$attribution, volatile=$volatile, prefetchZoomDelta=$prefetchZoomDelta, tileCacheBudget=$tileCacheBudget, minimumTileUpdateInterval=$minimumTileUpdateInterval, maxOverscaleFactorForParentTiles=$maxOverscaleFactorForParentTiles, tileRequestsDelay=$tileRequestsDelay, tileNetworkRequestsDelay=$tileNetworkRequestsDelay)"

  /**
   * Public companion object.
   */
  public companion object {
    /**
     * The default saver implementation for [RasterSourceState]
     */
    public val Saver: Saver<RasterSourceState, Holder> = Saver(
      save = { it.save() },
      restore = { holder ->
        RasterSourceState(
          sourceId = holder.sourcedId,
          sourceType = "raster",
          initialProperties = holder.savedProperties,
          url = holder.savedProperties["url"]?.let { StringValue(it.second) } ?: StringValue.INITIAL,
          tiles = holder.savedProperties["tiles"]?.let { StringListValue(it.second) } ?: StringListValue.INITIAL,
          bounds = holder.savedProperties["bounds"]?.let { DoubleListValue(it.second) } ?: DoubleListValue.INITIAL,
          minZoom = holder.savedProperties["minzoom"]?.let { LongValue(it.second) } ?: LongValue.INITIAL,
          maxZoom = holder.savedProperties["maxzoom"]?.let { LongValue(it.second) } ?: LongValue.INITIAL,
          tileSize = holder.savedProperties["tileSize"]?.let { LongValue(it.second) } ?: LongValue.INITIAL,
          scheme = holder.savedProperties["scheme"]?.let { SchemeValue(it.second) } ?: SchemeValue.INITIAL,
          attribution = holder.savedProperties["attribution"]?.let { StringValue(it.second) } ?: StringValue.INITIAL,
          volatile = holder.savedProperties["volatile"]?.let { BooleanValue(it.second) } ?: BooleanValue.INITIAL,
          prefetchZoomDelta = holder.savedProperties["prefetch-zoom-delta"]?.let { LongValue(it.second) } ?: LongValue.INITIAL,
          tileCacheBudget = holder.savedProperties["tile-cache-budget"]?.let { TileCacheBudget(it.second) } ?: TileCacheBudget.INITIAL,
          minimumTileUpdateInterval = holder.savedProperties["minimum-tile-update-interval"]?.let { DoubleValue(it.second) } ?: DoubleValue.INITIAL,
          maxOverscaleFactorForParentTiles = holder.savedProperties["max-overscale-factor-for-parent-tiles"]?.let { LongValue(it.second) } ?: LongValue.INITIAL,
          tileRequestsDelay = holder.savedProperties["tile-requests-delay"]?.let { DoubleValue(it.second) } ?: DoubleValue.INITIAL,
          tileNetworkRequestsDelay = holder.savedProperties["tile-network-requests-delay"]?.let { DoubleValue(it.second) } ?: DoubleValue.INITIAL,
        )
      }
    )
  }
}
// End of generated file.