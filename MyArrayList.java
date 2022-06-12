package EightModule;

import java.util.Arrays;

//Написать свой класс MyArrayList как аналог классу ArrayList.
//Можно использовать 1 массив для хранения данных.
//Методы

//add(Object value) добавляет элемент в конец               +
//remove(int index) удаляет элемент под индексом            +
//clear() очищает коллекцию                                 -
//size() возвращает размер коллекции                        +
//get(int index) возвращает элемент под индексом            +

public class MyArrayList {
    private int [] elements;  //массив, для хранения чисел
    private int size;  //поле-счетчик, которое указывает на количество элементов в массиве
    private static final int DEFAULT_CAPACITY = 10;  //размер массива по умолчанию

//конструктор без параметров, который создает массив на 10 элементов, если размер не был указан
    public MyArrayList(){
        this.elements = new int[DEFAULT_CAPACITY];
    }

//создает массив указанной емкости
    public MyArrayList(int initialCapacity){
        if (initialCapacity >= 0){
            this.elements = new int[initialCapacity];
        }
        else {
            throw new IllegalStateException("Capacity can't be less than 0!");
        }
    }
//методы:
//add(Object value) добавляет элемент в конец
    public boolean add(int value){
    if (size == elements.length){  //если в массиве места нет
        elements = increaseCapacity(); //вызываем метод, который отвечает за увеличение массива
    }
    elements[size] = value; //записываем в конец списка новое значение
    size++;  //увеличиваем значение переменной размера списка
    return true;
    }
//remove(int index) удаляет элемент под индексом
    public int remove (int index){
        isIndexExist(index);  //проверяем индекс
        int [] temp = elements;  //во временный массив заносим ссылку на текущий массив
        elements = new int [temp.length-1];  //полю elements присваиваем ссылку на новый массив размером меньше на 1
        int value = temp[index];  //сохраняем в доп. переменную значение удаляемого элемента
        System.arraycopy(temp, 0, elements, 0, index);  //копируем левую часть массива до указанного индекса
        System.arraycopy(temp, index + 1, elements, index, temp.length - index - 1);  //копируем правую часть массива после указанного индекса
        size--;  //уменьшаем значение переменной
        return value;
    }
//clear() очищает коллекцию
    public void clear(){
//  Arrays.fill(elements, 0);               //заменяет все элементы массива на 0 , не подходит
//  elements = null;                        //обнуляет массив , тоже не слишком подходит
    elements = new int[elements.length];    //создает новый пустой массив такой же длины и переназначает ссылку
    }
//size() возвращает размер коллекции
    public int size (){
        return size;
    }
//get(int index) возвращает элемент под индексом
    public int get(int index){
        isIndexExist(index);  //проверка корректности введенного индекса
        return elements[index];
    }
//-------------------------------------------------------------------------------------------------

//дополнительный закрытый метод для увеличения емкости массива
    private int [] increaseCapacity(){
        int [] temp = new int[(elements.length * 2)];  //создаем новый массив большего размера
        System.arraycopy(elements, 0, temp, 0, elements.length);  //копируем в новый массив элементы из старого массива
        return temp;
    }

//устанавливает элемент на указанную позицию
    public int set(int value, int index){
        isIndexExist(index);
        int temp = elements[index];
        elements[index] = value;
        return temp;
    }

//переопределил метод для красивого вывода списка на экран, иначе будут выводиться значения незаполненных ячеек [1, 10] вместо [1, 10, 0, 0...]
    @Override
    public String toString(){
        int [] temp = new int[size];
        System.arraycopy(elements, 0, temp, 0, size);
        return Arrays.toString(temp);
    }

//проверяем индексы, не выходят ли они за границы массива
    private int isIndexExist(int index){
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elements.length);
        }
        return index;
    }

//проверяем, есть ли элементы в списке
    public boolean isEmpty(){
        return (size == 0);
    }





}
