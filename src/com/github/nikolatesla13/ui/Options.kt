package com.github.nikolatesla13.ui

import java.awt.*
import javax.swing.*
import javax.swing.border.EmptyBorder


class Options : JPanel() {

    private var speed = 20
    private var checkDiagonals = false
    private var checkWalls = true

    init {
        border = BorderFactory.createLineBorder(Color.BLACK)

        val header = JLabel("Pathfinding Configuration")
        header.font =  Font("Calibri", Font.BOLD, 17)
        add(header)

        val controlPanel = JPanel()
        controlPanel.layout = GridLayout(0, 2)

        val firstColumn = JPanel()
        firstColumn.layout = GridLayout(2, 0)
        firstColumn.preferredSize = Dimension(200, 135)
        firstColumn.border = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK), EmptyBorder(0, 10, 10, 10))

        firstColumn.add(createColumn(JLabel("choose the points"), createRow(JButton("start"), JButton("finish"))))

        val sliderLabel = JLabel("Speed: $speed", JLabel.LEFT)
        sliderLabel.alignmentX = LEFT_ALIGNMENT

        val speedSlider = JSlider()
        speedSlider.minimum = 0
        speedSlider.maximum = 100
        speedSlider.majorTickSpacing = 20
        speedSlider.labelTable = speedSlider.createStandardLabels(20)
        speedSlider.paintTicks = true
        speedSlider.paintLabels = true
        speedSlider.value = speed
        speedSlider.addChangeListener {
            speed = speedSlider.value
            sliderLabel.text = "Speed: $speed"
        }
        speedSlider.preferredSize = Dimension(190, 45)

        firstColumn.add(createColumn(sliderLabel, speedSlider))

        controlPanel.add(firstColumn)


        val secondColumn = JPanel()
        secondColumn.layout = GridLayout(3, 0)
        secondColumn.preferredSize = Dimension(200, 125)
        secondColumn.border = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK), EmptyBorder(0, 10, 10, 10))

        val diagonalCheckbox = JCheckBox("diagonal")
        diagonalCheckbox.isSelected = checkDiagonals

        val wallsCheckbox = JCheckBox("walls")
        wallsCheckbox.isSelected = checkWalls

        secondColumn.add(createRow(diagonalCheckbox, wallsCheckbox))
        secondColumn.add(createRow(JLabel("steps: 0"), JLabel("open: 0")))

        val wallsButton = JButton("add walls")
        wallsButton.isEnabled = checkWalls
        wallsCheckbox.addChangeListener {
            checkWalls = wallsCheckbox.isSelected
            wallsButton.isEnabled = checkWalls
        }

        secondColumn.add(createRow(wallsButton, JButton("RUN")))

        controlPanel.add(secondColumn)

        add(controlPanel)
    }

    private fun createRow(c1: Component, c2: Component) : JPanel {
        val rowPanel = JPanel()
        val borderLayout = BorderLayout()
        rowPanel.layout = borderLayout
        rowPanel.border = BorderFactory.createEmptyBorder(10, 0, 10, 0)
        rowPanel.preferredSize = Dimension(180, 50)

        rowPanel.add(c1, BorderLayout.WEST)
        rowPanel.add(c2, BorderLayout.EAST)

        return rowPanel
    }

    private fun createColumn(c1: Component, c2: Component) : JPanel {
        val columnPanel = JPanel()
        val borderLayout = BorderLayout()
        columnPanel.layout = borderLayout

        columnPanel.add(c1, BorderLayout.NORTH)
        columnPanel.add(c2, BorderLayout.SOUTH)

        return columnPanel
    }

}