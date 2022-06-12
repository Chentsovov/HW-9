package EightModule;

//Написать свой класс MyQueue как аналог классу Queue, который будет работать по принципу FIFO (first-in-first-out).
//Можно делать либо с помощью Node либо с помощью массива.
//Методы
//
//-add(Object value) добавляет элемент в конец                           +
//-remove(int index) удаляет элемент под индексом                        -
//-clear() очищает коллекцию                                             +
//-size() возвращает размер коллекции                                    +
//-peek() возвращает первый элемент в очереди (FIFO)                     +
//-poll() возвращает первый элемент в очереди и удаляет его из коллекции +
public class MyQueue {
    // Указатель на первый элемент
    private ObjectBox head = null;
    // Указатель на последний элемент
    private ObjectBox tail = null;
    // Поле для хранения размера очереди
    private int size = 0;

//-----------------------------------------------------------------------------
// вспомогательный класс будет закрыт от посторонних глаз
    private class ObjectBox {
        // Поле для хранения объекта
        private Object object;
        // Поле для указания на следующий элемент в цепочке.
        // Если оно равно NULL - значит это последний элемент
        private ObjectBox next;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public ObjectBox getNext() {
            return next;
        }

        public void setNext(ObjectBox next) {
            this.next = next;
        }
    }
//---------------------------------------------------------------------------------
//-add(Object value) добавляет элемент в конец
    public void add(Object obj) {
        // Сразу создаем вспомогательный объект и помещаем новый элемент в него
        ObjectBox ob = new ObjectBox();
        ob.setObject(obj);
        // Если очередь пустая - в ней еще нет элементов
        if (head == null) {
            // Теперь наша голова указывает на наш первый элемент
            head = ob;
        } else {
            // Если это не первый элемент, то надо, чтобы последний элемент в очереди
            // указывал на вновь прибывший элемент
            tail.setNext(ob);
        }
        // И в любом случае нам надо наш "хвост" переместить на новый элемент
        // Если это первый элемент, то "голова" и "хвост" будут указывать на один и тот же элемент
        tail = ob;
        // Увеличиваем размер нашей очереди
        size++;
    }

//-poll() возвращает первый элемент в очереди и удаляет его из коллекции
    public Object poll() {
        // Если у нас нет элементов, то возвращаем null
        if (size == 0) {
            return null;
        }
        // Получаем наш объект из вспомогательного класса из "головы"
        Object obj = head.getObject();
        // Перемещаем "голову" на следующий элемент
        head = head.getNext();
        // Если это был единственный элемент, то head станет равен null
        // и тогда tail (хвост) тоже дожен указать на null.
        if (head == null) {
            tail = null;
        }
        // Уменьшаем размер очереди
        size--;
        // Возвращаем значение
        return obj;
    }

//-size() возвращает размер коллекции
    public int size() {
        return size;
    }

///-peek() возвращает первый элемент в очереди (FIFO)
public Object peek() {
    // Если у нас нет элементов, то возвращаем null
    if (size == 0) {
        return null;
    }
// Получаем наш объект из вспомогательного класса из "головы"
    Object obj = head.getObject();
        // Возвращаем значение
    return obj;
}

///-remove(int index) удаляет элемент под индексом
public boolean remove(int index) {
    // Если нет элементов или индекс больше размера или индекс меньше 0
    if(size == 0 || index >= size || index < 0) {
        return false;
    }
    // Устанавлваем указатель, который будем перемещать на "голову"
    ObjectBox current = head;
    // В этом случае позиция равну 0
    int pos = 0;
    // Пока позиция не достигла нужного индекса
    while(pos < index) {
        // Перемещаемся на следующий элемент
        current = current.getNext();
        // И увеличиваем позицию
        pos++;
    }
    // Мы дошли до нужной позиции и теперь можем удалить элемент
                //тут пока туплю..


    //Object obj = current.getObject();
    size--;
    return true;
}

// возвращает элемент под индексом
public Object get(int index) {
    // Если нет элементов или индекс больше размера или индекс меньше 0
    if(size == 0 || index >= size || index < 0) {
        return null;
    }
    // Устанавлваем указатель, который будем перемещать на "голову"
    ObjectBox current = head;
    // В этом случае позиция равну 0
    int pos = 0;
    // Пока позиция не достигла нужного индекса
    while(pos < index) {
        // Перемещаемся на следующий элемент
        current = current.getNext();
        // И увеличиваем позицию
        pos++;
    }
    // Мы дошли до нужной позиции и теперь можем вернуть элемент
    Object obj = current.getObject();
    return obj;
}
//-clear() очищает коллекцию
    public Object clear() {
        if (size == 0) {
            System.out.println("Коллекция итак пустая!");
           return this;
        }
        head = null;
        tail = null;
        size = 0;
        System.out.println("Коллекция успешно очищена!");
        return this;
    }


}