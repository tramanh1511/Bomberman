package uet.oop.bomberman.Menu;

import uet.oop.bomberman.graphics.loadMap;

import java.io.FileNotFoundException;

public class gameMenu {
    public gameMenu() throws FileNotFoundException {
        new loadMap("C:\\Users\\TRAM ANH\\OneDrive - vnu.edu.vn\\Dai hoc\\Kì I (2022-2023)\\oop\\bomberman-starter-starter-2\\bomberman-starter-starter-2\\res\\levels\\Level1.txt");

    }
}
