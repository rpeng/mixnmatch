package com.penguins.components;

import com.google.common.util.concurrent.Runnables;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

public class MenuButtonComponent extends MouseOverComponent {
    private final String title;
    private final Font menuFont;
    private final Font hoverFont;
    private Runnable onHoverRunnable;

    public MenuButtonComponent(GUIContext container, String title, Font menuFont, Font hoverFont) {
        this(container, title, menuFont, hoverFont, Runnables.doNothing());
    }

    public MenuButtonComponent(GUIContext container, String title, Font menuFont, Font hoverFont, Runnable onHoverRunnable) {
        super(container);
        this.title = title;
        this.menuFont = menuFont;
        this.hoverFont = hoverFont;
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
    protected void onHoverStart() {
        onHoverRunnable.run();
    }

    @Override
    public void renderImpl(GUIContext container, Graphics g) {

        if (isMouseOver()){
            g.setFont(hoverFont);
        }else{
            g.setFont(menuFont);
        }

        g.drawString(title, getX(), getY());
    }
}
