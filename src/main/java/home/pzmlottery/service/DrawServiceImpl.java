package home.pzmlottery.service;

import home.pzmlottery.model.Draw;
import home.pzmlottery.repository.DrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;

@Service
public class DrawServiceImpl implements DrawService {

    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private TicketService ticketService;

    @Override
    public void createNewDraw() {
        Draw draw = new Draw();
        draw.setTimestampstart(new Date().getTime());
        draw.setStatus(Draw.Status.OPEN);
        draw.setAmountrizepool(0L);
        draw.setTransactions(new TreeSet<>());
        drawRepository.saveDraw(draw);
    }

    @Override
    public void updateDraw() {

        if(drawRepository.getDrawByStatus(Draw.Status.OPEN) == null) createNewDraw();
        ticketService.saveNewTickets();
    }

}
