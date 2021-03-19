package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();
    private static final int PLAYER_CAR_DISTANCE = 12;
    private int passedCarsCount = 0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type.equals(RoadObjectType.THORN)) {
            return new Thorn(x, y);
        }
        if (type.equals(RoadObjectType.DRUNK_CAR)) {
            return new MovingCar(x, y);
        }
        else return new Car(type, x, y);
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y =  -1 * RoadObject.getHeight(type);
        RoadObject roadObject = createRoadObject(type, x, y);
        if (isRoadSpaceFree(roadObject)) {
            items.add(roadObject);
        }
    }

    public void draw(Game game) {
        for (RoadObject object: items) {
            object.draw(game);
        }
    }

    public void move(int boost) {
        for (RoadObject object: items) {
            object.move(boost + object.speed, items);
        }
        deletePassedItems();
    }

    private boolean isThornExists() {
        for (RoadObject object: items) {
            if (object.type == RoadObjectType.THORN) {
                return true;
            }
        }
        return false;
    }

    private void generateThorn(Game game) {
        int randomNumber = game.getRandomNumber(100);
        if (randomNumber < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void generateRegularCar(Game game) {
        int randomNumber = game.getRandomNumber(100);
        int carTypeNumber = game.getRandomNumber(4);
        if (randomNumber < 30) addRoadObject( RoadObjectType.values()[carTypeNumber], game);
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems() {
        /*
        #1
        Iterator<RoadObject> iterator = items.iterator();
        while (iterator.hasNext()) {
            RoadObject object = iterator.next();
            if (object.y > RacerGame.HEIGHT) {
                iterator.remove();
            }
        }
         */



        for (RoadObject item : new ArrayList<>(items)) {
            if (item.y >= RacerGame.HEIGHT) {
                items.remove(item);
                if (!item.type.equals(RoadObjectType.THORN)) passedCarsCount++;
            }
        }


//        items.removeIf(object -> object.y >= RacerGame.HEIGHT);

    }

    public boolean checkCrush(PlayerCar playerCar) {
        for (RoadObject item: items) {
            if (item.isCollision(playerCar)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject item: items) {
            if (item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMovingCarExists() {
        for (RoadObject item: items) {
             if (item.type.equals(RoadObjectType.DRUNK_CAR)) return true;
        }
        return false;
    }

    private void generateMovingCar(Game game) {
        int randomNumber = game.getRandomNumber(100);
        if (randomNumber < 10 && !isMovingCarExists()) addRoadObject(RoadObjectType.DRUNK_CAR, game);
    }
}
