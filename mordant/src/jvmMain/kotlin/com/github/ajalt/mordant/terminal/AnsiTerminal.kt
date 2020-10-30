package com.github.ajalt.mordant.terminal

import com.github.ajalt.mordant.AnsiLevel
import com.github.ajalt.mordant.Terminal
import com.github.ajalt.mordant.TerminalColors
import com.github.ajalt.mordant.rendering.Renderable
import com.github.ajalt.mordant.rendering.Theme
import com.github.ajalt.mordant.rendering.internal.renderLinesAnsi

internal class AnsiTerminal(
        ansiLevel: AnsiLevel?,
        override val theme: Theme,
        width: Int?,
        height: Int?,
        hyperlinks: Boolean?,
        override val tabWidth: Int,
) : Terminal {
    init {
        require(tabWidth >= 0) { "tab width cannot be negative" }
    }

    override val info: TerminalInfo = TerminalDetection.detectTerminal(ansiLevel, width, height, hyperlinks)
    override val colors: TerminalColors = TerminalColors(info.ansiLevel)
    override val cursor: TerminalCursor = if (info.interactive) PrintTerminalCursor(this) else DisabledTerminalCursor

    override fun render(renderable: Renderable): String {
        return renderLinesAnsi(renderable.render(this), info.ansiLevel, info.ansiHyperLinks)
    }

    override fun println() {
        kotlin.io.println()
    }

    override fun rawPrintln(message: String) {
        kotlin.io.println(message)
    }

    override fun rawPrint(message: String) {
        kotlin.io.print(message)
    }
}
