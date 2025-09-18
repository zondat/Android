package vn.fpt.coursesupport.prm.project.gamecaro.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.project.gamecaro.logic.GameCaro;
import vn.fpt.coursesupport.prm.project.gamecaro.logic.ICaroGameObserver;
import vn.fpt.coursesupport.prm.project.gamecaro.view.GridMap;
import vn.fpt.coursesupport.prm.project.gamecaro.view.IGridMapObserver;

public class Presenter implements IGridMapObserver, ICaroGameObserver {

    private GameCaro gameModel;
    private GridMap viewModel;
    private List<String> markImages;
    private int nRows = 15, nCols = 12;

    public Presenter(Context context) {
        markImages = new ArrayList<>();
        markImages.add("icon_o");
        markImages.add("icon_x");

        gameModel = new GameCaro();
        gameModel.createMap(nRows,nCols);
        gameModel.createPlayer(1);
        gameModel.createPlayer(2);
        gameModel.setCurrentPlayer(0);
        gameModel.addObserver(this);

        viewModel = new GridMap(context, nRows, nCols);
        viewModel.addObserver(this);
        viewModel.init();
    }

    @Override
    public void updateCurrentPlayer(int playerIndex) {
        Log.d("Game Caro", "Switch player, current player: " + playerIndex);
    }

    @Override
    public void updateMove(int row, int col, int mark) {
        viewModel.getCell(row, col).setImage(markImages.get(mark-1));
    }

    @Override
    public void updateWinner(int playerIndex) {
        viewModel.disable();
    }

    @Override
    public void updateClickOn(int row, int col) {
        gameModel.handleClick(row, col);
    }

    public View getView() {
        return viewModel.getView();
    }
}
