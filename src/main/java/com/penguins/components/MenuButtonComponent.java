package com.penguins.components;

import com.google.common.util.concurrent.Runnables;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

public class MenuButtonComponent extends MouseOverComponent {
    private final String title;
    private final Font menuFont;
    private Runnable onHoverRunnable;

    public MenuButtonComponent(GUIContext container, String title, Font menuFont) {
        this(container, title, menuFont, Runnables.doNothing());
    }

    public MenuButtonComponent(GUIContext container, String title, Font menuFont, Runnable onHoverRunnable) {
        super(container);
        this.title = title;
        this.menuFont = menuFont;
        this.width = menuFont.getWidth(title);
        this.height = menuFont.getHeight(title);
        this.onHoverRunnable = onHoverRunnable;
    }

    public void setOnHover(Runnable onHover) {
        this.onHoverRunnable = onHover;
    }

    @Override
    public Rectangle getShapeBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }

    @Override
    protected void onHover() {
        onHoverRunnable.run();
    }

    @Override
    public void renderImpl(GUIContext container, Graphics g) {
        g.setFont(menuFont);
        g.drawString(title, getX(), getY());
    }
}
