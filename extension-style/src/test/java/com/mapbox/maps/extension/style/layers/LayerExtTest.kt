package com.mapbox.maps.extension.style.layers

import com.mapbox.bindgen.Expected
import com.mapbox.bindgen.None
import com.mapbox.bindgen.Value
import com.mapbox.maps.StyleManager
import com.mapbox.maps.StylePropertyValue
import com.mapbox.maps.StylePropertyValueKind
import com.mapbox.maps.extension.style.ShadowStyleManager
import com.mapbox.maps.extension.style.StyleInterface
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import io.mockk.*
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.RuntimeException

@RunWith(RobolectricTestRunner::class)
@Config(shadows = [ShadowStyleManager::class])
class LayerExtTest {
  private val style = mockk<StyleInterface>(relaxUnitFun = true, relaxed = true)
  private val expected = mockk<Expected<String, None>>(relaxUnitFun = true, relaxed = true)
  private val booleanExpected = mockk<Expected<String, Boolean>>(relaxUnitFun = true, relaxed = true)
  private val styleProperty = mockk<StylePropertyValue>()
  private val booleanSlot = slot<Boolean>()

  @Before
  fun prepareTest() {
    every { style.isStyleLayerPersistent(any()) } returns booleanExpected
    every { style.addStyleLayer(any(), any()) } returns expected
    every { style.setStyleLayerProperties(any(), any()) } returns expected
    every { expected.error } returns null

    // For default property getters
    mockkStatic(StyleManager::class)
  }

  @After
  fun cleanup() {
    unmockkAll()
  }

  @Test
  fun testPersistentSet(){
    val layer = symbolLayer("id", "source") {}
    layer.bindTo(style)
    layer.persistent(true)
    verify { style.setStyleLayerPersistent("id", capture(booleanSlot)) }
    Assert.assertEquals(true, booleanSlot.captured)
  }

  @Test(expected = RuntimeException::class)
  fun testPersistentSetBeforeBind(){
    val layer = symbolLayer("id", "source") {}
    layer.persistent(true)
    layer.bindTo(style)
  }

  @Test
  fun testPersistentGet(){
    every { style.isStyleLayerPersistent(any()) } returns booleanExpected
    every { booleanExpected.value } returns true
    val layer = symbolLayer("id", "source") {}
    layer.bindTo(style)
    assertTrue(layer.persistent!!)
    verify { style.isStyleLayerPersistent("id") }
  }
}