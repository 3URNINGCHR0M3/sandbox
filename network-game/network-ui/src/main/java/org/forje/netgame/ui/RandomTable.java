package org.forje.netgame.ui;

import org.forje.netgame.engine.Board;
import org.forje.netgame.engine.PieceType;
import org.forje.netgame.engine.Tile;

import java.util.Random;

/**
 * Created by Brian on 9/1/15.
 */
public class RandomTable extends Board {

    private static Random RANDOM = new Random();

    private final int _size;

    public RandomTable(final int size) {

        super(size,size);

        _size = size;

        final PieceType[] pieceTypes = PieceType.values();
        int maxRand = pieceTypes.length;

        for (int i = 0; i < size;  i++) {

            for (int j = 0; j < size; j++) {
                int index = RANDOM.nextInt(maxRand);
                PieceType pieceType = pieceTypes[index];
                Tile tile = new Tile(pieceType);

                int r = RANDOM.nextInt(4);

                for (int x=0; x < r; x++) {
                    tile.rotate();
                }

                setTile(i, j, tile);

            }

        }


    }

}
