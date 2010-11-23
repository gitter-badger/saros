package de.fu_berlin.inf.dpp.ui.chat.chatControl.events;


public interface IChatControlListener extends IChatDisplayListener {

    public void characterEntered(CharacterEnteredEvent event);

    public void messageEntered(MessageEnteredEvent event);

}