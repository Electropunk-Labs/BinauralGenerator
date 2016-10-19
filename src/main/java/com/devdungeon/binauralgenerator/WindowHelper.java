/*
 * Copyright (C) 2016 NanoDano <nanodano@devdungeon.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.devdungeon.binauralgenerator;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NanoDano <nanodano@devdungeon.com>
 */
public class WindowHelper {

    /**
     *
     */
    public static void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TipsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TipsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TipsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TipsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @return
     */
    public static List<Image> loadIcons() {
        List<Image> icons = new ArrayList<>();
        icons.add(loadIcon("/logos/devdungeon16x16.png"));
        icons.add(loadIcon("/logos/devdungeon32x32.png"));
        icons.add(loadIcon("/logos/devdungeon48x48.png"));
        icons.add(loadIcon("/logos/devdungeon64x64.png"));
        icons.add(loadIcon("/logos/devdungeon128x128.png"));
        icons.add(loadIcon("/logos/devdungeon256x256.png"));
        return icons;
    }

    /**
     *
     * @param name
     * @return
     */
    public static Image loadIcon(String name) {
        Image icon = Toolkit.getDefaultToolkit().getImage(BinauralGenerator.class.getResource(name));
        return icon;
    }
}
