package com.penguins.components;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

public class MenuButtonComponent extends MouseOverComponent {
    private final String title;
    private final Font menuFont;

    public MenuButtonComponent(GUIContext container, String title, Font menuFont) {
        super(container);
        this.title = title;
        this.menuFont = menuFont;
        this.width = menuFont.getWidth(title);
        this.height = menuFont.getHeight(title);
    }

    @Override
    public Rectangle getShapeBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }

    @Override
    public void renderImpl(GUIContext container, Graphics g) {
        g.setFont(menuFont);
        g.drawString(title, getX(), getY());
    }
}
