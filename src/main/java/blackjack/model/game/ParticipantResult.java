package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;

public enum ParticipantResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String detail;

    ParticipantResult(String detail) {
        this.detail = detail;
    }

    public static ParticipantResult of(Dealer dealer, Participant participant) {
        if (participant.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }

        int dealerPoint = dealer.calculatePoint();
        int participantPoint = participant.calculatePoint();

        if (dealerPoint > participantPoint) {
            return LOSE;
        }
        if (dealerPoint < participantPoint) {
            return WIN;
        }
        return DRAW;
    }

    public String getDetail() {
        return detail;
    }
}
