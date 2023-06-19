package bitcamp.util;

public class ArrayList implements List{
  private static final int DEFAULT_SIZE = 3;

  private Object[] list = new Object[DEFAULT_SIZE];
  private int length;

  @Override // 컴파일러에게 다음 메서드가 수퍼클래스의 메서드를 재정의 한 것인지?
  // 또는 인터페이스의 메서드를 구현한 것인지? 검사해달라는 표시다.
  public boolean add(Object obj) {
    if(this.length == list.length) {
      increase();
    }
    this.list[this.length++] = obj;
    return true;
  }

  public void increase() {
    // 기존 배열보다 50% 큰 배열을 새로 만든다.
    Object[] arr = new Object[list.length + (list.length >> 1)];

    // 기존 배열의 값을 새 배열로 복사한다.
    for(int i = 0; i < list.length; i++) {
      arr[i] = list[i];
    }

    // boards 레퍼런스가 새 배열을 가리키도록 한다.
    list = arr;
    //System.out.println("배열 확장: " + members.length);
  }

  @Override
  public Object[] toArray() {
    // 리턴할 값을 담을 배열을 생성
    Object[] arr = new Object[this.length];

    // 원본 배열에서 입력된 인스턴스 주소를 꺼내 새 배열에 담는다.
    for (int i = 0; i < this.length; i++) {
      arr[i] = this.list[i];
    }

    // 새 배열을 리턴한다.
    return arr;
  }

  @Override
  public Object get(int index) {
    if(!isValid(index)) {
      return null;
    }

    return this.list[index];
  }

  @Override
  public boolean remove(Object obj) {
    int deleteIndex = indexOf(obj);
    if(deleteIndex == -1){
      return false;
    }

    for(int i=deleteIndex; i<this.length - 1; i++){
      this.list[i] = this.list[i+1];
    }
    this.list[--this.length] = null;
    return true;
  }

  @Override
  public Object remove(int index) {
    if(!isValid(index)) {
      return null;
    }

    Object old = this.list[index];

    for(int i = index; i < this.length - 1; i++){
      this.list[i] = this.list[i+1];
    }
    this.list[--this.length] = null;

    return old;
  }

  @Override
  public int size() {
    return this.length;
  }

  private boolean isValid(int index) {
    return index >= 0 && index < this.length;
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
