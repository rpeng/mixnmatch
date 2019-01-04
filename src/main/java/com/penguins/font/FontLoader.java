package com.penguins.font;

import com.google.common.collect.ImmutableMap;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.ShadowEffect;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;
import java.util.Map;

public class FontLoader {

    private Map<GameFont, UnicodeFont> fonts;

    public FontLoader() {}

    public void loadFonts() {

        InputStream fontFile = ResourceLoader.getResourceAsStream("fonts/font.ttf");
        try {

            java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontFile);
            UnicodeFont defaultFont = new UnicodeFont(font.deriveFont(28f));
            defaultFont.addAsciiGlyphs();
            defaultFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
            defaultFont.loadGlyphs();

            UnicodeFont defaultShadowFont = new UnicodeFont(font.deriveFont(28f));
            defaultShadowFont.addAsciiGlyphs();
            defaultShadowFont.getEffects().add(new ShadowEffect(java.awt.Color.black, 4, 4, 0.7f));
            defaultShadowFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
            defaultShadowFont.loadGlyphs();

            UnicodeFont defaultHighlightedFont = new UnicodeFont(font.deriveFont(32f));
            defaultHighlightedFont.addAsciiGlyphs();
            defaultHighlightedFont.getEffects().add(new ShadowEffect(java.awt.Color.black, 4, 4, 0.7f));
            defaultHighlightedFont.getEffects().add(new ColorEffect(new java.awt.Color(255, 90, 255)));
            defaultHighlightedFont.loadGlyphs();

            fonts = ImmutableMap.of(GameFont.DEFAULT, defaultFont,
                    GameFont.DEFAULT_SHADOW, defaultShadowFont,
                    GameFont.DEFAULT_HIGHLIGHT, defaultHighlightedFont);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public UnicodeFont getFont(GameFont font){
        return fonts.get(font);
    }
}
