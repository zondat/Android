package vn.fpt.coursesupport.prm.project.kingchess.presenter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.project.kingchess.logic.APiece;
import vn.fpt.coursesupport.prm.project.kingchess.logic.IPieceObserver;
import vn.fpt.coursesupport.prm.project.kingchess.logic.Match;
import vn.fpt.coursesupport.prm.project.kingchess.logic.Position;
import vn.fpt.coursesupport.prm.project.kingchess.view.Cell;
import vn.fpt.coursesupport.prm.project.kingchess.view.GridMap;
import vn.fpt.coursesupport.prm.project.kingchess.view.IGridMapObserver;

public class Presenter implements IPieceObserver, IGridMapObserver {

    private GridMap viewModel;
    private Match gameModel;

    private int highlightColor = Color.parseColor("#DC143C");
    private int selectedColor = Color.parseColor("#FCB53B");
    private int suggestionColor = Color.parseColor("#FFFFAF");

    public Presenter(Context context) {
        viewModel = new GridMap(context, 8, 8);
        viewModel.clickOn(1, 1);
        viewModel.addObserver(this);
        viewModel.init();

        gameModel = new Match();
        gameModel.init();

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
        viewModel.clearBackground();

        // Update Image in cells
        viewModel.setImage(previousPosition.getRow(), previousPosition.getCol(), null);
        viewModel.getCell(previousPosition.getRow(), previousPosition.getCol()).restoreBackground();
        viewModel.setImage(currentPosition.getRow(), currentPosition.getCol(), piece.getImagePath());
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
