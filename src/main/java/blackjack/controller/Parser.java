package blackjack.controller;

import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.Arrays;

public class Parser {

    public static final String DELIMITER = ",";
    public static final String RECEIVE_COMMAND = "y";
    public static final String NOT_RECEIVE_COMMAND = "n";

    public static Participants parseParticipants(String names) {
        String[] splittedNames = names.split(DELIMITER);
        validateNameCount(splittedNames);
        return new Participants(Arrays.stream(splittedNames)
                .map(Participant::new)
                .toList());
    }

    public static boolean parseCommand(String comamnd) {
        validateCommand(comamnd);
        return comamnd.equals(RECEIVE_COMMAND);
    }

    private static void validateNameCount(String[] splittedNames) {
        if (splittedNames.length == 0) {
            throw new IllegalArgumentException("이름을 올바르게 입력해 주세요.");
        }
    }

    private static void validateCommand(String command) {
        if (!command.equals(RECEIVE_COMMAND) && !command.equals(NOT_RECEIVE_COMMAND)) {
            throw new IllegalArgumentException("y 또는 n을 입력해 주세요.");
        }
    }
}
