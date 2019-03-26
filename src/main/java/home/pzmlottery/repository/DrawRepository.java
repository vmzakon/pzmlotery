package home.pzmlottery.repository;

import home.pzmlottery.model.Draw;

public interface DrawRepository {
    void saveDraw(Draw draw);
    Draw getDrawByStatus(Draw.Status status);
}
