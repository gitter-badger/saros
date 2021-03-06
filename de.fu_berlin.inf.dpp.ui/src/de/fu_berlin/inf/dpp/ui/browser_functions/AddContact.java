package de.fu_berlin.inf.dpp.ui.browser_functions;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.XMPPException;

import de.fu_berlin.inf.dpp.HTMLUIContextFactory;
import de.fu_berlin.inf.dpp.HTMLUIStrings;
import de.fu_berlin.inf.dpp.net.xmpp.JID;
import de.fu_berlin.inf.dpp.ui.JavaScriptAPI;
import de.fu_berlin.inf.dpp.ui.core_facades.StateFacade;

/**
 * Add a given contact to the roster.
 */
public class AddContact extends TypedJavascriptFunction {

    private static final Logger LOG = Logger.getLogger(AddContact.class);

    public static final String JS_NAME = "addContact";

    private final StateFacade stateFacade;

    /**
     * Created by PicoContainer
     * 
     * @param stateFacade
     * @see HTMLUIContextFactory
     */
    public AddContact(StateFacade stateFacade) {
        super(JS_NAME);
        this.stateFacade = stateFacade;
    }

    /**
     * Adds contact (given by its JID) to the roster of the active user.
     * <p>
     * An error is shown to the user if this operation fails.
     * 
     * @param jid
     *            The JID of the new contact
     * @param nickname
     *            How the new contact should be displayed in the roster
     */
    @BrowserFunction
    public void addContact(String jid, String nickname) {
        try {
            stateFacade.addContact(new JID(jid), nickname);
        } catch (XMPPException e) {
            LOG.error("Error while adding contact", e);
            JavaScriptAPI.showError(browser, HTMLUIStrings.ADD_CONTACT_FAILED);
        }
    }
}
