public class Node {
    private int row; // hang
    private int col; // cot
    private int g = 0; // khoang cach tu node ban dau den node hien tai
    private int h = 0; // khoang cach tu node hien tai den node cuoi
    private int f = 0; // f = g + h
    private boolean isBlocked = false; // bi chan
    private Node parent = null;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * h duoc tinh bang tong khoang cach giua cac cot va cac dong
     */
    public void calculateH(Node finalNode) {
        this.h = Math.abs(finalNode.getRow() - row) + Math.abs(finalNode.getCol() - col);
    }

    public void calculateF() {
        f = h + g;
    }

    @Override
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.row == other.getRow() && this.col == other.getCol();
    }

    /**
     * Kiem tra xem 1 Node co thay doi (co di ngay sau) (co parent) theo currentNode khong
     */
    public boolean checkIsChanged(Node currentNode) {
        if (g == 0 || g > currentNode.getG() + 1) { // chi co initialNode co g = 0, Node con lai > 0
            // => ktr g==0 la chua co parent => nhan tat ca lam cha duoc =))
            // neu khong => chi nhan nhung Node khien g nho hon lam cha
            this.changeNodeData(currentNode);
            return true;
        }
        return false;
    }

    /**
     * update parent, g, f theo currentNode
     */
    public void changeNodeData(Node currentNode) {
        parent = currentNode;
        g = currentNode.getG() + 1;
        calculateF();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlock) {
        this.isBlocked = isBlock;
    }
}
