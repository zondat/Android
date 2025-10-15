package vn.fpt.coursesupport.prm.mvvm.gamedama.viewmodel;

import vn.fpt.coursesupport.prm.mvvm.gamedama.model.Game;
import vn.fpt.coursesupport.prm.mvvm.gamedama.model.IGameDamaObserver;
import vn.fpt.coursesupport.prm.mvvm.gamedama.model.Location;
import vn.fpt.coursesupport.prm.mvvm.gamedama.model.Piece;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class BoardViewModel extends ViewModel implements IGameDamaObserver {
    private Game gameDama;
    private Piece selectedPiece;
    public final ObservableField<Integer>[][] cellImages = new ObservableField[8][8];
    private int blackPiece, whitePiece, blackDamePiece, whiteDamePiece, transparentDark, transparentLight;

    public BoardViewModel(int blackId, int whiteId, int blackDameId, int whiteDameId, int darkId, int lightId) {
        gameDama   = new Game();
        gameDama.addObserver(this);

        blackPiece = blackId;
        whitePiece = whiteId;
        blackDamePiece = blackDameId;
        whiteDamePiece = whiteDameId;
        transparentDark = darkId;
        transparentLight = lightId;

        for (int r=0; r<8; r++) {
            for (int c=0; c<8; c++) {
                cellImages[r][c] = new ObservableField<>(getImage(r, c));
            }
        }
    }

    private void updateCell(int row, int col) {
        cellImages[row][col].set(getImage(row, col));
    }

    private int getImage(int row, int col) {
        Piece piece = gameDama.getPiece(row, col);
        if (piece == null) {
            if ((row + col)%2 == 0) return transparentDark;
            else return transparentLight;
        } else {
            if (piece.getPlayer() == gameDama.getPlayer1()) {
                if (piece.isDame()) return blackDamePiece;
                else return blackPiece;
            } else {
                if (piece.isDame()) return whiteDamePiece;
                else return whitePiece;
            }
        }
    }

    public void onClickedOn(int row, int col) {
        Piece piece = gameDama.getPiece(row, col);

        if (piece!=null) {
            if (piece.getPlayer() == gameDama.getCurrentPlayer()) {
                selectedPiece = piece;
            }
        }
        else {
            if (selectedPiece!=null) {
                boolean moveNext = selectedPiece.move(row, col);
                if (!moveNext) gameDama.switchPlayer();

                // Reset variable
                selectedPiece = null;
            }
        }


    }

    @Override
    public void updateMove(Piece piece, Location src, Location dest) {
        updateCell(src.getRow(), src.getCol());
        updateCell(dest.getRow(), dest.getCol());
    }

    @Override
    public void updateDameTransform(Piece piece) {
        updateCell(piece.getRow(), piece.getCol());
    }

    @Override
    public void updateDead(Piece piece) {
        updateCell(piece.getRow(), piece.getCol());
    }
}
