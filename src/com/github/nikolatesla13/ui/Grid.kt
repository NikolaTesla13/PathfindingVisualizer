package com.github.nikolatesla13.ui

import java.awt.*
import javax.swing.BorderFactory
import javax.swing.JPanel

class Grid : JPanel() {

    private val squareSize = 20
    private var state: Array<IntArray> = Array(720/squareSize+1) { IntArray(720/squareSize+1) { 0 } }
    private var isClicking = false

    init {
        border = BorderFactory.createLineBorder(Color.black)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        g.color = Color.black

        for(i in 0..size.width step squareSize) {
            for(j in 0..size.height step squareSize) {
                val rectangle = Rectangle(i, j, squareSize, squareSize)

                val mouseOnScreen =  MouseInfo.getPointerInfo().location
                val mousePos = Point(mouseOnScreen.x -  locationOnScreen.x, mouseOnScreen.y -  locationOnScreen.y)

                if(isPointInsideRect(mousePos, rectangle)) {
                    state[i / squareSize][j / squareSize] = 1
                }
                if(state[i/squareSize][j/squareSize] == 1) {
                    g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                } else {
                    state[i/squareSize][j/squareSize] = 0
                    g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height)
                }
            }
        }
    }

    override fun getPreferredSize(): Dimension {
        return size
    }

    private fun isPointInsideRect(p: Point, rectangle: Rectangle): Boolean {
        return (p.x > rectangle.x && p.y > rectangle.y) && (p.x < rectangle.x+rectangle.width && p.y < rectangle.y + rectangle.height)
    }

}