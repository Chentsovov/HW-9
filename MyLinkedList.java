package EightModule;

import java.util.Objects;

        /*Написать свой класс MyLinkedList как аналог классу LinkedList.
        Нельзя использовать массив. Каждый элемент должен быть отдельным объектом-посредником(Node - нода) который
        хранит ссылку на предыдущий и следующий элемент коллекции (двусвязный список).
        Методы
        add(Object value) добавляет элемент в конец
        remove(int index) удаляет элемент под индексом
        clear() очищает коллекцию
        size() возвращает размер коллекции
        get(int index) возвращает элемент под индексом*/

public class MyLinkedList<E> {
    protected Node head;
    protected Node end;
    protected int size;
//Generic (обобщения) - это особые средства Java, позволяющие писать алгоритмы, работающие с
// разными типами данных без изменения исходного кода методов и классов, которые работают с этими данными.
//В Java для этого в определенных местах мы пишем угловые <> скобки, где указываем типы данных.
// Дальше в коде мы работаем уже с этими типами данных, которые мы описывали в угловых скобках.
    public MyLinkedList() {
        this.head = null;
    }

    //вспомогательные методы
    // Проверяем, пустая ли коллекция.
    public Boolean isEmpty() {
        return size == 0;
    }

    // внутренний класс посредник Node.
    class Node {
        protected Node previous;
        protected Node next;
        E data;
    }


///--------------------------------------------------------------------------------------------------
    // Добавляет элемент в конец.
    public MyLinkedList add(E value) {
        Node node = new Node();
        node.data = value;

        if (Objects.isNull(head)) {
            head = node;
            end = node;
        } else {
            end.next = node;
            node.previous = end;
            end = node;
        }

        size++;

        return this;
    }

    // Удаляет элемент под индексом.
    public MyLinkedList remove(int index) {
        Node currentNode = head;

        while (index != 0) {
            index--;
            currentNode = currentNode.next;
        }

        if (currentNode == head) {
            head = head.next;
            size--;
        } else if (currentNode == end) {
            end = currentNode.previous;
            size--;
        } else {
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
            size--;
        }
        return this;
    }

    // Очищает коллекцию.
    public MyLinkedList clear() {
        if (isEmpty()) {
            System.out.println("Коллекция итак пустая!");
            return this;
        }

        head = null;
        end = null;
        size = 0;
        System.out.println("Коллекция успешно очищена!");
        return this;
    }

    // Возвращает размер коллекции.
    public int size() {
        if (isEmpty()) {
            System.out.println("Коллекция пустая!");
            return 0;
        }

        System.out.print("Размер коллекции: ");
        return size;
    }

    // Возвращает элемент под индексом.
    public E get(int index)     {
        Node currentNode;

        if (((size - index) / (size * 1.0)) < 0.5) {
            currentNode = end;
            while (index != size - 1) {
                index++;
                currentNode = currentNode.previous;
            }
        } else {
            currentNode = head;
            while (index != 0) {
                index--;
                currentNode = currentNode.next;
            }
        }

        System.out.print("Элемент под индеком: " + index + " - ");
        return currentNode.data;
    }

    }