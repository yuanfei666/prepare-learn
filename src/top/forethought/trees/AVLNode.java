package top.forethought.trees;

import lombok.Data;

@Data
public class AVLNode <T> {
    private T data;
    private AVLNode lChild;
    private AVLNode rChild;
    private int height;
    public AVLNode(T data) {
        this.data = data;
        lChild=null;
        rChild=null;
        this.height=0;
    }
}
