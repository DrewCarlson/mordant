package com.github.ajalt.mordant.rendering

import com.github.ajalt.mordant.rendering.TextColors.red
import com.github.ajalt.mordant.test.RenderingTest
import com.github.ajalt.mordant.widgets.Text
import kotlin.js.JsName
import kotlin.test.Test

class WidgetBuilderTest : RenderingTest() {
    @Test
    @JsName("widget_builder")
    fun `widget builder`() = checkRender(
        buildWidget {
            appendln("1")
            appendln()
            appendAll(listOf(Text("2"), Text("3")))
            appendln(red("4"))
        }, "1\n\n2\n3\n${red("4")}"
    )
}
