package m_project.handler;

import m_project.vo.Member;

public class ArrayList {
    private static final int DEFAULT_SIZE = 3;
    private Object[] list = new Object[DEFAULT_SIZE];
    private int length;

    public boolean add(Object obj){
        if(this.length == list.length){
            increase();
        }
        this.list[this.length++] = obj;
        return true;
    }

    private void increase(){
        Object[] arr = new Object[list.length + (list.length >> 1)];
        for(int i = 0; i < list.length; i++){
            arr[i] = list[i];
        }
        list = arr;
    }

    public Object[] list(){
        Object[] arr = new Object[this.length];
        for(int i = 0; i < this.length; i++){
            arr[i] = this.list[i];
        }
        return arr;
    }

    public Object get(Object obj){
        for(int i = 0; i < this.length; i++){
            Object item = this.list[i];
            if(item.equals(obj)){
                return item;
            }
        }
        return null;
    }

    public boolean delete(Object obj){
        int deleteIndex = indexOf(obj);
        if(deleteIndex == -1){
            return false;
        }

        for(int i=deleteIndex; i<this.length-1; i++){
            this.list[i] = this.list[i+1];
        }
        this.list[--this.length] = null;
        return true;
    }

    private int indexOf(Object obj){
        for(int i=0; i<this.length; i++){
          Object item = this.list[i];
          if(item.equals(obj)){
            return i;
          }
        }
        return -1;
      }
}