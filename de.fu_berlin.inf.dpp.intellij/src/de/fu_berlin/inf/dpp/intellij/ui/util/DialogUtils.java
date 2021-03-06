/*
 *
 *  DPP - Serious Distributed Pair Programming
 *  (c) Freie Universität Berlin - Fachbereich Mathematik und Informatik - 2010
 *  (c) NFQ (www.nfq.com) - 2014
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 1, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * /
 */

package de.fu_berlin.inf.dpp.intellij.ui.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import de.fu_berlin.inf.dpp.SarosPluginContext;
import org.picocontainer.annotations.Inject;

import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Container;

/**
 * Dialog message helper that shows Dialogs in the current Thread.
 */
public class DialogUtils {

    @Inject
    private static Project project;

    private DialogUtils() {
    }

    static {
        SarosPluginContext.initComponent(new DialogUtils());
    }

    /**
     * Displays an error message.
     *
     * @param parent the parent Component. If <code>parent</code> is null, the
     *               project's window is used.
     * @param title
     * @param msg
     */
    public static void showError(Component parent, String title, String msg) {
        JOptionPane
            .showInternalMessageDialog(notNullOrDefaultParent(parent), msg,
                title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a confirmation dialog.
     *
     * @param parent the parent Component. If <code>parent</code> is null, the
     *               project's window is used.
     * @param title
     * @param msg
     * @return <code>true</code>, if OK was chosen, <code>false</code> otherwise
     */
    public static boolean showConfirm(Component parent, String title,
        String msg) {
        int resp = JOptionPane
            .showConfirmDialog(notNullOrDefaultParent(parent), msg, title,
                JOptionPane.OK_CANCEL_OPTION);
        return resp == JOptionPane.OK_OPTION;
    }

    /**
     * Shows a Yes/No question dialog.
     *
     * @param parent the parent Component. If <code>parent</code> is null, the
     *               project's window is used.
     * @param title
     * @param msg
     * @return <code>true</code> if Yes was chosen, <code>false</code> otherwise
     */
    public static boolean showQuestion(Component parent, String title,
        String msg) {
        int answer = JOptionPane
            .showConfirmDialog(notNullOrDefaultParent(parent), msg, title,
                JOptionPane.YES_NO_OPTION);

        return answer == JOptionPane.YES_OPTION;
    }

    /**
     * Shows an Info dialog.
     *
     * @param parent the parent Component. If <code>parent</code> is null, the
     *               project's window is used.
     * @param title
     * @param msg
     */
    public static void showInfo(Container parent, String title, String msg) {
        JOptionPane
            .showMessageDialog(notNullOrDefaultParent(parent), msg, title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static Component notNullOrDefaultParent(Component parent) {
        return parent != null ?
            parent :
            WindowManager.getInstance().getFrame(project);
    }
}