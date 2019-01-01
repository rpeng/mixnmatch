package com.penguins.components;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.BasicComponent;
import org.newdawn.slick.gui.GUIContext;

public abstract class MouseOverComponent extends BasicComponent {
    private boolean isMouseOver;

    public MouseOverComponent(GUIContext container) {
        super(container);
    }

    public abstract Shape getShapeBounds();

    @Override
    public void mousePressed(int button, int x, int y) {
        if (getShapeBounds().contains(x, y)) {
            notifyListeners();
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        isMouseOver = getShapeBounds().contains(newx, newy);
    }

    public boolean isMouseOver() {
        return isMouseOver;
    }
}