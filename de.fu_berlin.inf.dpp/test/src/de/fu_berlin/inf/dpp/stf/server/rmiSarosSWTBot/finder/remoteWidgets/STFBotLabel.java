package de.fu_berlin.inf.dpp.stf.server.rmiSarosSWTBot.finder.remoteWidgets;

import java.rmi.RemoteException;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;

import de.fu_berlin.inf.dpp.stf.server.rmiSarosSWTBot.sarosFinder.remoteComponents.EclipseComponent;

public interface STFBotLabel extends EclipseComponent {

    /**********************************************
     * 
     * actions
     * 
     **********************************************/

    public abstract void setFocus() throws RemoteException;

    /**********************************************
     * 
     * states
     * 
     **********************************************/

    public abstract boolean isEnabled() throws RemoteException;

    public abstract boolean isVisible() throws RemoteException;

    public abstract boolean isActive() throws RemoteException;

    public abstract String getToolTipText() throws RemoteException;

    /**
     * 
     * @return the text of the first found {@link SWTBotLabel}
     * @throws RemoteException
     */
    public String getText() throws RemoteException;

}