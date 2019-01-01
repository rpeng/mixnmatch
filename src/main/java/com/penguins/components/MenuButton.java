package com.penguins.components;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.BasicComponent;
import org.newdawn.slick.gui.GUIContext;

public class MenuButton extends BasicComponent {
    private final String title;
    private final Font menuFont;

    public MenuButton(GUIContext container, String title, Font menuFont) {
        super(container);
        this.title = title;
        this.menuFont = menuFont;
        this.width = menuFont.getWidth(title);
        this.height = menuFont.getHeight(title);
    }

    @Override
    public void renderImpl(GUIContext container, Graphics g) {
        g.setFont(menuFont);
        g.drawString(title, getX(), getY());
    }


    @Override
    public void mousePressed(int button, int x, int y) {
        if (x >= getX() && x <= getX() + width && y >= getY() && y <= getY() + height) {
            notifyListeners();
        }
    }
}
