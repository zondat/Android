package vn.fpt.coursesupport.prm.project.kingchess.presenter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.fpt.coursesupport.prm.project.kingchess.logic.APiece;
import vn.fpt.coursesupport.prm.project.kingchess.logic.Board;
import vn.fpt.coursesupport.prm.project.kingchess.logic.IPieceObserver;
import vn.fpt.coursesupport.prm.project.kingchess.logic.Match;
import vn.fpt.coursesupport.prm.project.kingchess.logic.Position;
import vn.fpt.coursesupport.prm.project.kingchess.view.Cell;
import vn.fpt.coursesupport.prm.project.kingchess.view.GridMap;
import vn.fpt.coursesupport.prm.project.kingchess.view.IGridMapObserver;

public class Presenter implements IPieceObserver, IGridMapObserver {

    private final GridMap viewModel;
    private final Match gameModel;
    private final Board board;
    private final int highlightColor = Color.parseColor("#DC143C");
    private final int selectedColor = Color.parseColor("#FCB53B");
    private final int suggestionColor = Color.parseColor("#FFFFAF");
    private Map<APiece, String> imagePaths;

    public Presenter(Context context) {
        viewModel = new GridMap(context, 8, 8);
        viewModel.clickOn(1, 1);
        viewModel.addObserver(this);
        viewModel.init();

        gameModel = new Match();

        board = gameModel.getBoard();

        imagePaths = new HashMap<>();
        imagePaths.put(board.getBlackTeam().getPiece(0), "b_king_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(1), "b_queen_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(2), "b_bishop_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(3), "b_bishop_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(4), "b_knight_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(5), "b_knight_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(6), "b_rook_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(7), "b_rook_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(8), "b_pawn_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(9), "b_pawn_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(10), "b_pawn_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(11), "b_pawn_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(12), "b_pawn_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(13), "b_pawn_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(14), "b_pawn_48x48");
        imagePaths.put(board.getBlackTeam().getPiece(15), "b_pawn_48x48");

        imagePaths.put(board.getWhiteTeam().getPiece(0), "w_king_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(1), "w_queen_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(2), "w_bishop_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(3), "w_bishop_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(4), "w_knight_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(5), "w_knight_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(6), "w_rook_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(7), "w_rook_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(8), "w_pawn_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(9), "w_pawn_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(10), "w_pawn_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(11), "w_pawn_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(12), "w_pawn_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(13), "w_pawn_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(14), "w_pawn_48x48");
        imagePaths.put(board.getWhiteTeam().getPiece(15), "w_pawn_48x48");

        for (APiece piece : gameModel.getBoard().getAllPieces()) {
            piece.addObserver(this);
            piece.notifyMove(piece.getCurrentPosition(), piece.getCurrentPosition());
        }
    }

    @Override
    public void updateSelection(APiece selectedPiece) {
        List<Position> lockedPositions = new ArrayList<>(gameModel.getBoard().getAllPositions());
        Position currPos = selectedPiece.getCurrentPosition();
        lockedPositions.remove(currPos);

        // Highlight selected position
        Cell cell = viewModel.getCell(currPos.getRow(), currPos.getCol());
        cell.setBackground(selectedColor);
        cell.unlock();

        // Highlight movable zone
        List<Position> movableZone = selectedPiece.getMovableZone();
        lockedPositions.removeAll(movableZone);
        for (Position movablePos : movableZone) {
            cell = viewModel.getCell(movablePos.getRow(), movablePos.getCol());
            cell.setBackground(suggestionColor);
            cell.unlock();
        }

        // Highlight killing zone
        List<Position> killableZone = selectedPiece.getKillingZone();
        lockedPositions.removeAll(killableZone);
        for (Position killablePos : killableZone) {
            cell = viewModel.getCell(killablePos.getRow(), killablePos.getCol());
            cell.setBackground(highlightColor);
            cell.unlock();
        }

        // Unlock teammate position for switching selected piece
        for (APiece teammate : selectedPiece.getTeammates()) {
            Position pos = teammate.getCurrentPosition();
            lockedPositions.remove(pos);
            viewModel.getCell(pos.getRow(), pos.getCol()).unlock();
        }

        // Lock all other free positions
        for (Position pos : lockedPositions) {
            viewModel.getCell(pos.getRow(), pos.getCol()).lock();
        }
    }

    @Override
    public void updateUnSelect(APiece unselectedPiece) {
        List<Position> restorePos = new ArrayList<>();
        restorePos.add(unselectedPiece.getCurrentPosition());
        restorePos.addAll(unselectedPiece.getMovableZone());
        restorePos.addAll(unselectedPiece.getKillingZone());

        for (Position pos: restorePos) {
            viewModel.getCell(pos.getRow(), pos.getCol()).restoreBackground();
        }
    }

    @Override
    public void updateMove(APiece piece, Position previousPosition, Position currentPosition) {
        // Remove moving suggestion zone
//        viewModel.clearBackground();

        // Update Image in cells
        viewModel.setImage(previousPosition.getRow(), previousPosition.getCol(), null);
        viewModel.getCell(previousPosition.getRow(), previousPosition.getCol()).restoreBackground();
        viewModel.setImage(currentPosition.getRow(), currentPosition.getCol(), imagePaths.get(piece));
    }

    @Override
    public void updateDead(APiece object) {
        viewModel.setImage(object.getCurrentPosition().getRow(), object.getCurrentPosition().getCol(), null);
    }

    @Override
    public void updateClickOn(int row, int col) {
        APiece selectedPiece = gameModel.getBoard().getSelectedPiece();
        APiece pieceAtPos = gameModel.getBoard().getPieceAt(row, col);
        if (selectedPiece!=null) {
            // Manipulate selected piece (second click)
            if (pieceAtPos==null) {
                selectedPiece.move(row, col);
                gameModel.getBoard().switchTeam();
                viewModel.unlockAll();
            }
            else if (pieceAtPos.isEnemy(selectedPiece)) {
                pieceAtPos.die();
                selectedPiece.move(row, col);
                gameModel.getBoard().switchTeam();
                viewModel.unlockAll();
            }
            else {
                // pieceAtPos is not null and at the same team
                gameModel.getBoard().selectPiece(pieceAtPos);
            }
        } else {
            // Select piece to manipulate (first click)
            if (pieceAtPos!=null && pieceAtPos.isTeam(gameModel.getBoard().getActiveTeam()))
                gameModel.getBoard().selectPiece(pieceAtPos);
        }
    }

    public View getView() {
        return viewModel.getView();
    }

}