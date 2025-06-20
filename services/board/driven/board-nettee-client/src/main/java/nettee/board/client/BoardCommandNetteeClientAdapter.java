package nettee.board.client;

import lombok.RequiredArgsConstructor;
import nettee.board.domain.Board;
import nettee.board.application.port.BoardCommandNetteeClientPort;
import nettee.restclient.NetteeClient;
import nettee.client.request.NetteeRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardCommandNetteeClientAdapter implements BoardCommandNetteeClientPort {

    private final NetteeClient netteeClient;

    @Override
    public Board save(Board board) {
        return netteeClient.post(NetteeRequest.<Board>builder()
                .domain("board")
                .path("/boards")
                .body(board)
                .responseType(Board.class)
                .build()
        );
    }
}
