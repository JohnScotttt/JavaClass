package src;
 
public class MyArrayList<E> {
	public Object[] array;
	private int size=0;
	
	public MyArrayList() {
		array =new MyArrayList[0];
	}
	
	public void add(E e) {
			Object[] a =new Object[array.length+1];
			for(int i=0;i<array.length;i++) {
				a[i]=array[i];
			}
			a[array.length]=e;
			array=a;
			size++;
	}
	
	@SuppressWarnings("unchecked")
	public E remove(int index) {
		if(index<=0||index>size) return null;
		Object[] a=new Object[array.length-1];
		for(int i=0;i<index;i++) {
			a[i]=array[i];
		}
		Object e=array[index];
		for(int i=index;i<a.length;i++) {
			a[i]=array[i+1];
		}
		array=a;
		return (E)e;
	}
	public void Delete() {
		Object[] a=new Object[array.length-1];
		for(int i=0;i<array.length-1;i++) {
			a[i]=array[i];
		}
		array=a;
		size--;
	}
	
	public int getSize() {
		return size;
	}
	public void setA(int i) {
		array[i]=null;
	}
	
	@SuppressWarnings("unchecked")
	public E get(int index) {
		Object e=array[index];
		return (E)e;
	}
	
	public void Reset() {
		Object[] a =new Object[0];
		size=0;
		array =a;
	}
}