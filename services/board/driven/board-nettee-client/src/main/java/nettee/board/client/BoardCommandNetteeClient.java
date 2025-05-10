package nettee.board.client;

import nettee.board.Board;
import nettee.board.port.BoardCommandNetteeClientPort;
import nettee.restclient.NetteeClient;
import netttee.client.request.NetteeRequest;
import org.springframework.stereotype.Component;

import static nettee.board.BoardCommandErrorCode.BOARD_ALREADY_EXIST;

@Component
public class BoardCommandNetteeClient implements BoardCommandNetteeClientPort {
    
    @Override
    public Board save() {
        return NetteeClient.post(NetteeRequest.<Board>builder()
                .domain("board")
                .path("/boards")
                .customException(BOARD_ALREADY_EXIST::exception)
                .responseType(Board.class)
                .build()
        );
    }
}
