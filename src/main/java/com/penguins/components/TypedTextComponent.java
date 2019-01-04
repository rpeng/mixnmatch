package com.penguins.components;

import com.penguins.GameController;
import com.penguins.animations.ComputedAnimation;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.GUIContext;

import static com.penguins.sound.SoundEffect.HOVER_C;
import static com.penguins.sound.SoundEffect.TYPE;

public class TypedTextComponent extends ComputedAnimation {

    private GameController gc;
    private final GUIContext container;
    private String text;
    private int tickDelay;
    private final Font font;
    private int currentFrame = 0;

    public TypedTextComponent(GameController gc, GUIContext container, String text, int tickDelay, Font font){
        super(text.replace(" ", "").length(), tickDelay);
        this.gc = gc;
        this.container = container;
        this.text = text;
        this.tickDelay = tickDelay;
        this.font = font;

        setLooping(false);
    }

    public void draw(Graphics g, float x, float y, Color col) {
        updateNoDraw();
        g.setFont(font);
        g.setColor(col);
        g.drawString(substringNoSpace(text, getFrame()), x, y);

        if (getFrame() != currentFrame) {
            currentFrame = getFrame();
            gc.getSoundController().playSoundEffect(TYPE);
        }

    }

    private String substringNoSpace(String text, int index){
        int end = index;
        for (int i = 0; i < end; i++){
            if (text.charAt(i) == ' '){
                index++;
            }
        }
        return text.substring(0, index);
    }
}
