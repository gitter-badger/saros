package de.fu_berlin.inf.dpp.stf.client.test.testcases.rolesAndFollowmode;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.rmi.RemoteException;

import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fu_berlin.inf.dpp.stf.client.test.helpers.InitMusician;
import de.fu_berlin.inf.dpp.stf.client.test.helpers.STFTest;

public class TestFollowMode extends STFTest {

    @BeforeClass
    public static void initMusicians() throws RemoteException {
        alice = InitMusician.newAlice();
        bob = InitMusician.newBob();
        alice.pEV.newJavaProjectWithClass(PROJECT1, PKG1, CLS1);
        alice.shareProjectWithDone(PROJECT1, CONTEXT_MENU_SHARE_PROJECT, bob);
    }

    @AfterClass
    public static void resetSaros() throws RemoteException {
        bob.workbench.resetSaros();
        alice.workbench.resetSaros();
    }

    @After
    public void cleanUp() throws RemoteException {
        if (bob.state.isInFollowMode())
            bob.sessionV.stopFollowingThisUser(alice.state);
        if (alice.state.isInFollowMode())
            alice.sessionV.stopFollowingThisUser(bob.state);
        bob.workbench.resetWorkbench();
        alice.workbench.resetWorkbench();
    }

    /**
     * TODO: It exists still some bugs in saros by giving exclusive driver role,
     * so you may get exception by perform this test.
     * 
     * @throws IOException
     * @throws CoreException
     */
    @Test
    public void testBobFollowAlice() throws IOException, CoreException {
        alice.editor.setTextInJavaEditorWithSave(CP1, PROJECT1, PKG1, CLS1);
        bob.sessionV.followThisUser(alice.state);
        bob.editor.waitUntilJavaEditorActive(CLS1);
        assertTrue(bob.state.isInFollowMode());
        assertTrue(bob.editor.isJavaEditorActive(CLS1));

        String clsContentOfAlice = alice.state.getClassContent(PROJECT1, PKG1,
            CLS1);

        bob.state.waitUntilClassContentsSame(PROJECT1, PKG1, CLS1,
            clsContentOfAlice);
        String clsContentOfBob = bob.state
            .getClassContent(PROJECT1, PKG1, CLS1);
        assertTrue(clsContentOfBob.equals(clsContentOfAlice));

        alice.pEV.newClass(PROJECT1, PKG1, CLS2);
        bob.editor.waitUntilJavaEditorActive(CLS2);
        assertTrue(bob.editor.isJavaEditorActive(CLS2));

        /*
         * After new release 10.10.28 all of the observer is automatically in
         * follow mode(are the observers really in follow mode???) when host
         * give someone a exclusive driver role. So the following line have to
         * comment out, otherwise you should get WidgetNotFoundException.
         */
        // alice.sessionV.followThisUser(bob.state);
        bob.editor.activateJavaEditor(CLS1);
        alice.editor.waitUntilJavaEditorActive(CLS1);
        assertTrue(alice.state.isInFollowMode());
        assertTrue(alice.editor.isJavaEditorActive(CLS1));

        bob.sessionV.followThisUser(alice.state);
        alice.pEV.newClass(PROJECT1, PKG1, CLS3);
        alice.editor.waitUntilJavaEditorActive(CLS3);
        alice.editor.setTextInJavaEditorWithSave(CP3, PROJECT1, PKG1, CLS3);
        alice.editor.setBreakPoint(13, PROJECT1, PKG1, CLS3);
        // alice.debugJavaFile(BotConfiguration.PROJECTNAME,
        // BotConfiguration.PACKAGENAME, BotConfiguration.CLASSNAME3);
        // bob.waitUntilJavaEditorActive(BotConfiguration.CLASSNAME3);
        // assertFalse(bob.isDebugPerspectiveActive());
        // alice.openJavaPerspective();
        // bob.sleep(1000);
        // int lineFromAlice = alice.getJavaCursorLinePosition(
        // BotConfiguration.PROJECTNAME, BotConfiguration.PACKAGENAME,
        // BotConfiguration.CLASSNAME3);
        // int lineFromBob = bob.getJavaCursorLinePosition(
        // BotConfiguration.PROJECTNAME, BotConfiguration.PACKAGENAME,
        // BotConfiguration.CLASSNAME3);
        // assertEquals(lineFromAlice, lineFromBob);
        // alice.waitUntilShellActive("Confirm Perspective Switch");
        // assertTrue(alice.isShellActive("Confirm Perspective Switch"));
    }
}