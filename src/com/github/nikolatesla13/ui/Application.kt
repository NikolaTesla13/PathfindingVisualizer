package com.github.nikolatesla13.ui

import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

class Application : JFrame() {

    private lateinit var grid: Grid
    private lateinit var options: Options

    init {
        addMouseMotionListener(object : MouseAdapter() {
            override fun mouseMoved(e: MouseEvent) {
                repaint()
            }
        })

        createBoard()
    }

    private fun createBoard() {
        title = "Pathfinding Visualizer"
        defaultCloseOperation = EXIT_ON_CLOSE
        preferredSize = Dimension(720, 720)
        isResizable = false
        isVisible = true
        jMenuBar = createMenuBar()

        val layeredPane = JLayeredPane()
        layeredPane.preferredSize = preferredSize

        options = Options()
        options.bounds = Rectangle(0, preferredSize.height-220, 400, 200)
        layeredPane.add(options)

        grid = Grid()
        grid.bounds = Rectangle(0, 0, preferredSize.width, preferredSize.height)
        layeredPane.add(grid)

        add(layeredPane, BorderLayout.CENTER)

        pack()
    }

    private fun createMenuBar(): JMenuBar {
        val menuBar = JMenuBar()

        val fileMenu = JMenu("File")
        fileMenu.add(JMenuItem("New"))
        fileMenu.add(JMenuItem("Open"))
        fileMenu.add(JMenuItem("Save"))
        fileMenu.add(JMenuItem("Save As"))
        fileMenu.add(JMenuItem("Exit"))

        val helpMenu = JMenu("Help")
        helpMenu.add(JMenuItem("How to use"))
        helpMenu.add(JMenuItem("About"))

        menuBar.add(fileMenu)
        menuBar.add(helpMenu)

        return menuBar
    }

}