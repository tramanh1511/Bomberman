import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static sun.java2d.cmm.ColorTransform.In;

public class AStar {
    private Node[][] searchArea; // mang chua cac Node theo khu vuc tim kiem (map dau vao)
    private PriorityQueue<Node> openList; // ds dong theo do uu tien f nho
    private Set<Node> closedSet; // luu cac Node da truy cap
    private Node initialNode; // Node bat dau
    private Node finalNode;  // Node ket thuc

    /**
     * ham khoi tao theo
     *
     * @param Map      mang dau vao theo tung map
     * @param RowInitial hang cua diem bat dau
     * @param ColInitial cot cua diem bat dau
     * @param RowFinal   hang cua diem ket thuc
     * @param ColFinal   cot cua diem ket thuc
     */
    public AStar(char[][] Map, int RowInitial, int ColInitial, int RowFinal, int ColFinal) {
        this.initialNode = new Node(RowInitial, ColInitial);
        this.finalNode = new Node(RowFinal, ColFinal);
        searchArea = new Node[Map.length][Map[0].length];
        setNodes(Map);
        openList = new PriorityQueue<>(Comparator.comparingInt(Node::getF)); // uu tien theo f nho
        closedSet = new HashSet<>();
    }

    /**
     * update cac Node trong searchArea
     * Trong do: row, col, h, isBlocked la nhung cai tim luon duoc
     * g, f, parent la nhung cai update trong qua trinh tim kiem
     */
    public void setNodes(char[][] Map) {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                searchArea[i][j] = new Node(i, j);
                searchArea[i][j].calculateH(finalNode);
                searchArea[i][j].setBlocked(Map[i][j] != ' ');
            }
        }
    }

    /**
     * Tim kiem A*
     *
     * @return danh sach duong di, neu khong tim duoc tra ve null
     */
    public List<Node> aStarSearch() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll(); // tra ve Node co do uu tien cao nhat va xoa khoi hang doi
            /**
             * da tung truy cap currentNode (tu do cap nhat cac Node xung quanh roi)
             * ma theo do uu tien nen currentNode sau khong the co do uu tien hon nen khong can xet no nua
             */
            if (closedSet.contains(currentNode)) {
                continue;
            }
            closedSet.add(currentNode); // them vao danh sach da truy cap
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                updateClosedNode(currentNode); // thuc hien update cac Node xung quanh
            }
        }
        return null;
    }

    /**
     * truy vet
     *
     * @param currentNode chinh la finalNode
     * @return
     */
    public List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        while (currentNode != null) {
            path.add(currentNode); // mang luu se theo thu tu nguoc :>
            currentNode = currentNode.getParent();
        }
        return path;
    }

    public void updateClosedNode(Node currentNode) {
        int[] X = { 0, 0, -1, 1}; // X[i] va Y[i] tuong ung voi 4 huong xung quanh currentNode
        int[] Y = {-1, 1,  0, 0};
        int rows = searchArea.length;
        int cols = searchArea[0].length;
        for (int i = 0; i < X.length; i++) {
            int RowClosedNode = currentNode.getRow() + X[i];
            int ColClosedNode = currentNode.getCol() + Y[i];
            if (RowClosedNode >= 0 && RowClosedNode < rows          // khi ClosedNode nam trong vung hop le
                    && ColClosedNode >= 0 && ColClosedNode < cols) {
                Node ClosedNode = searchArea[RowClosedNode][ColClosedNode];
                if (!ClosedNode.isBlocked() && ClosedNode.checkIsChanged(currentNode)) { // co the di vao va co su thay doi
                    openList.add(ClosedNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }
    public static void main(String[] args) {
        char[][] map = new char[0][];
        FileInputStream fin = null;
        try {
            fin = new FileInputStream("D:\\OOP\\Astar\\src\\Level1.txt");
            //read characters from FileInputStream and write them
            int ch;
            int lv = (int) (fin.read() - '0') ;
            ch = fin.read();
            int rows = (int) (fin.read() - '0') *10 + (int) (fin.read() - '0');
            ch = fin.read();
            int cols = (int) (fin.read() - '0') *10 + (int) (fin.read() - '0');
            System.out.println(rows+"rows\n" + cols +"cols");
            map = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    ch = fin.read();
                    map[i][j] = (char) ch;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(map[0].length+"rows\n");
        for(int i = 0; i< map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        AStar newAStar = new AStar(map,1,2,6,1);
        List<Node> path = newAStar.aStarSearch();
        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i).getRow()+ "  " + path.get(i).getCol());
        }
    }
}
