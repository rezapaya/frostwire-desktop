/*
 * Created by Angel Leon (@gubatron), Alden Torres (aldenml)
 * Copyright (c) 2011, 2012, 2013, FrostWire(R). All rights reserved.
 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frostwire.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.UIManager;

import com.frostwire.gui.theme.ThemeMediator;

/**
 * 
 * @author gubatron
 * @author aldenml
 * 
 */
public class LocaleLabel extends JLabel {

    public void setText(LocaleString text) {
        Font f;
        if (text.canDisplay()) {
            f = getParent().getFont();
        } else {
            f = ThemeMediator.DIALOG_FONT;
        }

        changeFont(f);
        setText(text.getValue());
    }

    private void changeFont(Font f) {
        if (f != null && !f.equals(getFont())) {
            setFont(f);
        }
    }

    public static class LocaleString {

        private final String value;

        private Boolean canDisplay;

        public LocaleString(String value) {
            this.value = value;
            this.canDisplay = null;
        }

        public String getValue() {
            return value;
        }

        public boolean canDisplay() {
            if (canDisplay == null) {
                canDisplay = getDefaultFont().canDisplayUpTo(value) == -1;
                System.out.println("display probe");
            }

            return canDisplay;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        private Font getDefaultFont() {
            return (Font) UIManager.getDefaults().get("defaultFont");
        }
    }
}
